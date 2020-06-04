package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.helpers.ObservableTask

class EditTaskViewModel(private val repository: TaskRepository, application: Application) :
    AndroidViewModel(application) {
    val observableTask = ObservableTask()

    fun getTaskFromRepository(taskId: Int): LiveData<Task> {
        return repository.getTask(taskId)
    }

    fun insertTask(task: Task) {
        repository.insertTask(task)
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
    }
}