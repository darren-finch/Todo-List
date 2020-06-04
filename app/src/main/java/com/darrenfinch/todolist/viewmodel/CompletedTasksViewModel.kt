package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.darrenfinch.todolist.model.TaskRepository

class CompletedTasksViewModel(private val repository: TaskRepository, application: Application) :
    AndroidViewModel(application) {
    fun getTasks() = repository.getCompletedTasks()
    fun uncompleteTask(taskId: Int) = repository.uncompleteTask(taskId)
}