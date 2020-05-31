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
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.view.helpers.Utils
import kotlinx.android.synthetic.main.fragment_completed_tasks.*

class CompletedTasksFragment : Fragment()
{
    private val adapter = TaskListAdapter()
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
            adapter.updateTasks(Utils.getSampleCompletedTasks())
        }
        return binding.root
    }
}
