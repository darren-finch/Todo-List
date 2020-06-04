package com.darrenfinch.todolist.viewmodel.data

import com.darrenfinch.todolist.model.room.TimeUnit
import com.darrenfinch.todolist.model.room.Task
import java.util.*

object TestTasksCreator {
    fun getTask(): Task {
        return Task(
            id = 1,
            name = "Task 1",
            scheduledDate = getCurrentTime(),
            dateOfCompletion = getCurrentTime(),
            estimatedTTC = 1,
            estimatedTTCUnit = TimeUnit.defaultUnit,
            description = "Task 1 Description"
        )
    }

    fun getIncompleteTasks(): List<Task> {
        return listOf(
            Task(
                id = 1,
                name = "Task 1",
                scheduledDate = getCurrentTime(),
                dateOfCompletion = getCurrentTime(),
                estimatedTTC = 1,
                estimatedTTCUnit = TimeUnit.defaultUnit,
                description = "Task 1 Description"
            ),
            Task(
                id = 2,
                name = "Task 2",
                scheduledDate = getCurrentTime(),
                dateOfCompletion = getCurrentTime(),
                estimatedTTC = 1,
                estimatedTTCUnit = TimeUnit.defaultUnit,
                description = "Task 2 Description"
            )
        )
    }

    fun getCompletedTasks(): List<Task> {
        return listOf(
            Task(
                id = 1,
                name = "Task 1",
                scheduledDate = getCurrentTime(),
                dateOfCompletion = getCurrentTime(),
                estimatedTTC = 1,
                estimatedTTCUnit = TimeUnit.defaultUnit,
                description = "Task 1 Description",
                isComplete = true
            ),
            Task(
                id = 2,
                name = "Task 2",
                scheduledDate = getCurrentTime(),
                dateOfCompletion = getCurrentTime(),
                estimatedTTC = 1,
                estimatedTTCUnit = TimeUnit.defaultUnit,
                description = "Task 2 Description",
                isComplete = true
            )
        )
    }
    private fun getCurrentTime() = System.currentTimeMillis()
}