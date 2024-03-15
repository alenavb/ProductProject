package com.example.testvkproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.testvkproject.data.remote.RetrofitInstance
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import com.example.testvkproject.paging.ProductsPagingSource
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class MainViewModel @AssistedInject constructor(application: Application)
    : AndroidViewModel(application) {
    private val repo: ProductsRepositoryImpl

    init {
        val api = RetrofitInstance.api
        repo = ProductsRepositoryImpl(api)
    }

    val productsList = Pager(PagingConfig(pageSize = 100)) {
        ProductsPagingSource(repo)
    }.flow.cachedIn(viewModelScope)


    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }
}