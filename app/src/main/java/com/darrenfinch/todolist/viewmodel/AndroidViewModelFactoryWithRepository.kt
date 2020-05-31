package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darrenfinch.todolist.model.TaskRepository

@Suppress("UNCHECKED_CAST")
class AndroidViewModelFactoryWithRepository(private val repository: TaskRepository, private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application)
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return (if(modelClass.isInstance(IncompleteTasksViewModel::class))
            IncompleteTasksViewModel(repository, application)
        else
            CompletedTasksViewModel(repository, application)) as T
    }
}