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
class EditUserViewModel(application: UserRepository): AndroidViewModel(application) {
    private val repo = UserRepository(UserDatabase.getInstance(application).userDao())
    val currentUser = MutableLiveData<User>()

    fun loadUser(user: User){
        currentUser.postValue(user)
    }

    fun update(userName: String, email: String, password: String) = viewModelScope.launch {
        currentUser.value?.let {
            val updated = it.copy(userName = userName, email = email, password = password)
            repo.update(updated)
            currentUser.postValue(updated)
        }
    }

    fun logout() = viewModelScope.launch {
        currentUser.value?.let {
            repo.delete(it)
        }
    }
}