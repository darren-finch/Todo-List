package com.darrenfinch.todolist.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.darrenfinch.todolist.view.fragments.CompletedTasksFragment
import com.darrenfinch.todolist.view.fragments.UncompletedTasksFragment

class TaskFragmentViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    override fun getPageTitle(position: Int): CharSequence?
    {
        return if(position == 0)
            "Uncompleted Tasks"
        else
            "Completed Tasks"
    }
    override fun getItem(position: Int): Fragment
    {
        return if(position == 0)
            UncompletedTasksFragment.newInstance()
        else
            CompletedTasksFragment.newInstance()
    }
    override fun getCount(): Int
    {
        return 2
    }
}