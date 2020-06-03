package com.darrenfinch.todolist.dependencyInjection.dagger2

import android.app.Application
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.view.fragments.CompletedTasksFragment
import com.darrenfinch.todolist.view.fragments.IncompleteTasksFragment
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModel
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class, ViewModelModule::class, UtilityModule::class])
interface ApplicationComponent
{
    fun inject(repository: TaskRepository) : TaskRepository
    fun inject(viewModel: IncompleteTasksViewModel)
    fun inject(viewModel: CompletedTasksViewModel)
    fun inject(fragment: IncompleteTasksFragment)
    fun inject(fragment: CompletedTasksFragment)
    fun application() : Application
}