package com.darrenfinch.todolist.view.adapters

import android.graphics.Paint
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.TaskItemBinding
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.helpers.DatabindingUtil
import com.darrenfinch.todolist.view.helpers.ExpandCollapseViewAnimator

class TaskViewHolder(val listener: Listener, itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private var binding = TaskItemBinding.bind(itemView)
    private var isExpanded = false
    private lateinit var task: Task

    fun bind(task: Task)
    {
        this.task = task
        binding.task = task
        binding.viewHolder = this
        setupUI()
    }
    private fun setupUI()
    {
        if(task.isComplete)
            setTaskCompletedStyle()

        binding.taskCompletedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) listener.onTaskFinished(task.id) else listener.onTaskUnfinished(task.id)
        }
    }
    private fun setTaskCompletedStyle()
    {
        binding.taskNameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun getTimeToCompleteWithUnit() = DatabindingUtil.putTogetherTimeAndUnit(task.estimatedTTC, task.estimatedTTCUnit)
    fun getScheduledDateAsString() = DatabindingUtil.dateStringFromLong(task.scheduledDate)
    fun getDateOfCompletionAsString() = DatabindingUtil.dateStringFromLong(task.dateOfCompletion)
    fun inverseExpandedWithAnimation()
    {
        if (!task.isComplete)
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
            R.id.editTaskMenuItem -> listener.onTaskEdit(task.id)
            R.id.deleteTaskMenuItem -> listener.onTaskDelete(task.id)
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