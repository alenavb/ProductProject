package com.example.testvkproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testvkproject.ui.utils.NetworkConnection
import com.example.testvkproject.R
import com.example.testvkproject.ui.utils.appComponent


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        inject()


        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { isConnected ->
            if (isConnected) {
                findViewById<LinearLayout>(R.id.linearNoСonnection).visibility = View.GONE
                findViewById<LinearLayout>(R.id.linearСonnection).visibility = View.VISIBLE
            } else {
                findViewById<LinearLayout>(R.id.linearNoСonnection).visibility = View.VISIBLE
                findViewById<LinearLayout>(R.id.linearСonnection).visibility = View.GONE
            }
        })

    }

    private fun inject() {
        applicationContext.appComponent().inject(this)
    }
}