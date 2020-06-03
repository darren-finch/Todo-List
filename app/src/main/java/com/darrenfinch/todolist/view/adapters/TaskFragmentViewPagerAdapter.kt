package com.darrenfinch.todolist.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.darrenfinch.todolist.view.fragments.CompletedTasksFragment
import com.darrenfinch.todolist.view.fragments.IncompleteTasksFragment
import java.lang.IndexOutOfBoundsException

const val INCOMPLETE_TASKS_FRAGMENT_TAB_INDEX = 0
const val COMPLETED_TASKS_FRAGMENT_TAB_INDEX = 1

/*The structure for this class totally wasn't ripped off of Sunflower*/
class TaskFragmentViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment)
{
    private val tabIndexToFragmentMap: Map<Int, () -> Fragment> = mapOf(
        INCOMPLETE_TASKS_FRAGMENT_TAB_INDEX to { IncompleteTasksFragment.newInstance() },
        COMPLETED_TASKS_FRAGMENT_TAB_INDEX to {CompletedTasksFragment.newInstance() }
    )
    override fun getItemCount() = tabIndexToFragmentMap.size
    override fun createFragment(position: Int) = tabIndexToFragmentMap[position]?.invoke() ?: throw IndexOutOfBoundsException()
}