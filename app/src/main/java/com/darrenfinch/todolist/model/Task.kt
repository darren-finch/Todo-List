package com.darrenfinch.todolist.model

data class Task(val taskName: String,
                val scheduledDate: Long,
                val estimatedTTC: Int,
                val estimatedTTCUnit: TimeUnit,
                val taskDescription: String)