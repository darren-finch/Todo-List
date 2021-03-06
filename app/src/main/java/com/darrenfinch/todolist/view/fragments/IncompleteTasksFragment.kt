package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentIncompleteTasksBinding
import com.darrenfinch.todolist.dependencyinjection.AppModule
import com.darrenfinch.todolist.dependencyinjection.DaggerApplicationComponent
import com.darrenfinch.todolist.dependencyinjection.RoomModule
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.view.adapters.TaskListAdapter
import com.darrenfinch.todolist.view.adapters.TaskViewHolder
import com.darrenfinch.todolist.view.helpers.DEFAULT_EDIT_FRAGMENT_TASK_ID
import com.darrenfinch.todolist.view.helpers.MarginItemDecoration
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModel
import com.darrenfinch.todolist.viewmodel.IncompleteTasksViewModelFactory
import javax.inject.Inject

class IncompleteTasksFragment : Fragment() {
    @Inject
    lateinit var incompleteTasksViewModelFactory: IncompleteTasksViewModelFactory
    private lateinit var incompleteTasksViewModel: IncompleteTasksViewModel

    private val tasksListObserver = Observer<List<Task>>
    { newTasks ->
        taskListAdapter.updateTasks(newTasks)
        binding.noIncompleteTasksTextView.visibility =
            if (newTasks.isNotEmpty()) View.GONE else View.VISIBLE
    }
    private val taskViewHolderListener = object : TaskViewHolder.Listener {
        override fun onTaskFinished(taskId: Int) {
            incompleteTasksViewModel.completeTask(taskId, System.currentTimeMillis())
            Toast.makeText(context, getString(R.string.marked_task_as_complete), Toast.LENGTH_SHORT)
                .show()
        }

        override fun onTaskUnfinished(taskId: Int) {}
        override fun onTaskEdit(taskId: Int) {
            navigateToEditTask(taskId)
        }

        override fun onTaskDelete(taskId: Int) {
            incompleteTasksViewModel.deleteTask(taskId)
        }
    }
    private val taskListAdapter = TaskListAdapter(taskViewHolderListener)

    private lateinit var binding: FragmentIncompleteTasksBinding

    companion object {
        fun newInstance() = IncompleteTasksFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_incomplete_tasks, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.apply {
            tasksRecyclerView.adapter = taskListAdapter
            tasksRecyclerView.layoutManager = LinearLayoutManager(context)
            tasksRecyclerView.addItemDecoration(MarginItemDecoration(16))

            addNewTaskFAB.setOnClickListener {
                navigateToEditTask()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupDependencies()
        setupViewModel()
    }

    //Observing the view model is necessary to do in onResume because incomplete and completed tasks can change rapidly.
    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    //DON'T USE ANY FIELDS WITH @INJECT BEFORE THIS METHOD IS CALLED.
    private fun setupDependencies() {
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

    private fun setupViewModel() {
        incompleteTasksViewModel =
            ViewModelProvider(viewModelStore, incompleteTasksViewModelFactory).get(
                IncompleteTasksViewModel::class.java
            )
    }

    private fun observeViewModel() {
        incompleteTasksViewModel.getTasks().observe(viewLifecycleOwner, tasksListObserver)
    }

    fun navigateToEditTask(taskId: Int = DEFAULT_EDIT_FRAGMENT_TASK_ID) {
        view?.let {
            val directions =
                AllTasksViewPagerFragmentDirections.actionAllTasksViewPagerFragmentToEditTasksFragment(
                    taskId
                )
            findNavController().navigate(directions)
        }
    }
}
