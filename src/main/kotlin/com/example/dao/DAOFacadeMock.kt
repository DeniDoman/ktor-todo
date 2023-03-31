package com.example.dao

import com.example.models.TodoItem

class DAOFacadeMock : DAOFacade {
    private val itemsList = mutableListOf(TodoItem(0, "Mocked item", false))

    override suspend fun getAllItems(): List<TodoItem> {
        return itemsList
    }

    override suspend fun addNewItem(text: String): TodoItem? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun changeItemState(id: Int, isDone: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    fun renameFirstItem(newName: String) {
        itemsList.first().text = newName
    }

    fun reset() {
        itemsList.clear()
        itemsList.add(TodoItem(0, "Mocked item", false))
    }
}