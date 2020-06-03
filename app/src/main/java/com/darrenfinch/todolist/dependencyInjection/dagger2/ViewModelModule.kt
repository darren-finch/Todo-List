package com.darrenfinch.todolist.dependencyInjection.dagger2

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModelFactory
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModel
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModelFactory
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(private val viewModelStore: ViewModelStore)
{
    @Provides
    fun provideIncompleteTasksViewModel(repository: TaskRepository, application: Application) : IncompleteTasksViewModel
    {
        return ViewModelProvider(viewModelStore, IncompleteTasksViewModelFactory(repository, application)).get(IncompleteTasksViewModel::class.java)
    }
    @Provides
    fun provideCompleteTasksViewModel(repository: TaskRepository, application: Application) : CompletedTasksViewModel
    {
        return ViewModelProvider(viewModelStore, CompletedTasksViewModelFactory(repository, application)).get(CompletedTasksViewModel::class.java)
    }
}