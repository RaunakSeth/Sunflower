package com.example.sunflower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Date

class MyGardenAdapter(private val plantList: List<MyGardernThumbnailClass>):
    RecyclerView.Adapter<MyGardenAdapter.MyGardernViewHolder>() {
    class MyGardernViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.Plant_IMG)
        val title: TextView = itemView.findViewById(R.id.Plant_Name)
        val water:TextView=itemView.findViewById(R.id.Watering)
        val planted:TextView=itemView.findViewById(R.id.Planted)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGardernViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.gardenplants, parent, false)
        return MyGardernViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: MyGardernViewHolder, position: Int) {
        val currentItem = plantList[position]
        Picasso.get().load(currentItem.imageUrl).into(holder.image)
        holder.title.text = currentItem.title
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        holder.planted.text=currentItem.planted
        holder.itemView.setOnClickListener {
            currentItem.plant_water=currentDate.toString()
        }
        holder.water.text=currentItem.plant_water
    }
}