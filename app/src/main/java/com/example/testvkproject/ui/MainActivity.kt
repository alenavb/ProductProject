package com.example.testvkproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import com.example.testvkproject.ui.utils.appComponent


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MAIN = this
        inject()

        navController = Navigation.findNavController(this, R.id.fragmentContainerView)

    }

    private fun inject() {
        applicationContext.appComponent().inject(this)
    }
}