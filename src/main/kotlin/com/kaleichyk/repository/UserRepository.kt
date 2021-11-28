package com.kaleichyk.repository

import com.kaleichyk.models.User

interface UserRepository {

    fun getUsers(): List<User>

    fun getUser(id: Int): User?

    fun doesExists(id: Int): Boolean

    fun addUser(user: User): Boolean
}

class UserRepositoryImpl : UserRepository {

    private val users = mutableListOf<User>()

    override fun getUsers() = users

    override fun getUser(id: Int) = users.find { it.id == id }

    override fun doesExists(id: Int) = users.find { it.id == id } != null

    override fun addUser(user: User) = users.add(user)
}