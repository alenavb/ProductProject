package com.example.testvkproject.ui.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel



//fun Context.appComponent(): AppComponent {
//    return when (this) {
//        is App -> appComponent
//        else -> this.applicationContext.appComponent()
//    }
//}
//
//inline fun <reified T : ViewModel> Fragment.lazyViewModel(
//    noinline create: (stateHandle: SavedStateHandle) -> T
//) = viewModels<T> {
//    ViewModelFactory(this, create)
//}