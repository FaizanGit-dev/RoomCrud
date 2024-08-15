package com.example.room.mvvm.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.mvvm.R
import com.example.room.mvvm.adapter.LoginAdapter
import com.example.room.mvvm.component.DaggerLoginComponent
import com.example.room.mvvm.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.ageInput
import kotlinx.android.synthetic.main.activity_main.classInput
import kotlinx.android.synthetic.main.activity_main.genderInput
import kotlinx.android.synthetic.main.activity_main.nameInput
import kotlinx.android.synthetic.main.activity_main.rollnoInput
import kotlinx.android.synthetic.main.activity_main.schoolInput


class MainActivity : AppCompatActivity() {

    //    private lateinit var loginViewModel: LoginViewModel
    private val loginComponent = DaggerLoginComponent.builder().build()
    private var loginViewModel = loginComponent.getLoginViewModel()

    private lateinit var loginAdapter: LoginAdapter

    lateinit var context: Context

    lateinit var name: String
    lateinit var sclass: String
    lateinit var rollno: String
    lateinit var school: String
    lateinit var gender: String
    lateinit var age: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        val insertButton = findViewById<Button>(R.id.insertButton)


        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        insertButton.setOnClickListener {

            name = nameInput.text.toString().trim()
            sclass = classInput.text.toString().trim()
            rollno = rollnoInput.text.toString().trim()
            school = schoolInput.text.toString().trim()
            gender = genderInput.text.toString().trim()
            age = ageInput.text.toString().trim()
            if (name.isEmpty()) {
                nameInput.error = "Please enter the name"
            } else if (sclass.isEmpty()) {
                classInput.error = "Please enter the class"
            } else if (rollno.isEmpty()) {
                rollnoInput.error = "Please enter the roll number"
            } else if (school.isEmpty()) {
                schoolInput.error = "Please enter the school"
            } else if (age.isEmpty()) {
                ageInput.error = "Please enter the age"
            } else if (gender.isEmpty()) {
                genderInput.error = "Please enter the gender"
            } else {
                loginViewModel.insertData(context, name, sclass, rollno, school, gender, age)
                Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        loginAdapter = LoginAdapter(mutableListOf())
        recyclerView.adapter = loginAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loginViewModel.getLoginDetails(context)!!.observe(this, Observer { loginDetails ->
            loginDetails?.let {
                loginAdapter.updateData(it)
                Log.d("Details", "$it")
            }


        })

        loginAdapter.onEditClickListener = { loginItem ->
            // Handle edit action
            Toast.makeText(this, "Edit clicked for: ${loginItem.Name}", Toast.LENGTH_SHORT).show()
            // You can show a dialog or navigate to an edit screen here
        }

        loginAdapter.onDeleteClickListener = { loginItem ->
            // Handle delete action
            loginViewModel.deleteData(loginItem)
            Toast.makeText(this, "Deleted: ${loginItem.Name}", Toast.LENGTH_SHORT).show()
        }

    }
}