package com.example.sunflower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewPager: ViewPager =findViewById(R.id.viewpager)
        var tabLayout: TabLayout =findViewById(R.id.tabLayout)
        val fragmentAdapter=FragmentAdapter(supportFragmentManager)
//        fragmentAdapter.addFragment(MyGarden(),"My Garden")
        fragmentAdapter.addFragment(PlantListFragment(),"Plant List")
        viewPager.adapter=fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
//        tabLayout.getTabAt(0)!!.setIcon(R.drawable.garden_active)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.plant_active)
    }
}