package com.example.sunflower

import com.example.sunflower.DataClasses.PlantAPIDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlantApiInterface {
    @GET("species-list")
    fun getSpeciesList(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("indoor") indoor: Int
    ): Call<PlantAPIDataClass>
}