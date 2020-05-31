package com.darrenfinch.todolist.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.darrenfinch.todolist.model.TimeUnit

@Entity(tableName = "tasks")
data class Task(@PrimaryKey(autoGenerate = true)
                val taskId: Int, //I've prefixed this with "task" to keep my sql queries sane. The other variables should not be prefixed with "task".
                val name: String,
                val scheduledDate: Long,
                val estimatedTTC: Int,
                val estimatedTTCUnit: TimeUnit,
                val description: String,
                val isComplete: Boolean = false)