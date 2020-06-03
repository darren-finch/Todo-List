package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentCompletedTasksBinding
import com.darrenfinch.todolist.view.adapters.TaskListAdapter
import com.darrenfinch.todolist.view.adapters.TaskViewHolder
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.view.helpers.ExampleTasksCreator

class CompletedTasksFragment : Fragment()
{
    private val taskViewHolderListener = object : TaskViewHolder.Listener
    {
        override fun onTaskFinished(taskId: Int)
        {
        }
        override fun onTaskUnfinished(taskId: Int)
        {
        }
        override fun onTaskEdit(taskId: Int)
        {
        }
        override fun onTaskDelete(taskId: Int)
        {
        }
    }

    private val adapter = TaskListAdapter(taskViewHolderListener)
    private lateinit var binding: FragmentCompletedTasksBinding

    companion object
    {
        fun newInstance() = CompletedTasksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentCompletedTasksBinding>(inflater, R.layout.fragment_completed_tasks, container, false).apply {
            tasksRecyclerView.adapter = adapter
            tasksRecyclerView.layoutManager = LinearLayoutManager(context)
            tasksRecyclerView.addItemDecoration(MarginItemDecoration(16))

            adapter.updateTasks(ExampleTasksCreator.getSampleCompletedTasks())
        }
        return binding.root
    }
}
