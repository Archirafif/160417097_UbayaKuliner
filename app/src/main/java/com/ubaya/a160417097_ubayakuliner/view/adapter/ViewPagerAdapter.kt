package com.ubaya.a160417097_ubayakuliner.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ubaya.a160417097_ubayakuliner.view.fragment.RestaurantAboutFragment
import com.ubaya.a160417097_ubayakuliner.view.fragment.RestaurantMenuFragment
import com.ubaya.a160417097_ubayakuliner.view.fragment.RestaurantReviewsFragment

class ViewPagerAdapter(fragmentsManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentsManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                RestaurantAboutFragment()
            }
            1 ->{
                RestaurantMenuFragment()
            }
            2 -> {
                RestaurantReviewsFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}