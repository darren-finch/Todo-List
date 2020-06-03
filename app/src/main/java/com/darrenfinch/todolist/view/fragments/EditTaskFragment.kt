package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentEditTaskBinding
import com.darrenfinch.todolist.dependencyInjection.dagger2.AppModule
import com.darrenfinch.todolist.dependencyInjection.dagger2.DaggerApplicationComponent
import com.darrenfinch.todolist.dependencyInjection.dagger2.RoomModule
import com.darrenfinch.todolist.dependencyInjection.dagger2.ViewModelFactoryModule
import com.darrenfinch.todolist.model.TimeUnit
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.viewmodel.EditTaskViewModel
import com.darrenfinch.todolist.viewmodel.EditTaskViewModelFactory
import javax.inject.Inject

class EditTaskFragment : Fragment()
{
    private val args: EditTaskFragmentArgs by navArgs()
    private var insertingTask = false

    @Inject
    lateinit var editTaskViewModelFactory: EditTaskViewModelFactory
    private lateinit var editTaskViewModel: EditTaskViewModel

    private lateinit var binding: FragmentEditTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate<FragmentEditTaskBinding>(inflater, R.layout.fragment_edit_task, container, false).apply {
            materialToolbar.setOnMenuItemClickListener { onMenuItemClicked(it.itemId) }
            materialToolbar.inflateMenu(R.menu.edit_task_menu)
            materialToolbar.setNavigationOnClickListener { navigateUp() }
        }
        setHasOptionsMenu(true)

        return binding.root
    }
    private fun onMenuItemClicked(menuItemId: Int) : Boolean
    {
        when(menuItemId)
        {
            R.id.saveTaskMenuItem -> saveTask()
        }
        return true
    }
    private fun saveTask()
    {
        val task = getTaskFromUI()
        if(insertingTask)
            editTaskViewModel.insertTask(task)
        else
            editTaskViewModel.updateTask(task)

        navigateUp()
    }
    private fun getTaskFromUI() : Task
    {
        //TODO: Don't forget to bind everything properly here.
        return Task(
            name = binding.taskNameEditText.text.toString(),
            estimatedTTC = 1,
            estimatedTTCUnit = TimeUnit.HRS,
            scheduledDate = System.currentTimeMillis(),
            description = binding.taskDescriptionEditText.text.toString()
        )
    }
    private fun navigateUp()
    {
        view?.findNavController()?.navigateUp()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        if(args.taskId == DEFAULT_EDIT_FRAGMENT_TASK_ID)
            insertingTask = true

        setupDependencies()

        if(!insertingTask)
            observeViewModel()
    }
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

        editTaskViewModel = ViewModelProvider(viewModelStore, editTaskViewModelFactory).get(EditTaskViewModel::class.java)
        binding.viewModel = editTaskViewModel
    }
    private fun observeViewModel()
    {
        editTaskViewModel.getTask(args.taskId).observe(viewLifecycleOwner, Observer {
            editTaskViewModel.observableTask.set(it)
        })
    }
}
