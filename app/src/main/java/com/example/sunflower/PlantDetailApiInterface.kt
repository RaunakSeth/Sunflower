package com.example.sunflower

import com.example.sunflower.DetailClasses.Detail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlantDetailApiInterface {
    @GET("species-care-guide-list")
    fun getSpeciesDetail(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("species_id") species_id: Int
    ): Call<Detail>
}