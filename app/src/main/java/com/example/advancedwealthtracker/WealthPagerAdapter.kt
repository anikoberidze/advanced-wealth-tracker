package com.ani.wealthtracker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ani.wealthtracker.fragments.AnalyticsFragment
import com.ani.wealthtracker.fragments.InputFragment
import com.ani.wealthtracker.fragments.ProfileFragment

class WealthPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InputFragment()
            1 -> AnalyticsFragment()
            2 -> ProfileFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}