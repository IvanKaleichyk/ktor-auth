package com.kaleichyk.models

import com.google.gson.annotations.SerializedName
import com.kaleichyk.utils.hash.PasswordHasher

abstract class BaseUser(open val id: Int, open val name: String, open val age: Int)

data class User(
    val id: Int,
    val name: String,
    val password: String,
    val age: Int
)

data class UserAuth(
    val id: Int,
    val name: String,
    val password: String,
    @SerializedName("repeat_password")
    val repeatPassword: String,
    val age: Int,
    val roles: List<String> = emptyList()
) {

    fun toUser() = User(id, name, PasswordHasher.hash(password), age)
}
