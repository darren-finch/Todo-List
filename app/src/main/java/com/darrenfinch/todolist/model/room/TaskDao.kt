package com.darrenfinch.todolist.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TaskDao
{
    @Query("SELECT * FROM tasks WHERE isComplete = 1")
    fun getCompletedTasks() : Observable<List<Task>>

    @Query("SELECT * FROM tasks WHERE isComplete = 0")
    fun getIncompleteTasks() : Observable<List<Task>>

    @Query("SELECT 1 FROM tasks WHERE taskId = :taskId")
    fun getTask(taskId: Int) : Single<Task>

    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM tasks WHERE taskId = :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("UPDATE tasks SET isComplete = 1 WHERE taskId = :taskId")
    suspend fun completeTask(taskId: Int)

    @Query("UPDATE tasks SET isComplete = 0 WHERE taskId = :taskId")
    suspend fun uncompleteTask(taskId: Int)
}