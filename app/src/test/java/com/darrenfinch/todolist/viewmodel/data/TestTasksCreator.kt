package com.darrenfinch.todolist.viewmodel.data

import com.darrenfinch.todolist.model.room.TimeUnit
import com.darrenfinch.todolist.model.room.Task
import java.util.*

object TestTasksCreator
{
    fun getTask() : Task
    {
        return Task(
            1,
            "Task 1",
            getCurrentTime(),
            1,
            TimeUnit.defaultUnit,
            "Task 1 Description"
        )
    }
    fun getIncompleteTasks() : List<Task> {
        return listOf(
            Task(
                1,
                "Task 1",
                getCurrentTime(),
                1,
                TimeUnit.defaultUnit,
                "Task 1 Description"
            ),
            Task(
                2,
                "Task 2",
                getCurrentTime(),
                2,
                TimeUnit.defaultUnit,
                "Task 2 Description"
            ),
            Task(
                3,
                "Task 3",
                getCurrentTime(),
                3,
                TimeUnit.defaultUnit,
                "Task 3 Description"
            ),
            Task(
                4,
                "Task 4",
                getCurrentTime(),
                4,
                TimeUnit.defaultUnit,
                "Task 4 Description"
            ),
            Task(
                5,
                "Task 5",
                getCurrentTime(),
                5,
                TimeUnit.defaultUnit,
                "Task 5 Description"
            )
        )
    }
    fun getCompletedTasks() : List<Task> {
        return listOf(
            Task(
                1,
                "Task 1",
                getCurrentTime(),
                1,
                TimeUnit.defaultUnit,
                "Task 1 Description",
                isComplete = true
            ),
            Task(
                2,
                "Task 2",
                getCurrentTime(),
                2,
                TimeUnit.defaultUnit,
                "Task 2 Description",
                isComplete = true
            ),
            Task(
                3,
                "Task 3",
                getCurrentTime(),
                3,
                TimeUnit.defaultUnit,
                "Task 3 Description",
                isComplete = true
            ),
            Task(
                4,
                "Task 4",
                getCurrentTime(),
                4,
                TimeUnit.defaultUnit,
                "Task 4 Description",
                isComplete = true
            ),
            Task(
                5,
                "Task 5",
                getCurrentTime(),
                5,
                TimeUnit.defaultUnit,
                "Task 5 Description",
                isComplete = true
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