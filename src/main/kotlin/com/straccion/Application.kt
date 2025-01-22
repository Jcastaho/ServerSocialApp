package com.straccion

import com.straccion.dao.DataBaseFactory
import com.straccion.di.configureDi
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DataBaseFactory.init()
    configureSerialization()
    configureDi()
    configureSecurity()
    configureRouting()
}
