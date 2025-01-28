package com.straccion.plugins


import com.straccion.route.*
import com.straccion.util.Constants.POST_IMAGES_FOLDER
import com.straccion.util.Constants.POST_IMAGES_FOLDER_PATH
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        authRouting()
        followsRouting()
        postRouting()
        profileRouting()
        postCommentsRouting()
        postLikesRouting()
        staticFiles(POST_IMAGES_FOLDER, File(POST_IMAGES_FOLDER_PATH)) //para mostrar las imagenes staticas

    }
}
