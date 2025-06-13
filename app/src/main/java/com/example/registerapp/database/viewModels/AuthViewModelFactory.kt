package com.example.registerapp.database.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.registerapp.database.UserRepository

class AuthViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModels::class.java)){
            return AuthViewModels(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}