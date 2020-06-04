package com.darrenfinch.todolist.dependencyinjection

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class CoroutineModule {
    @Provides
    @Singleton
    fun provideIOScope() = CoroutineScope(Dispatchers.IO)
}