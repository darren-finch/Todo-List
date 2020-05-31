package com.darrenfinch.todolist.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.ActivityMainBinding
import com.darrenfinch.todolist.view.adapters.TaskFragmentViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewPager.adapter = TaskFragmentViewPagerAdapter(supportFragmentManager)
            tabLayout.setupWithViewPager(binding.viewPager)
        }
    }
}
