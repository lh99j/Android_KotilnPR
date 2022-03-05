package com.example.ch11_practice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView;
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch11_practice.databinding.ActivityItemMainBinding
import com.example.ch11_practice.databinding.ActivityMainBinding


//view pager
class MyPagerViewHolder(val binding: ActivityItemMainBinding) :
    RecyclerView.ViewHolder(binding.root)

class MyPagerAdapter(val datas: MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyPagerViewHolder(
            ActivityItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyPagerViewHolder).binding

        //뷰에 데이터 출력
        binding.itemData.text = datas[position]
        when (position % 3) {
            0 -> binding.itemData.setBackgroundColor(Color.RED)
            1 -> binding.itemData.setBackgroundColor(Color.BLUE)
            2 -> binding.itemData.setBackgroundColor(Color.GREEN)

        }
    }
}

class MyFragmentPagerAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity){
    val fragments : List<Fragment>
    init{
        fragments = listOf(Onefragment(), TwoFragment(), ThreeFragment())
        Log.d("kkang", "fragment size : ${fragments.size}")
    }

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //프래그먼트  출력
//        val fragmentManager:FragmentManager = supportFragmentManager
//        val transaction:FragmentTransaction = fragmentManager.beginTransaction()
//        val fragment = Onefragment()
//        transaction.add(R.id.fragment_content, fragment)
//        transaction.commit()

        val datas = mutableListOf<String>()
        for (i in 1..3) {
            datas.add("Item $i")
        }

//        binding.viewpager.adapter = MyPagerAdapter(datas)
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("kkang", "onSupportNavigateUp")
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private var backPressedTime: Long = 0

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
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        0 -> {
            Log.d("kkang", "menu1 click")
            true
        }
        1 -> {
            Log.d("kkang", "menu2 click")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
