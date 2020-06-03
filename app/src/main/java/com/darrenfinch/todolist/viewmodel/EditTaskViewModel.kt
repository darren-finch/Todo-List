package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.Task
import javax.inject.Inject

class EditTaskViewModel(private val repository: TaskRepository, application: Application) : AndroidViewModel(application)
{
    val observableTask = ObservableField<Task>()

    fun getTask(taskId: Int) : LiveData<Task>
    {
        return repository.getTask(taskId)
    }
    fun insertTask(task: Task)
    {
        repository.insertTask(task)
    }
    fun updateTask(task: Task)
    {
        repository.updateTask(task)
    }
}