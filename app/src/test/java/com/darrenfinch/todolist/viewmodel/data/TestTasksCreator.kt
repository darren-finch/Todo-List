package com.darrenfinch.todolist.viewmodel.data

import com.darrenfinch.todolist.model.TimeUnit
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.helpers.ExampleTasksCreator

object TestTasksCreator
{
    fun getSampleTask() : Task
    {
        return Task(
            1,
            "Task 1",
            ExampleTasksCreator.getCurrentTime(),
            1,
            TimeUnit.HRS,
            "Task 1 Description"
        )
    }
    fun getSampleIncompleteTasks() : List<Task> {
        return listOf(
            Task(
                1,
                "Task 1",
                ExampleTasksCreator.getCurrentTime(),
                1,
                TimeUnit.HRS,
                "Task 1 Description"
            ),
            Task(
                2,
                "Task 2",
                ExampleTasksCreator.getCurrentTime(),
                2,
                TimeUnit.HRS,
                "Task 2 Description"
            ),
            Task(
                3,
                "Task 3",
                ExampleTasksCreator.getCurrentTime(),
                3,
                TimeUnit.HRS,
                "Task 3 Description"
            ),
            Task(
                4,
                "Task 4",
                ExampleTasksCreator.getCurrentTime(),
                4,
                TimeUnit.HRS,
                "Task 4 Description"
            ),
            Task(
                5,
                "Task 5",
                ExampleTasksCreator.getCurrentTime(),
                5,
                TimeUnit.HRS,
                "Task 5 Description"
            )
        )
    }
}