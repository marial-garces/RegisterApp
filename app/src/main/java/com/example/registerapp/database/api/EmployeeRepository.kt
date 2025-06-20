package com.example.registerapp.database.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepository {

    private val apiService = NetworkModule.employeeApiService

    suspend fun getEmployees(): Result<List<Employee>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getEmployees()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(Exception("Error ${response.code()}: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}