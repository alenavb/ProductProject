package com.example.testvkproject.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import com.example.testvkproject.domain.Product
import retrofit2.HttpException

class ProductsPagingSource(
    private val repository: ProductsRepositoryImpl,
    private val query: String? = null
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getAllProducts(currentPage, limit = 20)
            val data = response.body()!!.products
            val responseData = mutableListOf<Product>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )



        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }

}