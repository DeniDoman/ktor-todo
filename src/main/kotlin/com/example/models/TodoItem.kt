package com.example.models

import org.jetbrains.exposed.sql.*


data class TodoItem(val id: Int, var text: String, val isDone: Boolean)

object TodoList : Table() {
    val id = integer("id").autoIncrement()
    val text = varchar("text", 1024)
    val isDone = bool("isDone")

    override val primaryKey = PrimaryKey(id)
}