package com.example.sunflower

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.sunflower.DetailClasses.Detail
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PlantDetailPage : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://perenual.com/api/")
        .build()
        .create(PlantDetailApiInterface::class.java)
    private val apiKey = "sk-0kpa65af6d40633033860"
//    private lateinit var activityData:MyGardernThumbnailClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail_page)
        val plantImage:ImageView=findViewById(R.id.PlantImage)
        val planttext:TextView=findViewById(R.id.Planttext)
        val plantWater:TextView=findViewById(R.id.PlantWateringNeeds)
        val plantDescription:TextView=findViewById(R.id.PlantDescription)
        val text:TextView=findViewById(R.id.textView3)
        val button:Button=findViewById(R.id.SavetoGardern)
        val back:FloatingActionButton=findViewById(R.id.floatingActionButton)
        plantImage.visibility=View.GONE
        planttext.visibility=View.GONE
        text.visibility=View.GONE
        plantWater.visibility=View.GONE
        plantDescription.visibility=View.GONE
        button.visibility=View.GONE
        val plant= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("PlantDetail",PlantThumbnailDataClass::class.java)
        } else {
            intent.getParcelableExtra<PlantThumbnailDataClass>("PlantDetail")
        }
        if(plant!=null)
        {
            CoroutineScope(Dispatchers.IO).launch {
                val retrofitData = retrofit.getSpeciesDetail(apiKey, 1, plant.plant_id)
                retrofitData.enqueue(object : Callback<Detail> {
                    override fun onResponse(
                        call: Call<Detail>,
                        response: Response<Detail>
                    ) {
                        if (response.isSuccessful) {
                            val speciesDetail = response.body()!!
                            Picasso.get().load(plant.imageUrl).into(plantImage)
                            planttext.setText("${speciesDetail.data.get(0).common_name}")
                            plantWater.setText("${plant.plant_water}")
                            plantDescription.setText("${speciesDetail.data.get(0).section.get(0).description}")
                            val sdf = SimpleDateFormat("dd/M/yyyy")
                            val currentDate = sdf.format(Date())
//                            activityData= MyGardernThumbnailClass("${speciesDetail.data.get(0).common_name}", plant.imageUrl,currentDate.toString(),currentDate.toString())
                            plantImage.visibility=View.VISIBLE
                            planttext.visibility=View.VISIBLE
                            text.visibility=View.VISIBLE
                            plantWater.visibility=View.VISIBLE
                            plantDescription.visibility=View.VISIBLE
                            button.visibility=View.VISIBLE
                        } else {
                            // Handle error
                            Log.e("API Call", "Error: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<Detail>, t: Throwable) {
                        // Handle failure
                        Log.e("API Call", "Failure: ${t.message}")
                    }
                })
            }
            back.setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
//            button.setOnClickListener{
//                val intent = Intent(this,MyGarden::class.java)
//                intent.putExtra("PlantDetail",activityData)
//                startActivity(intent)
//            }

        }
    }
//    fun String.capitalizeWords(delimiter: String = " ") =
//        split(delimiter).joinToString(delimiter) { word ->
//
//            val smallCaseWord = word.lowercase()
//            smallCaseWord.replaceFirstChar(Char::titlecaseChar)
//
//        }
}