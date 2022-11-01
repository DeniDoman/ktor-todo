package com.example.plugins

import com.example.dao.DAOFacadeImpl
import com.example.json.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    val dao = DAOFacadeImpl()

    routing {
        singlePageApplication {
            react("client/build")
        }
        get("/api/v1/hello") {
            call.respondText("Hello World!")
        }
        get("/api/v1/getAllItems") {
            val items = dao.getAllItems().map { SerializableTodoItem(it.id, it.text, it.isDone) }
            val res = GetAllItemsResponse(items)
            call.respond(res)
        }
        post("/api/v1/addNewItem") {
            val req = call.receive<AddNewItemRequest>()
            val item = dao.addNewItem(req.text) ?: throw Exception("I can't add an item for some reason")
            val res = AddNewItemResponse(SerializableTodoItem(item.id, item.text, item.isDone))
            call.respond(res)
        }
        delete("/api/v1/deleteItem/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("ToDo item ID is not provided")
            val result = dao.deleteItem(id)
            val res = DeleteItemResponse(result)
            call.respond(res)
        }
        patch("/api/v1/changeItemState/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw Exception("ToDo item ID is not provided")
            val req = call.receive<ChangeItemStateRequest>()
            val result = dao.changeItemState(id, req.isDone)
            val res = ChangeItemStateResponse(result)
            call.respond(res)
        }
    }
}
