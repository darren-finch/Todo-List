package com.darrenfinch.todolist.dependencyInjection.dagger2

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application)
{
    @Provides
    fun provideApplication() = application
}