package com.example.courselist.data

val sampleCourses = listOf(
    Course(
        title = "Introduction to Computer Science",
        code = "CS101",
        creditHours = 3,
        description = "Covers basics of computer systems, programming, and problem-solving.",
        prerequisites = "None"
    ),
    Course(
        title = "Data Structures",
        code = "CS201",
        creditHours = 4,
        description = "In-depth look at data structures like lists, trees, graphs, and their algorithms.",
        prerequisites = "CS101"
    ),
    Course(
        title = "Mobile App Development",
        code = "CS310",
        creditHours = 3,
        description = "Covers Android app development using Kotlin and Jetpack Compose.",
        prerequisites = "CS201"
    )
)
