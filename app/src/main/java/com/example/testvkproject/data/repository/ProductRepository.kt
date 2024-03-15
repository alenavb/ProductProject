package com.example.testvkproject.data.repository

import com.example.testvkproject.domain.ModelProduct
import retrofit2.Response
import retrofit2.http.Path


interface ProductRepository {
    suspend fun getAllProducts(
        skip: Int,
        limit: Int
    ): Response<ModelProduct>

}

