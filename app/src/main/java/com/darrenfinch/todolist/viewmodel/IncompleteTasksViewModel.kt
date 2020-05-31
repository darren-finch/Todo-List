package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.darrenfinch.todolist.model.TaskRepository
import javax.inject.Inject

class IncompleteTasksViewModel
@Inject
constructor(private val repository: TaskRepository, application: Application) : AndroidViewModel(application)
{

}