package com.kaleichyk.models

abstract class BaseUser(open val id: Int, open val name: String, open val age: Int)

data class User(
    val id: Int,
    val name: String,
    val age: Int
)

data class UserAuth(
    val id: Int,
    val name: String,
    val password: String,
    val age: Int
) {

    fun toUser() = User(id, name, age)
}
