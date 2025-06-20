package com.example.registerapp.database.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://hub.dummyapis.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val employeeApiService: EmployeeApiService by lazy {
        retrofit.create(EmployeeApiService::class.java)
    }
}