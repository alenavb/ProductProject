package com.example.testvkproject.ui.utils

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

//class NetworkConnection(private val context: Context): LiveData<Boolean>() {
//    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//    private lateinit var networkCallBack: ConnectivityManager.NetworkCallback
//
//    override fun onActive() {
//        super.onActive()
//        updateConnection()
//        when {
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
//                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallBack())
//            }
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
//                lollipopnetworkRequest()
//            }
//            else -> {
//                context.registerReceiver(
//                    networkReceiver,
//                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//                )
//            }
//        }
//    }
//
//    override fun onInactive() {
//        super.onInactive()
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            connectivityManager.unregisterNetworkCallback(connectivityManagerCallBack())
//        } else {
//            context.unregisterReceiver(networkReceiver)
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private fun lollipopnetworkRequest(){
//        val requestBuilder = NetworkRequest.Builder()
//            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
//        connectivityManager.registerNetworkCallback(
//            requestBuilder.build(),
//            connectivityManagerCallBack()
//        )
//    }
//
//    private fun connectivityManagerCallBack(): ConnectivityManager.NetworkCallback{
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            networkCallBack = object : ConnectivityManager.NetworkCallback(){
//
//                override fun onLost(network: Network) {
//                    super.onLost(network)
//                    postValue(false)
//                }
//
//                override fun onAvailable(network: Network) {
//                    super.onAvailable(network)
//                    postValue(true)
//                }
//            }
//            return networkCallBack
//        } else {
//            throw IllegalAccessError("Error")
//        }
//    }
//    private val networkReceiver = object : BroadcastReceiver(){
//        override fun onReceive(p0: Context?, p1: Intent?) {
//            updateConnection()
//        }
//    }
//
//    private fun updateConnection(){
//        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//        postValue(activeNetwork?.isConnected == true)
//    }
//}