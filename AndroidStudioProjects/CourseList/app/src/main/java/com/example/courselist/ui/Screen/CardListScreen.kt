package com.example.courselist.ui.Screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import com.example.courselist.data.Course
import com.example.courselist.ui.Component.CourseCard

@Composable
fun CourseList(courses: List<Course>, innerPadding: PaddingValues) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
        items(courses) { course ->
            CourseCard(
                courseTitle = course.title,
                courseCode = course.code,
                creditHours = course.creditHours,
                description = course.description,
                prerequisites = course.prerequisites
            )
        }
    }
}
