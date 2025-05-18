package com.example.jetpack_compose_assignment_2.ui.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun TodoDetailScreen(
    viewModel: MainViewModel,
    todoId: Int,
    navController: NavController
) {
    var todo by remember { mutableStateOf<Todo?>(null) }

    LaunchedEffect(todoId) {
        todo = viewModel.getTodoById(todoId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Todo Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (todo == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 6.dp,
                    shadowElevation = 8.dp,
                    color =  if (todo!!.completed)
                        Color(0xFF00796B)
                    else
                        Color.Gray
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = todo!!.title,
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                        Spacer(modifier = Modifier.height(16.dp))

                        InfoRow(label = "User ID", value = todo!!.userId.toString())
                        InfoRow(label = "Status", value = if (todo!!.completed) "Completed ✅" else "Pending ⏳")
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.White,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
        )
    }
}
