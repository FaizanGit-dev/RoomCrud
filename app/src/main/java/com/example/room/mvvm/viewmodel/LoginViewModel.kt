package com.example.room.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.mvvm.model.LoginTableModel
import com.example.room.mvvm.repository.LoginRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    var liveDataLogin: LiveData<List<LoginTableModel>>? = null

    fun insertData(
        context: Context,
        name: String,
        sclass: String,
        rollno: String,
        school: String,
        age: String,
        gender: String
    ) {
        LoginRepository.insertData(context, name, sclass, rollno, school, age, gender)
    }

    fun getLoginDetails(context: Context): LiveData<List<LoginTableModel>>? {
        liveDataLogin = LoginRepository.getLoginDetails(context)
        return liveDataLogin
    }

    fun updateData(loginTableModel: LoginTableModel) {
        viewModelScope.launch {
            LoginRepository.update(loginTableModel)
        }
    }

    fun deleteData(loginTableModel: LoginTableModel) {
        viewModelScope.launch {
            LoginRepository.delete(loginTableModel)
        }
    }

}