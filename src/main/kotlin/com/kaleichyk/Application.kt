package com.kaleichyk

import com.kaleichyk.plugins.configureSecurity
import com.kaleichyk.plugins.configureSerialization
import com.kaleichyk.plugins.configureUserRoute
import io.ktor.application.Application
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureSecurity()

    routing {
        configureUserRoute()
    }
}
