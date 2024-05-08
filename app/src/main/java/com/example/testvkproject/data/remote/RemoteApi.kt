package com.example.testvkproject.data.remote

import com.example.testvkproject.domain.model.ModelProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("product")
    suspend fun getAllProduct(@Query("skip") skip: Int, @Query("limit") limit: Int = 20): Response<ModelProduct>

    @GET("products/search")
    suspend fun searchByTitle(@Query("q") query: String): Response<ModelProduct>

}
