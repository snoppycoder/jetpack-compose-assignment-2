package com.example.jetpack_compose_assignment_2.DI

import android.content.Context
import androidx.room.Room
import com.example.jetpack_compose_assignment_2.data.local.TodoDatabase
import com.example.jetpack_compose_assignment_2.data.local.TodoDao
import com.example.jetpack_compose_assignment_2.data.Network.ApiServiceInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private var database: TodoDatabase? = null

    fun provideDatabase(context: Context): TodoDatabase {
        return database ?: Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo_db"
        ).build().also { database = it }
    }

    fun provideTodoDao(context: Context): TodoDao = provideDatabase(context).todoDao()

    fun provideApiService(): ApiServiceInterface {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceInterface::class.java)
    }
}
