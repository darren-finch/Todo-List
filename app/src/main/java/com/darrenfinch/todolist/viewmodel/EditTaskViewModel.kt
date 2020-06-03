package com.darrenfinch.todolist.viewmodel

import androidx.lifecycle.LiveData
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.Task
import javax.inject.Inject

class EditTaskViewModel
@Inject
constructor(private val repository: TaskRepository)
{
    fun getTask(taskId: Int): LiveData<Task>
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