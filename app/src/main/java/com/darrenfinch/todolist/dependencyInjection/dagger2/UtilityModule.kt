package com.darrenfinch.todolist.dependencyInjection.dagger2

import com.darrenfinch.todolist.view.helpers.ExpandCollapseViewAnimator
import com.darrenfinch.todolist.view.helpers.ToastHelper
import dagger.Module
import dagger.Provides

@Module
class UtilityModule
{
    @Provides
    fun provideToastHelper() = ToastHelper()
    @Provides
    fun provideExpandCollapseViewAnimator() = ExpandCollapseViewAnimator()
}