package com.example.testvkproject.di

import com.example.testvkproject.domain.repo.ProductRepository
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindProductRepository(repositoryImpl: ProductsRepositoryImpl): ProductRepository
}