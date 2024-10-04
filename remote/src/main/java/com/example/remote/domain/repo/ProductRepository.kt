package com.example.testvkproject.domain.repo

import com.example.testvkproject.domain.model.ModelProduct
import retrofit2.Response


interface ProductRepository {
    suspend fun getAllProducts(
        skip: Int,
        limit: Int
    ): Response<ModelProduct>

    suspend fun searchByTitle(query: String) : Response<ModelProduct>

}

