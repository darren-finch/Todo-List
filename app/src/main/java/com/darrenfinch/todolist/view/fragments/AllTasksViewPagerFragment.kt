package com.darrenfinch.todolist.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentAllTasksViewPagerBinding
import com.darrenfinch.todolist.view.adapters.COMPLETED_TASKS_FRAGMENT_TAB_INDEX
import com.darrenfinch.todolist.view.adapters.INCOMPLETE_TASKS_FRAGMENT_TAB_INDEX
import com.darrenfinch.todolist.view.adapters.TaskFragmentViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class AllTasksViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentAllTasksViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAllTasksViewPagerBinding>(
            inflater,
            R.layout.fragment_all_tasks_view_pager,
            container,
            false
        ).apply {
            viewPager.adapter = TaskFragmentViewPagerAdapter(this@AllTasksViewPagerFragment)
            TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, position: Int ->
                tab.text = getFragmentTitle(position)
            }.attach()
        }
        return binding.root
    }

    private fun getFragmentTitle(position: Int): String {
        return when (position) {
            INCOMPLETE_TASKS_FRAGMENT_TAB_INDEX -> getString(R.string.incomplete_tasks)
            COMPLETED_TASKS_FRAGMENT_TAB_INDEX -> getString(R.string.completed_tasks)
            else -> ""
        }
    }
}
