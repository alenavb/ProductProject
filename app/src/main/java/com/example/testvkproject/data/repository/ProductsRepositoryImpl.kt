package com.example.testvkproject.data.repository

import com.example.testvkproject.data.remote.RemoteApi
import com.example.testvkproject.data.remote.RetrofitInstance
import com.example.testvkproject.domain.ModelProduct
import retrofit2.Response
import retrofit2.http.Query

class ProductsRepositoryImpl(private val api: RemoteApi) : ProductRepository {
    override suspend fun getAllProducts(skip: Int, limit: Int): Response<ModelProduct> {
        return api.getAllProduct(skip, limit)
    }
}


