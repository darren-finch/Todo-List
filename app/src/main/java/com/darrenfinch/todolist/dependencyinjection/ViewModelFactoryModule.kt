package com.darrenfinch.todolist.dependencyinjection

import android.app.Application
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModelFactory
import com.darrenfinch.todolist.viewmodel.EditTaskViewModelFactory
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideIncompleteTasksViewModel(
        repository: TaskRepository,
        application: Application
    ): IncompleteTasksViewModelFactory {
        return IncompleteTasksViewModelFactory(repository, application)
    }

    @Provides
    @Singleton
    fun provideCompleteTasksViewModel(
        repository: TaskRepository,
        application: Application
    ): CompletedTasksViewModelFactory {
        return CompletedTasksViewModelFactory(repository, application)
    }

    @Provides
    @Singleton
    fun provideEditTaskViewModel(
        repository: TaskRepository,
        application: Application
    ): EditTaskViewModelFactory {
        return EditTaskViewModelFactory(repository, application)
    }
}