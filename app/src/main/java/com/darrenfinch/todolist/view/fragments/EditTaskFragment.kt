package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs

import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentEditTaskBinding

class EditTaskFragment : Fragment()
{
//    private val args: EditTasksFragmentArgs by navArgs()

    private lateinit var binding: FragmentEditTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_task, container, false)
        return binding.root
    }
}
