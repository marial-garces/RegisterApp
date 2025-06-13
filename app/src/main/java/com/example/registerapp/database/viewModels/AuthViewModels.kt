package com.example.registerapp.database.viewModels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.registerapp.database.User
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModels(application: UserRepository) : AndroidViewModel(application) {

    private val repo = UserRepository(UserDatabase.getInstance(application).userDao())

    val loginResult = MutableLiveData<Result<User>>()
    val registerResult = MutableLiveData<Result<Long>>()

    fun login(email: String, password: String) = viewModelScope.launch {
        val user = repo.login(email, password)
        if(user != null) loginResult.postValue(Result.success(user))
        else loginResult.postValue(Result.failure(Exception("Invalid email or password")))
    }

    fun register(userName: String, email: String, password: String) = viewModelScope.launch {
        try {
            val userId = repo.register(userName, email, password)
            registerResult.postValue(Result.success(userId))
        } catch (e: Exception) {
            registerResult.postValue(Result.failure(e))
        }
    }
}