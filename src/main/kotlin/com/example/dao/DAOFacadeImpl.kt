package com.example.dao

import com.example.models.TodoItem
import com.example.models.TodoList
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToTodoItem(row: ResultRow) = TodoItem(
        id = row[TodoList.id],
        text = row[TodoList.text],
        isDone = row[TodoList.isDone]
    )

    override suspend fun getAllItems(): List<TodoItem> = dbQuery {
        TodoList.selectAll().map(::resultRowToTodoItem)
    }

    override suspend fun addNewItem(text: String): TodoItem? = dbQuery {
        val insertStatement = TodoList.insert {
            it[TodoList.text] = text
            it[TodoList.isDone] = false
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToTodoItem)
    }

    override suspend fun deleteItem(id: Int): Boolean = dbQuery {
        TodoList.deleteWhere { TodoList.id eq id } > 0
    }

    override suspend fun changeItemState(id: Int, isDone: Boolean): Boolean = dbQuery {
        TodoList.update({TodoList.id eq id}) {
            it[TodoList.isDone] = isDone
        } > 0
    }
}