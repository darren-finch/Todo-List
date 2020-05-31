package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentIncompleteTasksBinding
import com.darrenfinch.todolist.view.adapters.TaskListAdapter
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.view.helpers.ExampleTasksCreator

class IncompleteTasksFragment : Fragment()
{
    private val adapter = TaskListAdapter()
    private lateinit var binding: FragmentIncompleteTasksBinding

    companion object
    {
        fun newInstance() = IncompleteTasksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentIncompleteTasksBinding>(inflater, R.layout.fragment_incomplete_tasks, container, false).apply {
            tasksRecyclerView.adapter = adapter
            tasksRecyclerView.layoutManager = LinearLayoutManager(context)
            tasksRecyclerView.addItemDecoration(MarginItemDecoration(16))
            adapter.updateTasks(ExampleTasksCreator.getSampleUncompletedTasks())
        }
        return binding.root
    }
}
