package com.straccion.route

import com.straccion.model.AuthResponse
import com.straccion.model.SignInParams
import com.straccion.model.SignUpParams
import com.straccion.repository.auth.AuthRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Routing.authRouting() {
    val repository by inject<AuthRepository>()

    route(path = "/signup"){
        post{
            val params = call.receiveNullable<SignUpParams>()
            if(params == null){
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage = "Credenciales Invalidos!!"
                    )
                )
                return@post
            }
            val result = repository.signUp(params = params)
            call.respond(
                status = result.code,
                message = result.data
            )
        }
    }
    route(path = "/login"){
        post{
            val params = call.receiveNullable<SignInParams>()
            if(params == null){
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage = "Credenciales Invalidos!!"
                    )
                )
                return@post
            }
            val result = repository.signIn(params = params)
            call.respond(
                status = result.code,
                message = result.data
            )
        }
    }
}