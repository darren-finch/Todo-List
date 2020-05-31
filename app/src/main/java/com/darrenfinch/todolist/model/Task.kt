package com.darrenfinch.todolist.model

data class Task(val name: String,
                val scheduledDate: Long,
                val estimatedTTC: Int,
                val estimatedTTCUnit: TimeUnit,
                val description: String,
                val isComplete: Boolean = false)