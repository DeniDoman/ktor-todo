package com.example

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeMock
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

class ApiTest {
    @Test
    fun test1() = testApplication {
        // DatabaseFactory.init()
        val mock = DAOFacadeMock()

        application {
            applicationModuleDI(DI {
                bind<DAOFacade>() with singleton { mock }
            })
        }

        mock.renameFirstItem("Mocked item #1")
        client.get("/api/v1/getAllItems").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(
                """
                {"items":[{"id":0,"text":"Mocked item #1","isDone":false}]}
            """.trimIndent(), bodyAsText()
            )
        }
    }

    @Test
    fun test2() = testApplication {
        // DatabaseFactory.init()
        val mock = DAOFacadeMock()

        application {
            applicationModuleDI(DI {
                bind<DAOFacade>() with singleton { mock }
            })
        }

        mock.renameFirstItem("Mocked item #2")
        client.get("/api/v1/getAllItems").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(
                """
                {"items":[{"id":0,"text":"Mocked item #2","isDone":false}]}
            """.trimIndent(), bodyAsText()
            )
        }
    }
}
