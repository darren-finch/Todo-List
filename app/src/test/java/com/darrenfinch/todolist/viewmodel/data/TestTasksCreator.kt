package com.darrenfinch.todolist.viewmodel.data

import com.darrenfinch.todolist.model.TimeUnit
import com.darrenfinch.todolist.model.room.Task
import java.util.*

object TestTasksCreator
{
    fun getSampleTask() : Task
    {
        return Task(
            1,
            "Task 1",
            getCurrentTime(),
            1,
            TimeUnit.HR,
            "Task 1 Description"
        )
    }
    fun getSampleIncompleteTasks() : List<Task> {
        return listOf(
            Task(
                1,
                "Task 1",
                getCurrentTime(),
                1,
                TimeUnit.HR,
                "Task 1 Description"
            ),
            Task(
                2,
                "Task 2",
                getCurrentTime(),
                2,
                TimeUnit.HR,
                "Task 2 Description"
            ),
            Task(
                3,
                "Task 3",
                getCurrentTime(),
                3,
                TimeUnit.HR,
                "Task 3 Description"
            ),
            Task(
                4,
                "Task 4",
                getCurrentTime(),
                4,
                TimeUnit.HR,
                "Task 4 Description"
            ),
            Task(
                5,
                "Task 5",
                getCurrentTime(),
                5,
                TimeUnit.HR,
                "Task 5 Description"
            )
        )
    }
    private fun getCurrentTime() = GregorianCalendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}