package com.example.registerapp.database.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.registerapp.database.User
import com.example.registerapp.database.UserDatabase
import com.example.registerapp.database.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class EditUserViewModel(private val repo: UserRepository): ViewModel(){
    private val _currentUser = MutableLiveData<User?>()
    val currentUser = _currentUser

    fun loadById(userId: Long) = viewModelScope.launch {
        val user = repo.getUserById(userId)
        _currentUser.postValue(user)
    }

    fun update(userName: String, email: String, password: String) = viewModelScope.launch {
        _currentUser.value?.let {
            val updated = it.copy(userName = userName, email = email, password = password)
            repo.update(updated)
            _currentUser.postValue(updated)
        }
    }

    fun logout() = viewModelScope.launch {
        _currentUser.value?.let {
            repo.delete(it)
        }
    }

    class Factory(private val repo: UserRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditUserViewModel::class.java)) {
                return EditUserViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}



