package com.darrenfinch.todolist.view.adapters

import android.graphics.Paint
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.TaskItemBinding
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.helpers.ExpandCollapseViewAnimator

class TaskViewHolder(val listener: TaskViewHolder.Listener, itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private var binding = TaskItemBinding.bind(itemView)
    private var isExpanded = false
    private var taskIsComplete = false
    private var taskId = 0

    fun bind(task: Task)
    {
        binding.task = task
        binding.viewHolder = this
        setupUI(task)
    }
    private fun setupUI(task: Task)
    {
        taskId = task.id
        taskIsComplete = task.isComplete

        if(taskIsComplete)
            setTaskDisabledStyle()

        binding.taskCompletedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) listener.onTaskFinished(taskId) else listener.onTaskUnfinished(taskId)
        }
    }
    private fun setTaskDisabledStyle()
    {
        binding.taskNameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun inverseExpandedWithAnimation()
    {
        if (!taskIsComplete)
        {
            if (isExpanded)
                ExpandCollapseViewAnimator.collapse(binding.taskDetails)
            else
                ExpandCollapseViewAnimator.expand(binding.taskDetails)
            isExpanded = !isExpanded
        }
    }
    fun showPopupMenu()
    {
        PopupMenu(itemView.context, binding.viewMoreButton).apply {
            inflate(R.menu.task_view_more_menu)
            setOnMenuItemClickListener { menuItem ->
                onMenuItemClicked(menuItem.itemId)
                true
            }
            show()
        }
    }
    private fun onMenuItemClicked(itemId: Int)
    {
        when(itemId)
        {
            R.id.editTaskMenuItem -> listener.onTaskEdit(taskId)
            R.id.deleteTaskMenuItem -> listener.onTaskDelete(taskId)
        }
    }

    interface Listener
    {
        fun onTaskFinished(taskId: Int)
        fun onTaskUnfinished(taskId: Int)
        fun onTaskEdit(taskId: Int)
        fun onTaskDelete(taskId: Int)
    }
}