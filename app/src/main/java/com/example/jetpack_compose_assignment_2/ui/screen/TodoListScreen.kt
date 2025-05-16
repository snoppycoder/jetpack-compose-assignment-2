package com.example.jetpack_compose_assignment_2.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack_compose_assignment_2.domain.model.Todo
import com.example.jetpack_compose_assignment_2.ui.viewmodel.MainViewModel

@Composable
fun TodoListScreen(viewModel: MainViewModel, navController: NavController) {
    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTodos()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(todos) { todo ->
            TodoListItem(todo) {
                navController.navigate("detail/${todo.id}")
            }
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, onClick: () -> Unit) {
    Column(modifier = Modifier
        .clickable { onClick() }
        .padding(16.dp)) {
        Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
        Text(text = if (todo.completed) "Completed" else "Pending", style = MaterialTheme.typography.bodySmall)
    }
}
