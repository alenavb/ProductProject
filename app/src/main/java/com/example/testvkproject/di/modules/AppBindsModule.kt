package com.example.testvkproject.di.modules

import com.example.testvkproject.data.repository.ProductRepository
import com.example.testvkproject.data.repository.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppBindsModule {
    @Binds
    abstract fun bindProductRepository(repo: ProductsRepositoryImpl): ProductRepository
}