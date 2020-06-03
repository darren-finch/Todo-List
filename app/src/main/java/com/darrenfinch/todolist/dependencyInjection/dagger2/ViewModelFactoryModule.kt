package com.darrenfinch.todolist.dependencyInjection.dagger2

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.viewmodel.*
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule
{
    @Provides
    fun provideIncompleteTasksViewModel(repository: TaskRepository, application: Application) : IncompleteTasksViewModelFactory
    {
        return IncompleteTasksViewModelFactory(repository, application)
    }
    @Provides
    fun provideCompleteTasksViewModel(repository: TaskRepository, application: Application) : CompletedTasksViewModelFactory
    {
        return CompletedTasksViewModelFactory(repository, application)
    }
    @Provides
    fun provideEditTaskViewModel(repository: TaskRepository, application: Application) : EditTaskViewModelFactory
    {
        return EditTaskViewModelFactory(repository, application)
    }
}