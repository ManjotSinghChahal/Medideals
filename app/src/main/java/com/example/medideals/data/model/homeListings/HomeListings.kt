package com.example.medideals.data.model.homeListings

data class HomeListings(
    val getMostDiscounted: List<GetMostDiscounted>,
    val getNewProducts: List<GetNewProduct>,
    val hotDealsProducts: List<HotDealsProduct>,
    val images: List<String>,
    val status: String
)