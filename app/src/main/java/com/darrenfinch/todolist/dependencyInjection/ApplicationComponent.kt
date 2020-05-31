package com.darrenfinch.todolist.dependencyInjection

import android.app.Application
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.view.fragments.CompletedTasksFragment
import com.darrenfinch.todolist.view.fragments.IncompleteTasksFragment
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModel
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component()
interface ApplicationComponent
{
    fun inject(repository: TaskRepository) : TaskRepository
    fun inject(viewModel: IncompleteTasksViewModel)
    fun inject(viewModel: CompletedTasksViewModel)
    fun inject(fragment: IncompleteTasksFragment)
    fun inject(fragment: CompletedTasksFragment)
    fun application() : Application
}