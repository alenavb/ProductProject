package com.example.testvkproject.data.repository

import com.example.testvkproject.data.remote.RemoteApi
import com.example.testvkproject.domain.model.ModelProduct
import com.example.testvkproject.domain.repo.ProductRepository
import retrofit2.Response
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(private val api: RemoteApi) : ProductRepository {
    override suspend fun getAllProducts(skip: Int, limit: Int): Response<ModelProduct> {
        return api.getAllProduct(skip, limit)
    }

    override suspend fun searchByTitle(query: String): Response<ModelProduct> {
        return api.searchByTitle(query)
    }

}


