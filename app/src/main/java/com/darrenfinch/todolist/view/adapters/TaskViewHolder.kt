package com.darrenfinch.todolist.view.adapters

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import com.darrenfinch.todolist.model.Task
import com.darrenfinch.todolist.view.helpers.AnimationUtils

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private var binding = MealItemBinding.bind(itemView)
    var expanded = ObservableBoolean(false)
    fun bind(task: Task)
    {
        binding.meal = meal
        binding.viewHolder = this
    }
    fun inverseExpanded()
    {
        if(expanded.get()) AnimationUtils.collapse(binding) else AnimationUtils.expand(binding)
        expanded.set(!expanded.get())
    }
}