package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentIncompleteTasksBinding
import com.darrenfinch.todolist.dependencyInjection.dagger2.AppModule
import com.darrenfinch.todolist.dependencyInjection.dagger2.DaggerApplicationComponent
import com.darrenfinch.todolist.dependencyInjection.dagger2.RoomModule
import com.darrenfinch.todolist.dependencyInjection.dagger2.ViewModelFactoryModule
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.adapters.TaskListAdapter
import com.darrenfinch.todolist.view.adapters.TaskViewHolder
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.view.helpers.ToastHelper
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModel
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModelFactory
import javax.inject.Inject

const val DEFAULT_EDIT_FRAGMENT_TASK_ID = -1

class IncompleteTasksFragment : Fragment()
{
    @Inject
    lateinit var incompleteTasksViewModelFactory: IncompleteTasksViewModelFactory
    private lateinit var incompleteTasksViewModel: IncompleteTasksViewModel
    @Inject
    lateinit var toastHelper: ToastHelper

    private val tasksListObserver = Observer<List<Task>>
    { newTasks ->
        taskListAdapter.updateTasks(newTasks)
    }
    private val taskViewHolderListener = object : TaskViewHolder.Listener
    {
        override fun onTaskFinished(taskId: Int)
        {
            incompleteTasksViewModel.completeTask(taskId)
        }
        override fun onTaskUnfinished(taskId: Int)
        {
            TODO("Not yet implemented")
        }
        override fun onTaskEdit(taskId: Int)
        {
            navigateToEditTask(taskId)
        }
        override fun onTaskDelete(taskId: Int)
        {
            TODO("Not yet implemented")
        }
    }
    private val taskListAdapter = TaskListAdapter(taskViewHolderListener)

    private lateinit var binding: FragmentIncompleteTasksBinding

    companion object
    {
        fun newInstance() = IncompleteTasksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_incomplete_tasks, container, false)
        setupUI()
        return binding.root
    }
    private fun setupUI()
    {
        binding.apply {
            tasksRecyclerView.adapter = taskListAdapter
            tasksRecyclerView.layoutManager = LinearLayoutManager(context)
            tasksRecyclerView.addItemDecoration(MarginItemDecoration(16))

            addNewTaskFAB.setOnClickListener {
                navigateToEditTask()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        setupDependencies()
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

        incompleteTasksViewModel = ViewModelProvider(viewModelStore, incompleteTasksViewModelFactory).get(IncompleteTasksViewModel::class.java)
    }
    private fun observeViewModel()
    {
        incompleteTasksViewModel.getTasks().observe(viewLifecycleOwner, tasksListObserver)
    }

    fun navigateToEditTask(taskId: Int = DEFAULT_EDIT_FRAGMENT_TASK_ID)
    {
        view?.let {
            val directions = AllTasksViewPagerFragmentDirections.actionAllTasksViewPagerFragmentToEditTasksFragment(taskId)
            findNavController().navigate(directions)
        }
    }
}
