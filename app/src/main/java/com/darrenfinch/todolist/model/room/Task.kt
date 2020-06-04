package com.darrenfinch.todolist.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(@PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "taskId")
                val id: Int = 0,
                val name: String,
                val scheduledDate: Long,
                val dateOfCompletion: Long,
                val estimatedTTC: Int,
                val estimatedTTCUnit: TimeUnit,
                val description: String,
                val isComplete: Boolean = false)