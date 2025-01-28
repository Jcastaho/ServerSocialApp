package com.straccion.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true // Ignora claves desconocidas en el JSON
            prettyPrint = true // Opcional: para un JSON formateado
        })
    }
}
