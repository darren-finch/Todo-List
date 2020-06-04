package com.darrenfinch.todolist.model

import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.model.room.TaskDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskRepository
@Inject
constructor(
    private val ioScope: CoroutineScope,
    private val taskDao: TaskDao)
{
    fun getCompletedTasks() = taskDao.getCompletedTasks()
    fun getIncompleteTasks() = taskDao.getIncompleteTasks()
    fun getTask(taskId: Int) = taskDao.getTask(taskId)
    fun insertTask(task: Task)
    {
        ioScope.launch {
            taskDao.insertTask(task)
        }
    }
    fun updateTask(task: Task)
    {
        ioScope.launch {
            taskDao.updateTask(task)
        }
    }
    fun deleteTask(taskId: Int)
    {
        ioScope.launch {
            taskDao.deleteTask(taskId)
        }
    }
    fun completeTask(taskId: Int, dateOfCompletion: Long)
    {
        ioScope.launch {
            taskDao.completeTask(taskId, dateOfCompletion)
        }
    }
    fun uncompleteTask(taskId: Int)
    {
        ioScope.launch {
            taskDao.uncompleteTask(taskId)
        }
    }
}