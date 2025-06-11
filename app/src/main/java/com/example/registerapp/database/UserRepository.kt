package com.example.registerapp.database

import kotlin.hashCode

class UserRepository(private val dao: UserDatabaseDao) {
    suspend fun register(userName: String, email: String, password: String): Long {
        if(dao.findByEmail(email) != null) {
            throw IllegalArgumentException("Email already exists")
        }
        val hashed = PasswordHasher.hash(password)
        return dao.insert(User(userName = userName, email = email, password = hashed))
    }

    suspend fun login(email: String, password: String): User? {
        val user = dao.findByEmail(email) ?: return null
        return if(PasswordHasher.verify(password, user.password)) {
            user
        } else {
            null
        }
    }


    suspend fun update(user: User) = dao.update(user)
    suspend fun delete(user: User) = dao.delete(user)
}