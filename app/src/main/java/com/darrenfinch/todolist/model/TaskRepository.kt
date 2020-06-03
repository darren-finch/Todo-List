package com.darrenfinch.todolist.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.model.room.TaskDao
import com.darrenfinch.todolist.view.helpers.ExampleTasksCreator
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TaskRepository
@Inject
constructor(private val taskDao: TaskDao)
{
    fun getCompletedTasks() = taskDao.getCompletedTasks()
    fun getIncompleteTasks() = taskDao.getIncompleteTasks()
    fun getTask(taskId: Int) = taskDao.getTask(taskId)
    fun insertTask(task: Task)
    {
        runBlocking {
            taskDao.insertTask(task)
        }
    }
    fun updateTask(task: Task)
    {
        runBlocking {
            taskDao.updateTask(task)
        }
    }
    fun deleteTask(taskId: Int)
    {
        runBlocking {
            taskDao.deleteTask(taskId)
        }
    }
    fun completeTask(taskId: Int)
    {
        runBlocking {
            taskDao.completeTask(taskId)
        }
    }
    fun uncompleteTask(taskId: Int)
    {
        runBlocking {
            taskDao.uncompleteTask(taskId)
        }
    }
}