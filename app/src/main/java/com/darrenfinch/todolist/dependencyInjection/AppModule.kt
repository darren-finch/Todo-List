package com.darrenfinch.todolist.dependencyInjection

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application)
{
    @Provides
    fun provideApplication() = application
}