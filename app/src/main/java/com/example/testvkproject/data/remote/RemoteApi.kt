package com.example.testvkproject.data.remote

import com.example.testvkproject.domain.ModelProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("products")
    suspend fun getAllProduct(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Response<ModelProduct>
}