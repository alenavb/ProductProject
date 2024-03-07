package com.example.testvkproject.data.repository

import com.example.testvkproject.data.remote.RemoteApi
import com.example.testvkproject.domain.ModelProduct
import retrofit2.Response


class ProductsRepositoryImpl (private val api: RemoteApi) : ProductRepository {
    override suspend fun getAllProducts(skip: Int, limit: Int): Response<ModelProduct> {
        return api.getAllProduct(skip, limit)
    }

    override suspend fun searchByTitle(query: String): Response<ModelProduct> {
        return api.searchByTitle(query)
    }

}


