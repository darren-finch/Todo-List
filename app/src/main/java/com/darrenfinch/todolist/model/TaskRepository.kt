package com.darrenfinch.todolist.model

import com.darrenfinch.todolist.model.room.TaskDao
import com.darrenfinch.todolist.model.room.TaskDatabase
import javax.inject.Inject

class TaskRepository
@Inject
constructor(private val taskDao: TaskDao)
{

}