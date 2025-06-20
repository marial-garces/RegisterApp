package com.example.registerapp.database.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmployeeApiService {
    @GET("employee")
    suspend fun getEmployees(
        @Query("noofRecords") numberOfRecords: Int = 10,
        @Query("idStarts") idStarts: Int = 1001
    ): Response<List<Employee>>
}