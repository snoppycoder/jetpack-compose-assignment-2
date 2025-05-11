package com.example.courselist.ui.Component
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(
    courseTitle: String,
    courseCode: String,
    creditHours: Int,
    description: String,
    prerequisites: String
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = courseTitle,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = "Code: $courseCode", fontSize = 14.sp)
            Text(text = "Credit Hours: $creditHours", fontSize = 14.sp)

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                // for visibility purpose
                Text(
                    text = "Description: $description",
                    fontSize = 14.sp
                )
                Text(
                    text = "Prerequisites: $prerequisites",
                    fontSize = 14.sp
                )
            }
        }
    }
}
