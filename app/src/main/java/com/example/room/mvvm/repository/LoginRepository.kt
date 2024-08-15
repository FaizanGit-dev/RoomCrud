package com.example.room.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.room.mvvm.model.LoginTableModel
import com.example.room.mvvm.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginRepository {

    companion object {

        var loginDatabase: LoginDatabase? = null

        var loginTableModel: LiveData<List<LoginTableModel>>? = null

        fun initializeDB(context: Context) : LoginDatabase {
            return LoginDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context,name: String,sclass: String, rollno: String, school: String,age: String,gender: String) {

            loginDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = LoginTableModel(name,sclass,rollno,school,age,gender)
                loginDatabase!!.loginDao().InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context) : LiveData<List<LoginTableModel>>? {

            loginDatabase = initializeDB(context)

            loginTableModel = loginDatabase!!.loginDao().getLoginDetails()

            return loginTableModel
        }

        suspend fun update(loginTableModel: LoginTableModel) {
            loginDatabase!!.loginDao().update(loginTableModel)
        }

        suspend fun delete(loginTableModel: LoginTableModel) {
            loginDatabase!!.loginDao().delete(loginTableModel)
        }

    }
}