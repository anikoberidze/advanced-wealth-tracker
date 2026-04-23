package com.ani.wealthtracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.ani.wealthtracker.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.akZeViewpager.orientation = ViewPager2.ORIENTATION_VERTICAL

        val adapter = WealthPagerAdapter(this)
        binding.akZeViewpager.adapter = adapter

        val tabTitles = listOf("💰 Input", "📊 Analytics", "👤 Profile")
        TabLayoutMediator(binding.akZeTabLayout, binding.akZeViewpager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}