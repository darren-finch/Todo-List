package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentCompletedTasksBinding
import com.darrenfinch.todolist.dependencyinjection.AppModule
import com.darrenfinch.todolist.dependencyinjection.DaggerApplicationComponent
import com.darrenfinch.todolist.dependencyinjection.RoomModule
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.adapters.TaskListAdapter
import com.darrenfinch.todolist.view.adapters.TaskViewHolder
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModel
import com.darrenfinch.todolist.viewmodel.CompletedTasksViewModelFactory
import javax.inject.Inject

class CompletedTasksFragment : Fragment()
{
    @Inject
    lateinit var completedTasksViewModelFactory: CompletedTasksViewModelFactory
    private lateinit var completedTasksViewModel: CompletedTasksViewModel

    private val taskListObserver = Observer<List<Task>>
    { newTasks ->
        adapter.updateTasks(newTasks)
        binding.noCompleteTasksTextView.visibility = if(newTasks.isNotEmpty()) View.GONE else View.VISIBLE
    }
    private val taskViewHolderListener = object : TaskViewHolder.Listener
    {
        override fun onTaskUnfinished(taskId: Int)
        {
            completedTasksViewModel.uncompleteTask(taskId)
            Toast.makeText(context, getString(R.string.marked_task_as_incomplete), Toast.LENGTH_SHORT).show()
        }
        override fun onTaskFinished(taskId: Int) {}
        override fun onTaskEdit(taskId: Int) {}
        override fun onTaskDelete(taskId: Int) {}
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
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        setupDependencies()
        setupViewModel()
    }
    //Observing the view model is necessary to do in onResume because incomplete and completed tasks can change rapidly.
    override fun onResume()
    {
        super.onResume()
        observeViewModel()
    }

    //DON'T USE ANY FIELDS WITH @INJECT BEFORE THIS METHOD IS CALLED.
    private fun setupDependencies()
    {
        DaggerApplicationComponent.builder()
            .appModule(
                AppModule(
                    requireActivity().application
                )
            )
            .roomModule(
                RoomModule(
                    requireActivity().application
                )
            )
            .build()
            .inject(this)
    }
    private fun setupViewModel()
    {
        completedTasksViewModel = ViewModelProvider(viewModelStore, completedTasksViewModelFactory).get(CompletedTasksViewModel::class.java)
    }
    private fun observeViewModel()
    {
        completedTasksViewModel.getTasks().observe(viewLifecycleOwner, taskListObserver)
    }
}
