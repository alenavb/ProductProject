package com.example.testvkproject.di

import com.example.testvkproject.data.repository.ProductRepository
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindProductRepository(repositoryImpl: ProductsRepositoryImpl): ProductRepository
}