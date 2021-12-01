package com.kaleichyk.interactors

import com.kaleichyk.models.User
import com.kaleichyk.repository.UserRepository

interface UserInteractor {

    fun getUsers(): List<User>

    fun getUser(id: Int): User?

    fun addUser(user: User): Boolean
}

class UserInteractorImpl(
    private val repository: UserRepository
) : UserInteractor {

    override fun getUsers() = repository.getUsers()

    override fun getUser(id: Int) = repository.getUser(id)

    override fun addUser(user: User) = repository.addUser(user)
}