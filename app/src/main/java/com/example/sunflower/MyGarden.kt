package com.example.sunflower

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MyGarden : Fragment() {
    private lateinit var adapter1: MyGardenAdapter
    private lateinit var GardernRecyclerView: RecyclerView
    private lateinit var GardernArrayList: ArrayList<MyGardernThumbnailClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GardernArrayList = arrayListOf<MyGardernThumbnailClass>()
        GardernRecyclerView = view.findViewById(R.id.RecyclerView1)
        val layoutManager = GridLayoutManager(context,2)
        GardernRecyclerView.layoutManager = layoutManager
        GardernRecyclerView.setHasFixedSize(true)
        adapter1 = MyGardenAdapter(GardernArrayList)
        GardernRecyclerView.adapter = adapter1

    }

}