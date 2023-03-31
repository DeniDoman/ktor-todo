package com.example

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeMock
import com.example.dao.DatabaseFactory
import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val mock = DAOFacadeMock()
val playwright: Playwright = Playwright.create()
val browser: Browser = playwright.chromium().launch(BrowserType.LaunchOptions().setHeadless(true))

fun Application.applicationModuleDIMocked() = applicationModuleDI(DI {
    bind<DAOFacade>() with singleton { mock }
})

class End2EndTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            DatabaseFactory.init()
            playwright.selectors().setTestIdAttribute("data-test")
            embeddedServer(
                Netty,
                port = 8080,
                host = "0.0.0.0",
                module = Application::applicationModuleDIMocked
            ).start()
        }
    }

    @BeforeEach
    fun beforeEach() {
        mock.reset()
    }

    @Test
    fun test1() = testApplication {
        val context = browser.newContext()
        val page = context.newPage()

        page.pause()
        page.navigate("http://0.0.0.0:8080")
        assertThat(page.getByTestId("todo-list-item")).containsText("Mocked item");
        mock.renameFirstItem("Renamed item")
        page.reload()
        assertThat(page.getByTestId("todo-list-item")).containsText("Renamed item");
    }

    @Test
    fun test2() = testApplication {
        val context = browser.newContext()
        val page = context.newPage()

        page.navigate("http://0.0.0.0:8080")
        assertThat(page.getByTestId("todo-list-item")).containsText("Mocked item");
        mock.renameFirstItem("Renamed item")
        page.reload()
        assertThat(page.getByTestId("todo-list-item")).containsText("Renamed item");
    }
}