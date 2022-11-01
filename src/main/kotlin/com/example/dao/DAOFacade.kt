package com.example.dao

import com.example.models.*

interface DAOFacade {
    suspend fun getAllItems(): List<TodoItem>
    suspend fun addNewItem(text: String): TodoItem?
    suspend fun deleteItem(id: Int): Boolean
    suspend fun changeItemState(id: Int, isDone: Boolean): Boolean
}