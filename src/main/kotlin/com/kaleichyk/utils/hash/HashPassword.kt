package com.kaleichyk.utils.hash

import org.mindrot.jbcrypt.BCrypt

typealias HashedPassword = String

object PasswordHasher {

    fun hash(password: String): HashedPassword =
        BCrypt.hashpw(password, BCrypt.gensalt())

    fun check(password: String, hashedPassword: HashedPassword) =
        BCrypt.checkpw(password, hashedPassword)
}
