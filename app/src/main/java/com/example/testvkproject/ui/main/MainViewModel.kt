package com.example.testvkproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testvkproject.data.remote.RetrofitInstance
import com.example.testvkproject.data.repository.ProductRepository
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import com.example.testvkproject.domain.ModelProduct
import kotlinx.coroutines.launch
import retrofit2.Response
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: ProductRepository
    val myProducts: MutableLiveData<Response<ModelProduct>> = MutableLiveData()
    val myProductSearch: MutableLiveData<Response<ModelProduct>> = MutableLiveData()
    val MAX_LIMIT = 100

    init {
        val api = RetrofitInstance.api
        repo = ProductsRepositoryImpl(api)
    }

    fun getProducts(page: Int, itemsPerPage: Int) {
        viewModelScope.launch {
            val skip = (page - 1) * itemsPerPage
            val limit = if (skip >= MAX_LIMIT) 0 else itemsPerPage
            myProducts.value = repo.getAllProducts(skip, limit)
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            myProductSearch.value = repo.searchByTitle(query)
        }
    }
}