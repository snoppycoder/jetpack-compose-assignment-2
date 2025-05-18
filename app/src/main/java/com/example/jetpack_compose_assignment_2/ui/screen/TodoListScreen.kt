package com.example.jetpack_compose_assignment_2.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack_compose_assignment_2.domain.model.Todo
import com.example.jetpack_compose_assignment_2.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(viewModel: MainViewModel, navController: NavController) {
    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTodos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Todos") },

            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todos) { todo ->
                TodoListItem(todo) {
                    navController.navigate("detail/${todo.id}")
                }
            }
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (todo.completed)
                Color(0xFFB2DFDB)
            else
                Color(0xFFFFF3E0)
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = if (todo.completed) "✅ Completed" else "⏳ Pending",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (todo.completed)
                        Color(0xFF00796B)
                    else
                        Color(0xFFEF6C00)
                )
            }

            Icon(
                imageVector = if (todo.completed) Icons.Default.CheckCircle else Icons.Filled.Close,
                contentDescription = null,
                tint = if (todo.completed) Color(0xFF00796B) else Color(0xFFEF6C00)
            )
        }
    }
}

