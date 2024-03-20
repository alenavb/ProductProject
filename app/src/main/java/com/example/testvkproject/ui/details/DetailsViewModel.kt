package com.example.testvkproject.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.testvkproject.data.repository.ProductRepository
import dagger.assisted.AssistedInject
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel(){

}