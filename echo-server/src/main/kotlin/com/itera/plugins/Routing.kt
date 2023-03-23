package com.itera.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText(call.request.queryParameters["val"] ?: "No value passed")
        }
    }
}
