package com.example.moviezone.repository

import com.example.moviezone.dao.DatabaseDAO
import com.example.moviezone.model.User

class UsersRepository {
    var users: List<User> = listOf()
    var user: User? = null

    private val databaseDao = DatabaseDAO().apply {
        this.setUserRepository(this@UsersRepository)
    }

    fun getUsers() {
        databaseDao.getUsers()
    }
}