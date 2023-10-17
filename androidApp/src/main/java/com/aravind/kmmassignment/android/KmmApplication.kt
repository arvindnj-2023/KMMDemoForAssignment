package com.aravind.kmmassignment.android

import android.app.Application
import com.aravind.kmmassignment.di.initKoin
import org.koin.android.ext.koin.androidContext

class KmmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@KmmApplication)
            modules(viewModelModule)
        }
    }
}