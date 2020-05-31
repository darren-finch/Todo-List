package com.darrenfinch.todolist.view.helpers

import com.darrenfinch.todolist.model.Task
import com.darrenfinch.todolist.model.TimeUnit
import java.util.*

object Utils
{
    fun getSampleUncompletedTasks() : List<Task>
    {
        return listOf(
            Task("Task 1", getCurrentTime(), 1, TimeUnit.HRS, "Task 1 Description"),
            Task("Task 2", getCurrentTime(), 2, TimeUnit.HRS, "Task 2 Description"),
            Task("Task 3", getCurrentTime(), 3, TimeUnit.HRS, "Task 3 Description"),
            Task("Task 4", getCurrentTime(), 4, TimeUnit.HRS, "Task 4 Description"),
            Task("Task 5", getCurrentTime(), 5, TimeUnit.HRS, "Task 5 Description"))
    }
    fun getSampleCompletedTasks()  : List<Task>
    {
        return listOf(
            Task("Task 1", getCurrentTime(), 1, TimeUnit.HRS, "Task 1 Description", true),
            Task("Task 2", getCurrentTime(), 2, TimeUnit.HRS, "Task 2 Description", true),
            Task("Task 3", getCurrentTime(), 3, TimeUnit.HRS, "Task 3 Description", true),
            Task("Task 4", getCurrentTime(), 4, TimeUnit.HRS, "Task 4 Description", true),
            Task("Task 5", getCurrentTime(), 5, TimeUnit.HRS, "Task 5 Description", true))
    }
    fun getCurrentTime() = GregorianCalendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}