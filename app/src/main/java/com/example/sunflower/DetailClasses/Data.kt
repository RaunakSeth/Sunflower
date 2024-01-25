package com.example.sunflower.DetailClasses

data class Data(
    val common_name: String,
    val id: Int,
    val scientific_name: List<String>,
    val section: List<Section>,
    val species_id: Int
)