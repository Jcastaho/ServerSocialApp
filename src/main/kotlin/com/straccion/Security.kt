package com.straccion

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.straccion.model.AuthResponse
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import org.jetbrains.exposed.sql.*

private const val CLAIM = "email"
private const val jwtAudience = "jwt-audience"
private const val jwtDomain = "https://jwt-provider-domain/"
private const val jwtRealm = "ktor sample app"
private const val jwtSecret = "secret"
fun Application.configureSecurity() {
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim(CLAIM).asString() != null){
                    JWTPrincipal(payload = credential.payload)
                }else{
                    null
                }
            }
            challenge { _, _ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = AuthResponse(
                        errorMessage = "Token no valido o vencido"
                    )
                )
            }
        }
    }
}

fun generateToken(email: String): String{
    return JWT.create()
        .withAudience(jwtAudience)
        .withIssuer(jwtDomain)
        .withClaim(CLAIM, email)
     //   .withExpiresAt() esto sirve para que el token expire
        .sign(Algorithm.HMAC256(jwtSecret))
}