package com.example.testvkproject.di.component

import android.app.Application
import com.example.testvkproject.di.modules.AppBindsModule
import com.example.testvkproject.di.modules.AppModule
import com.example.testvkproject.di.modules.NetworkModule
import com.example.testvkproject.di.modules.ViewModelModule
import com.example.testvkproject.ui.MainActivity
import com.example.testvkproject.ui.details.DetailsFragment
import com.example.testvkproject.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppBindsModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(detailsFragment: DetailsFragment)


}