package com.example.jetpack_compose_assignment_2.domain.repository

import com.example.jetpack_compose_assignment_2.data.Network.ApiServiceInterface
import com.example.jetpack_compose_assignment_2.data.Network.toEntity
import com.example.jetpack_compose_assignment_2.data.local.TodoDao
import com.example.jetpack_compose_assignment_2.data.local.toDomain
import com.example.jetpack_compose_assignment_2.domain.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(
    private val api: ApiServiceInterface,
    private val dao: TodoDao
) {
    suspend fun getTodos(): List<Todo> = withContext(Dispatchers.IO) {
        try {
            val remoteTodos = api.getTodos()
            val todoEntities = remoteTodos.map { it.toEntity() }
            dao.insertAll(todoEntities)
            todoEntities.map { it.toDomain() }
        } catch (e: Exception) {
            dao.getTodos().map { it.toDomain() }
        }
    }

    suspend fun getTodoById(id: Int): Todo? = withContext(Dispatchers.IO) {
        dao.getTodoById(id)?.toDomain()
    }
}
