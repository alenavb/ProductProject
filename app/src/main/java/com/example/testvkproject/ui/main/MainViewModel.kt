package com.example.testvkproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.testvkproject.data.remote.RetrofitInstance
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import com.example.testvkproject.domain.ModelProduct
import com.example.testvkproject.paging.ProductsPagingSource
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel @AssistedInject constructor(application: Application)
    : AndroidViewModel(application) {
    private val repo: ProductsRepositoryImpl

    val myProductSearch: MutableLiveData<Response<ModelProduct>> = MutableLiveData()

    init {
        val api = RetrofitInstance.api
        repo = ProductsRepositoryImpl(api)
    }

    val productsList = Pager(PagingConfig(pageSize = 100)) {
        ProductsPagingSource(repo)
    }.flow.cachedIn(viewModelScope)

    fun searchProducts(query: String) {
        viewModelScope.launch {
            myProductSearch.value = repo.searchByTitle(query)
        }
    }


    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }
}