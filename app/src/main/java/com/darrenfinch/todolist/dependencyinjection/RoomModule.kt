package com.darrenfinch.todolist.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.TaskDao
import com.darrenfinch.todolist.model.room.TaskDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
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
    fun provideDao() = taskDatabase.taskDao()

    @Singleton
    @Provides
    fun provideRepository(ioScope: CoroutineScope, taskDao: TaskDao) = TaskRepository(ioScope = ioScope, taskDao = taskDao)
}