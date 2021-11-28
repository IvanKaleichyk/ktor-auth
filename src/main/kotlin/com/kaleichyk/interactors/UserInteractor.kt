package com.kaleichyk.interactors

import com.kaleichyk.models.User
import com.kaleichyk.repository.UserRepositoryImpl

interface UserInteractor {

    fun getUsers(): List<User>

    fun getUser(id: Int): User?
}

class UserInteractorImpl(
    private val repositoryImpl: UserRepositoryImpl
) : UserInteractor {

    override fun getUsers() = repositoryImpl.getUsers()

    override fun getUser(id: Int) = repositoryImpl.getUser(id)
}