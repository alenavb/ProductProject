package com.example.testvkproject

import android.app.Application
import com.example.testvkproject.di.AppComponent
import com.example.testvkproject.di.DaggerAppComponent

open class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}