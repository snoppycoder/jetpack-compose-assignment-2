package com.example.jetpack_compose_assignment_2.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.jetpack_compose_assignment_2.domain.model.Todo
import com.example.jetpack_compose_assignment_2.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun TodoDetailScreen(viewModel: MainViewModel, todoId: Int, navBackStackEntry: NavBackStackEntry
                     , navController: NavController
) {
    val scope = rememberCoroutineScope()
    var todo by remember { mutableStateOf<Todo?>(null) }



    LaunchedEffect(todoId) {
        scope.launch {
            todo = viewModel.getTodoById(todoId)
        }
    }

    if (todo == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${todo!!.title}", style = MaterialTheme.typography.titleLarge)
            Text(text = "User ID: ${todo!!.userId}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Completed: ${todo!!.completed}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
