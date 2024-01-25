package com.example.sunflower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.Locale

class PlantAdapter(private val plantList: List<PlantThumbnailDataClass>) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
    var onItemClick:((PlantThumbnailDataClass)->Unit)?=null
    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.Plant_IMG)
        val title: TextView = itemView.findViewById(R.id.Plant_Name)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.plants, parent, false)
        return PlantViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentItem = plantList[position]
        Picasso.get().load(currentItem.imageUrl).into(holder.image)
        holder.title.text = currentItem.title.capitalizeWords()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentItem)
        }
    }
    fun String.capitalizeWords(delimiter: String = " ") =
        split(delimiter).joinToString(delimiter) { word ->

            val smallCaseWord = word.lowercase()
            smallCaseWord.replaceFirstChar(Char::titlecaseChar)

        }
}


