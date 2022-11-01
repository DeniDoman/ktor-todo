package com.example

import com.example.dao.DatabaseFactory
import com.example.plugins.configureRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    DatabaseFactory.init()
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) { json() }
        install(CORS) {
            allowHost("localhost:3000")
            allowMethod(HttpMethod.Patch)
            allowMethod(HttpMethod.Delete)
            allowHeader(HttpHeaders.ContentType)
        }
        configureRouting()
    }.start(wait = true)
}

/**
 1) Create ktor app with Routing plugin
 2) Create client app
 3) Add singlePageApplication to Routes: https://ktor.io/docs/serving-spa.html
 4) Adjust build.gradle to build client before running backend
 5) Add exposed to build a database: https://ktor.io/docs/interactive-website-add-persistence.html
 6) Add json negotiation: https://ktor.io/docs/serialization.html
 7) Install and configure CORS: https://ktor.io/docs/cors.html
 **/