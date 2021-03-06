package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.darrenfinch.todolist.model.TaskRepository

class IncompleteTasksViewModel(private val repository: TaskRepository, application: Application) :
    AndroidViewModel(application) {
    fun getTasks() = repository.getIncompleteTasks()
    fun completeTask(taskId: Int, dateOfCompletion: Long) =
        repository.completeTask(taskId, dateOfCompletion)

    fun deleteTask(taskId: Int) = repository.deleteTask(taskId)
}