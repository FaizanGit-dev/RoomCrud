package com.example.room.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class LoginTableModel(

    @ColumnInfo(name = "name")
    var Name: String,

    @ColumnInfo(name = "sclass")
    var SClass: String,

    @ColumnInfo(name = "rollno")
    var RollNo: String,

    @ColumnInfo(name = "school")
    var School: String,

    @ColumnInfo(name = "age")
    var Age: String,

    @ColumnInfo(name = "gender")
    var gender: String

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}