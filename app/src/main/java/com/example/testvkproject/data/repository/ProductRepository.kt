package com.example.testvkproject.data.repository

import com.example.testvkproject.domain.ModelProduct
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.Path


interface ProductRepository {
    fun getAllProducts(
        skip: Int,
        limit: Int
    ): Observable<Response<ModelProduct>>

    fun searchByTitle(query: String) : Observable<Response<ModelProduct>>

}

