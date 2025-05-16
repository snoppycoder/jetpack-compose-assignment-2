package com.example.jetpack_compose_assignment_2.data.Network

import com.example.jetpack_compose_assignment_2.data.local.TodoEntity
import com.example.jetpack_compose_assignment_2.domain.model.Todo

// data/remote/TodoDto.kt
data class TodoDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
) {
    fun toTodo(): Todo = Todo(id, userId, title, completed)

}
fun TodoDto.toEntity(): TodoEntity = TodoEntity(
    id = id,
    userId = userId,
    title = title,
    completed = completed
)