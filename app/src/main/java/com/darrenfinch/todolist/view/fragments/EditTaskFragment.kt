package com.darrenfinch.todolist.view.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentEditTaskBinding
import com.darrenfinch.todolist.dependencyinjection.AppModule
import com.darrenfinch.todolist.dependencyinjection.DaggerApplicationComponent
import com.darrenfinch.todolist.dependencyinjection.RoomModule
import com.darrenfinch.todolist.model.room.TimeUnit
import com.darrenfinch.todolist.view.helpers.DEFAULT_EDIT_FRAGMENT_TASK_ID
import com.darrenfinch.todolist.viewmodel.EditTaskViewModel
import com.darrenfinch.todolist.viewmodel.EditTaskViewModelFactory
import java.util.*
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

            scheduledDate.setOnClickListener { openDatePickerDialog() }
            timeToComplete.setOnClickListener { openEstimatedTTCPicker() }
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
        val task = editTaskViewModel.observableTask.get()
        if(insertingTask)
            editTaskViewModel.insertTask(task)
        else
            editTaskViewModel.updateTask(task)

        navigateUp()
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
        setupViewModel()

        if(!insertingTask && !editTaskViewModel.observableTask.dirty)
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
    }
    private fun setupViewModel()
    {
        editTaskViewModel = ViewModelProvider(viewModelStore, editTaskViewModelFactory).get(EditTaskViewModel::class.java)
        binding.viewModel = editTaskViewModel
    }
    private fun observeViewModel()
    {
        editTaskViewModel.getTaskFromRepository(args.taskId).observe(viewLifecycleOwner, Observer {
            editTaskViewModel.observableTask.set(it)
        })
    }

    private fun openDatePickerDialog()
    {
        val calendarForScheduledDate = Calendar.getInstance()
        calendarForScheduledDate.timeInMillis = editTaskViewModel.observableTask.scheduledDate
        context?.let{
                DatePickerDialog(it, DatePickerDialog.OnDateSetListener
                { _, year, month, dayOfMonth ->
                    updateTaskScheduledDate(year, month, dayOfMonth)
                }, calendarForScheduledDate[Calendar.YEAR], calendarForScheduledDate[Calendar.MONTH], calendarForScheduledDate[Calendar.DAY_OF_MONTH])
            }?.show()
    }
    private fun updateTaskScheduledDate(year: Int, month: Int, dayOfMonth: Int)
    {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        editTaskViewModel.observableTask.scheduledDate = calendar.timeInMillis
    }

    private fun openEstimatedTTCPicker()
    {
        val dialog = PickTimeToCompleteDialog.newInstance(editTaskViewModel.observableTask.estimatedTTC, editTaskViewModel.observableTask.estimatedTTCUnit)
        dialog.setListener(object : PickTimeToCompleteDialog.Listener
        {
            override fun onTimeToCompleteSelected(estimatedTTC: Int, estimatedTTCUnit: TimeUnit)
            {
                editTaskViewModel.observableTask.estimatedTTC = estimatedTTC
                editTaskViewModel.observableTask.estimatedTTCUnit = estimatedTTCUnit
            }
        })
        dialog.show(parentFragmentManager, "PickTimeToCompleteDialog")
    }
}
