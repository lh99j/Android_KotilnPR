package com.example.ch12_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ch12_practice.databinding.ActivityMain2Binding
import com.google.android.material.tabs.TabLayout

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val tab1 : TabLayout.Tab = binding.tabs.newTab()
//        tab1.text = "Tab1"
//        binding.tabs.addTab(tab1)
//
//        val tab2 : TabLayout.Tab = binding.tabs.newTab()
//        tab2.text = "Tab2"
//        binding.tabs.addTab(tab2)
//
//        val tab3 : TabLayout.Tab = binding.tabs.newTab()
//        tab3.text = "Tab3"
//        binding.tabs.addTab(tab3)

        binding.tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
    }
}