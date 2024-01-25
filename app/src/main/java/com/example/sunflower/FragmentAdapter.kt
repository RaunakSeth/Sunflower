package com.example.sunflower

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var fragmentlist:ArrayList<Fragment> = ArrayList()
    var fragmenttitle:ArrayList<String> = ArrayList()
    var fragmenticon:ArrayList<Int> = ArrayList()

    override fun getCount(): Int {
       return fragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
      return fragmentlist[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmenttitle[position]
    }
    fun addFragment(fragment: Fragment,title:String)
    {
        fragmentlist.add(fragment)
        fragmenttitle.add(title)
    }
}