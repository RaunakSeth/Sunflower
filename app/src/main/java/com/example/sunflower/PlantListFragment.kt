package com.example.sunflower

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunflower.DataClasses.PlantAPIDataClass
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlantListFragment : Fragment() {
    private var page:Int=1
    private lateinit var adapter1: PlantAdapter
    private lateinit var PlantRecyclerView: RecyclerView
    private lateinit var PlantArrayList: ArrayList<PlantThumbnailDataClass>
    private lateinit var ShimmerLayout: ShimmerFrameLayout
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://perenual.com/api/")
        .build()
        .create(PlantApiInterface::class.java)
    private val apiKey = "sk-0kpa65af6d40633033860"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PlantArrayList = arrayListOf<PlantThumbnailDataClass>()
        fetchDataFromApi(view,page)
        PlantRecyclerView = view.findViewById(R.id.MyGardernRecyclerView)
        val layoutManager = GridLayoutManager(context,2)
        PlantRecyclerView.layoutManager = layoutManager
        PlantRecyclerView.setHasFixedSize(true)
        adapter1 = PlantAdapter(PlantArrayList)
        PlantRecyclerView.adapter = adapter1
        PlantRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    page++
                    fetchDataFromApi(view,page)
                }
            }
        })
        adapter1.onItemClick={
            val intent = Intent(view.context,PlantDetailPage::class.java)
            intent.putExtra("PlantDetail",it)
            activity?.startActivity(intent)
        }
    }
    private fun fetchDataFromApi(view: View,page:Int):ArrayList<PlantThumbnailDataClass> {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofitData = retrofit.getSpeciesList(apiKey, page, 1)
            retrofitData.enqueue(object : Callback<PlantAPIDataClass> {
                override fun onResponse(
                    call: Call<PlantAPIDataClass>,
                    response: Response<PlantAPIDataClass>
                ) {
                    if (response.isSuccessful) {
                        val speciesList = response.body()!!
                            for (data in speciesList.data) {
                                val defaultImage = data.default_image
                                if (defaultImage != null) {
                                    val originalUrl = defaultImage.original_url
                                    PlantArrayList.add(
                                        PlantThumbnailDataClass(
                                            data.common_name,
                                            originalUrl,data.id,
                                            data.watering
                                        )
                                    )
                                }
                        }
                        adapter1.notifyDataSetChanged()
                        ShimmerLayout = view.findViewById(R.id.Shimmerlayout)
                        ShimmerLayout.visibility = View.GONE
                    } else {
                        // Handle error
                        Log.e("API Call", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<PlantAPIDataClass>, t: Throwable) {
                    // Handle failure
                    Log.e("API Call", "Failure: ${t.message}")
                }
            })
        }
        return PlantArrayList
    }

}