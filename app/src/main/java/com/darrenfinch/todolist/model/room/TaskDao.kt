package com.darrenfinch.todolist.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE isComplete = 1 ORDER BY scheduledDate DESC")
    fun getCompletedTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE isComplete = 0 ORDER BY scheduledDate DESC")
    fun getIncompleteTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    fun getTask(taskId: Int): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM tasks WHERE taskId = :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("UPDATE tasks SET isComplete = 1, dateOfCompletion = :dateOfCompletion WHERE taskId = :taskId")
    suspend fun completeTask(taskId: Int, dateOfCompletion: Long)

    @Query("UPDATE tasks SET isComplete = 0 WHERE taskId = :taskId")
    suspend fun uncompleteTask(taskId: Int)
}
