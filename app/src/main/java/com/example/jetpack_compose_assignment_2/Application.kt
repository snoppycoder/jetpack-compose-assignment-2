package com.example.jetpack_compose_assignment_2
import android.app.Application
import androidx.room.Room
import com.example.jetpack_compose_assignment_2.data.local.TodoDatabase
import com.example.jetpack_compose_assignment_2.data.Network.ApiServiceInterface
import com.example.jetpack_compose_assignment_2.domain.repository.TodoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    lateinit var database: TodoDatabase
    lateinit var apiService: ApiServiceInterface
    lateinit var todoRepository: TodoRepository

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todos"
        ).build()

        apiService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceInterface::class.java)

        todoRepository = TodoRepository(apiService, database.todoDao())

    }
}
