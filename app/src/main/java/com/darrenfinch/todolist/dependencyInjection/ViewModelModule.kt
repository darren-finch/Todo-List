package com.darrenfinch.todolist.dependencyInjection

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.viewmodel.AndroidViewModelFactoryWithRepository
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModel
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(private val viewModelFactory: AndroidViewModelFactoryWithRepository, private val viewModelStoreOwner: ViewModelStoreOwner)
{
    @Provides
    fun provideIncompleteTasksViewModel(repository: TaskRepository, application: Application) : IncompleteTasksViewModel
    {
        return ViewModelProvider(viewModelStoreOwner, AndroidViewModelFactoryWithRepository(repository, application)).get(IncompleteTasksViewModel::class.java)
    }
    @Provides
    fun provideCompleteTasksViewModel(repository: TaskRepository, application: Application) : CompletedTasksViewModel
    {
        return ViewModelProvider(viewModelStoreOwner, AndroidViewModelFactoryWithRepository(repository, application)).get(CompletedTasksViewModel::class.java)
    }
}