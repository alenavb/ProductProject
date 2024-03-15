package com.example.testvkproject.data.repository

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.testvkproject.data.remote.RemoteApi
import com.example.testvkproject.domain.ModelProduct
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(private val api: RemoteApi) : ProductRepository {
    override suspend fun getAllProducts(skip: Int, limit: Int): Response<ModelProduct> {
        return api.getAllProduct(skip, limit)
    }

}


