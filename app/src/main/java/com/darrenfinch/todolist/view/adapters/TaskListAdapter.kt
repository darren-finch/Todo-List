package com.darrenfinch.todolist.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.model.Task

class TaskListAdapter : RecyclerView.Adapter<TaskViewHolder>()
{
    private val allTasks = mutableListOf<Task>()
    fun updateTasks(newTasks: List<Task>)
    {
        allTasks.clear()
        allTasks.addAll(newTasks)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder
    {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))
    }
    override fun getItemCount() = allTasks.size
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int)
    {
        holder.bind(allTasks[position])
    }

}