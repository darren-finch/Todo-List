package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentCompletedTasksBinding
import com.darrenfinch.todolist.dependencyInjection.dagger2.AppModule
import com.darrenfinch.todolist.dependencyInjection.dagger2.DaggerApplicationComponent
import com.darrenfinch.todolist.dependencyInjection.dagger2.RoomModule
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.adapters.TaskListAdapter
import com.darrenfinch.todolist.view.adapters.TaskViewHolder
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.view.helpers.ExampleTasksCreator
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
    }
    private val taskViewHolderListener = object : TaskViewHolder.Listener
    {
        override fun onTaskUnfinished(taskId: Int)
        {
            completedTasksViewModel.uncompleteTask(taskId)
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
    override fun onStart()
    {
        super.onStart()
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
