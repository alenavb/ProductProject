package com.example.testvkproject.domain.model

data class ModelProduct(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)