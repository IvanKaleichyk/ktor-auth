package com.kaleichyk

import com.kaleichyk.plugins.configureRouting
import com.kaleichyk.plugins.configureSecurity
import com.kaleichyk.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSecurity()
    configureSerialization()
}
