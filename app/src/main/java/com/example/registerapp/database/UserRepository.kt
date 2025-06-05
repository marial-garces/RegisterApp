package com.example.registerapp.database

class UserRepository(private val dao: UserDatabaseDao) {
    val users = dao.getAllUsers()

    suspend fun insert(user: User){
        return dao.insertUser(user)
    }

    suspend fun getUserName(userName: String): User?{
        return dao.getUserName(userName)
    }


}