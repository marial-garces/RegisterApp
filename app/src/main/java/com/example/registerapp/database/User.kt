package com.example.registerapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @ColumnInfo(name = "password_num")
    val password: Int
)
