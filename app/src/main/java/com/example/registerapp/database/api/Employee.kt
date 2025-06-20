package com.example.registerapp.database.api

import com.google.gson.annotations.SerializedName

data class Employee(

    @SerializedName("id") val id: Int,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("email")
    val email: String

){
    val fullName: String
        get() = "$firstName $lastName"
}
