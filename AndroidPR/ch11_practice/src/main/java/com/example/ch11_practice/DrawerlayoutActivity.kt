package com.example.ch11_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.ch11_practice.databinding.ActivityDrawerlayoutBinding

class DrawerlayoutActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var binding : ActivityDrawerlayoutBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDrawerlayoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.hello_blank_fragment, R.string.bottom_sheet_behavior)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(binding.toolbar)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}