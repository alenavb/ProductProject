package com.example.testvkproject

import android.app.Application
import com.example.testvkproject.di.component.AppComponent
import com.example.testvkproject.di.component.DaggerAppComponent

open class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}