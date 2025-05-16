package com.example.jetpack_compose_assignment_2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_assignment_2.domain.model.Todo
import com.example.jetpack_compose_assignment_2.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            try {
                _todos.value = repository.getTodos()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    suspend fun getTodoById(id: Int): Todo? {
        return repository.getTodoById(id)
    }
}
