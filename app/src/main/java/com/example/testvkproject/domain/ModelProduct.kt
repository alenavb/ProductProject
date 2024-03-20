package com.example.testvkproject.domain

import com.google.gson.annotations.SerializedName

data class ModelProduct(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)