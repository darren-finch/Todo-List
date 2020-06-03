package com.darrenfinch.todolist.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.darrenfinch.todolist.model.TimeUnit

@Entity(tableName = "tasks")
data class Task(@PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "taskId")
                val id: Int,
                val name: String,
                val scheduledDate: Long,
                val estimatedTTC: Int,
                val estimatedTTCUnit: TimeUnit,
                val description: String,
                val isComplete: Boolean = false)