package com.example

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dao.DAOFacadeMock
import com.example.dao.DatabaseFactory
import com.example.plugins.configureRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import java.util.*

fun main() {
    DatabaseFactory.init()
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::applicationModuleDI).start(wait = true)
}

fun Application.applicationModuleDI() = applicationModuleDI(DI {
    bind<DAOFacade>() with singleton { DAOFacadeImpl() }
})

fun Application.applicationModuleDI(kodein: DI) {
    install(ContentNegotiation) { json() }
    install(CORS) {
        allowHost("localhost:3000")
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Delete)
        allowHeader(HttpHeaders.ContentType)
    }
    configureRouting(kodein)
}

/**
1) Create ktor app with Routing plugin
2) Create client app
3) Add singlePageApplication to Routes: https://ktor.io/docs/serving-spa.html
4) Adjust build.gradle to build client before running backend
5) Add exposed to build a database: https://ktor.io/docs/interactive-website-add-persistence.html
6) Add json negotiation: https://ktor.io/docs/serialization.html
7) Install and configure CORS: https://ktor.io/docs/cors.html
8) Add Kodein https://kosi-libs.org/kodein/7.18/getting-started.html
 **/