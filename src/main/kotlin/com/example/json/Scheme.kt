package com.example.json

import kotlinx.serialization.Serializable


@Serializable
data class SerializableTodoItem(val id: Int, val text: String, val isDone: Boolean)

// Requests
@Serializable
data class AddNewItemRequest(val text: String)

@Serializable
data class ChangeItemStateRequest(val isDone: Boolean)

// Responses
@Serializable
data class GetAllItemsResponse(val items: List<SerializableTodoItem>)

@Serializable
data class AddNewItemResponse(val item: SerializableTodoItem)

@Serializable
data class DeleteItemResponse(val result: Boolean)

@Serializable
data class ChangeItemStateResponse(val result: Boolean)
