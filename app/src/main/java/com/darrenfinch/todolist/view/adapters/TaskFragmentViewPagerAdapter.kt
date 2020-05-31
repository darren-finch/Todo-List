package com.darrenfinch.todolist.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.darrenfinch.todolist.view.fragments.CompletedTasksFragment
import com.darrenfinch.todolist.view.fragments.IncompleteTasksFragment

class TaskFragmentViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    override fun getPageTitle(position: Int): CharSequence?
    {
        return if(position == 0)
            "Incomplete Tasks"
        else
            "Completed Tasks"
    }
    override fun getItem(position: Int): Fragment
    {
        return if(position == 0)
            IncompleteTasksFragment.newInstance()
        else
            CompletedTasksFragment.newInstance()
    }
    override fun getCount() = 2
}