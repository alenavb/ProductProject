package com.example.testvkproject.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testvkproject.data.repository.ProductRepository
import com.example.testvkproject.domain.Product
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Provider

class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    val productsLiveData = MutableLiveData<List<Product>>()
    val searchLiveData = MutableLiveData<List<Product>>()
    val isLoadingError = MutableLiveData<Boolean>(false)

    private var currentPage = 0

    init {
        loadInitialProducts()
    }

    fun loadInitialProducts() {
        getAllProducts(0)
    }

    fun loadNextPage() {
        getAllProducts(currentPage + 1)

    }

    private fun getAllProducts(page: Int) {
        disposables.add(
            productRepository.getAllProducts(page * 2, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        val newProducts = response.body()?.products ?: emptyList()
                        val currentProducts = productsLiveData.value ?: emptyList()
                        productsLiveData.value = currentProducts + newProducts
                        isLoadingError.postValue(false)
                        currentPage += 1
                    } else {
                        isLoadingError.postValue(true)
                    }
                }, { error ->
                    isLoadingError.postValue(true)
                })
        )
    }

    fun searchProducts(query: String) {
        disposables.add(
            productRepository.searchByTitle(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isSuccessful) {
                        val foundProducts = response.body()?.products ?: emptyList()
                        searchLiveData.postValue(foundProducts)
                    } else {
                        searchLiveData.postValue(emptyList())
                    }
                }, { error ->
                    searchLiveData.postValue(emptyList())
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
