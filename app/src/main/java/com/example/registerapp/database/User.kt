
package com.example.registerapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Long = 0,

    @ColumnInfo(name = "email", index = true) val email: String,

    @ColumnInfo(name = "user_name") val userName: String,

    @ColumnInfo(name = "password") val password: String
)
