package com.darrenfinch.todolist.view.helpers

import android.content.Context
import android.widget.Toast

object ToastHelper
{
    fun makeShortToast(context: Context, message: String)
    {
        makeToast(context, message, Toast.LENGTH_SHORT)
    }
    fun makeLongToast(context: Context, message: String)
    {
        makeToast(context, message, Toast.LENGTH_LONG)
    }
    private fun makeToast(context: Context, message: String, length: Int)
    {
        Toast.makeText(context, message, length).show()
    }
}