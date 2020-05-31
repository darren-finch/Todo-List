package com.darrenfinch.todolist.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.TaskDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application)
{
    private val taskDatabase = Room.databaseBuilder(application.applicationContext, TaskDatabase::class.java, "TaskDatabase")
        .fallbackToDestructiveMigration() //TODO: REMOVE IN PRODUCTION
        .build()

    @Singleton
    @Provides
    fun provideDatabase() = taskDatabase

    @Singleton
    @Provides
    fun provideRepository(database: TaskDatabase) = TaskRepository(database.taskDao())
}