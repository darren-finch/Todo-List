package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentUncompletedTasksBinding
import com.darrenfinch.todolist.view.adapters.TaskListAdapter
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.view.helpers.Utils

class UncompletedTasksFragment : Fragment()
{
    private val adapter = TaskListAdapter()
    private lateinit var binding: FragmentUncompletedTasksBinding

    companion object
    {
        fun newInstance() = UncompletedTasksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentUncompletedTasksBinding>(inflater, R.layout.fragment_uncompleted_tasks, container, false).apply {
            tasksRecyclerView.adapter = adapter
            tasksRecyclerView.layoutManager = LinearLayoutManager(context)
            tasksRecyclerView.addItemDecoration(MarginItemDecoration(16))
            adapter.updateTasks(Utils.getSampleUncompletedTasks())
        }
        return binding.root
    }
}