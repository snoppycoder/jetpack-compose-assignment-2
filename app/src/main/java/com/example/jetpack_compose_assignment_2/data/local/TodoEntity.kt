package com.example.jetpack_compose_assignment_2.data.local
// data/local/TodoEntity.kt
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpack_compose_assignment_2.domain.model.Todo

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)
fun TodoEntity.toDomain(): Todo = Todo(id, userId, title, completed)