package com.example.testvkproject.paging

import android.content.res.Resources
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import com.example.testvkproject.domain.model.Product
import retrofit2.HttpException

class ProductsPagingSource(
    private val repository: ProductsRepositoryImpl,
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllProducts(currentPage, limit = 20)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.products
                val responseData = mutableListOf<Product>().apply {
                    addAll(data)
                }

                LoadResult.Page(
                    data = responseData,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (data.isEmpty()) null else currentPage + 1
                )
            } else {
                throw HttpException(response)
            }
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }

}