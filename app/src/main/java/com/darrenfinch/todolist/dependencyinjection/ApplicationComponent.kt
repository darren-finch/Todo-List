package com.darrenfinch.todolist.dependencyinjection

import android.app.Application
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.view.fragments.CompletedTasksFragment
import com.darrenfinch.todolist.view.fragments.EditTaskFragment
import com.darrenfinch.todolist.view.fragments.IncompleteTasksFragment
import com.darrenfinch.todolist.viewmodel.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RoomModule::class, ViewModelFactoryModule::class, CoroutineModule::class])
interface ApplicationComponent
{
    fun inject(repository: TaskRepository) : TaskRepository
    fun inject(viewModelFactory: IncompleteTasksViewModelFactory)
    fun inject(viewModelFactory: CompletedTasksViewModelFactory)
    fun inject(viewModelFactory: EditTaskViewModelFactory)
    fun inject(fragment: IncompleteTasksFragment)
    fun inject(fragment: CompletedTasksFragment)
    fun inject(fragment: EditTaskFragment)
    fun application() : Application
}