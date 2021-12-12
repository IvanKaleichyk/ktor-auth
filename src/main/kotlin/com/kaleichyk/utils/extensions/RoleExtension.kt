package com.kaleichyk.utils.extensions

import io.ktor.auth.jwt.JWTPrincipal

const val ROLES_CLAIM = "ROLES_CLAIM"

fun JWTPrincipal.roles() = (getClaim(ROLES_CLAIM, List::class) as? List<String> ?: emptyList()).toSet()