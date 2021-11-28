package com.kaleichyk.plugins

import com.kaleichyk.interactors.UserInteractorImpl
import com.kaleichyk.repository.UserRepositoryImpl
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.configureUserRoute() {

    val interactor = UserInteractorImpl(UserRepositoryImpl())

    authenticate {
        route("users") {
            get {
                call.respond(interactor.getUsers())
            }
        }
    }
}