package com.example.testvkproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testvkproject.MAIN
import com.example.testvkproject.R
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MAIN = this

        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
    }
}