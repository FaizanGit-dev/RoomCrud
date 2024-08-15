package com.example.room.mvvm.component


import com.example.room.mvvm.model.LoginTableModel
import com.example.room.mvvm.viewmodel.LoginViewModel
import dagger.Component

@Component
interface LoginComponent {

    fun getLoginViewModel(): LoginViewModel

}