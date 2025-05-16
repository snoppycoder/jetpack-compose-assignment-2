package com.example.jetpack_compose_assignment_2.data.Network

import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("todos")
    suspend fun getTodos(): List<TodoDto>


}