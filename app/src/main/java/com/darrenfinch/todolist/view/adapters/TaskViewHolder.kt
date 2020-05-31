package com.darrenfinch.todolist.view.adapters

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.darrenfinch.todolist.databinding.TaskItemBinding
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.helpers.ExpandCollapseViewAnimator

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private var binding = TaskItemBinding.bind(itemView)
    private var isExpanded = false
    private var taskIsComplete = false

    fun bind(task: Task)
    {
        binding.task = task
        binding.viewHolder = this

        taskIsComplete = task.isComplete
        if(taskIsComplete)
            setTaskDisabledStyle()
    }
    fun inverseExpandedWithAnimation()
    {
        if(!taskIsComplete)
        {
            if(isExpanded)
                ExpandCollapseViewAnimator.collapse(binding.taskDetails)
            else
                ExpandCollapseViewAnimator.expand(binding.taskDetails)
            isExpanded = !isExpanded
        }
    }
    private fun setTaskDisabledStyle()
    {
        binding.taskNameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}