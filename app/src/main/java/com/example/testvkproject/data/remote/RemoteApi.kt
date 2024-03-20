package com.example.testvkproject.data.remote

import com.example.testvkproject.domain.ModelProduct
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("product")
    fun getAllProduct(@Query("skip") skip: Int, @Query("limit") limit: Int = 100): Observable<Response<ModelProduct>>

    @GET("products/search")
    fun searchByTitle(@Query("q") query: String): Observable<Response<ModelProduct>>

}
