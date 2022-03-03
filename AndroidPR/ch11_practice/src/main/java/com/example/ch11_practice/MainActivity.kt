package com.example.ch11_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView;
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ch11_practice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentManager:FragmentManager = supportFragmentManager
        val transaction:FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = Onefragment()
        transaction.add(R.id.fragment_content, fragment)
        transaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("kkang", "onSupportNavigateUp")
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    private var backPressedTime : Long = 0

    override fun onBackPressed() {
        Log.d("TAG", "뒤로가기")

        // 2초내 다시 클릭하면 앱 종료
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }

        // 처음 클릭 메시지
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(nextText: String?): Boolean {
                //검색어 변경 이벤트
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                ///키보드의 검색 버튼을 클릭한 순간의 이벤트
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        0->{
            Log.d("kkang","menu1 click")
            true
        }
        1->{
            Log.d("kkang","menu2 click")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}