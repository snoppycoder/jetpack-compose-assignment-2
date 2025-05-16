package com.example.jetpack_compose_assignment_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_assignment_2.ui.screen.TodoListScreen
import com.example.jetpack_compose_assignment_2.ui.screen.TodoDetailScreen
import com.example.jetpack_compose_assignment_2.ui.theme.Jetpackcomposeassignment2Theme
import com.example.jetpack_compose_assignment_2.ui.viewmodel.MainViewModel
import com.example.jetpack_compose_assignment_2.ui.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as MyApplication
        val repository = app.todoRepository
        val factory = MainViewModelFactory(repository)

        setContent {
            Jetpackcomposeassignment2Theme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = viewModel(factory = factory)

                // Setup NavHost with routes
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        TodoListScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                    composable("detail/{todoId}") { backStackEntry ->
                        val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
                        if (todoId != null) {
                            TodoDetailScreen(
                                todoId = todoId,
                                viewModel = viewModel,
                                navBackStackEntry = backStackEntry,
                                navController = navController
                            )
                        } else {
                            // Handle null ID case
                        }
                    }
                }
            }
        }
    }
}
