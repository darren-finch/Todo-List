package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darrenfinch.todolist.model.TaskRepository

@Suppress("UNCHECKED_CAST")
class EditTaskViewModelFactory(
    private val repository: TaskRepository,
    private val application: Application
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditTaskViewModel(repository, application) as T
    }
}