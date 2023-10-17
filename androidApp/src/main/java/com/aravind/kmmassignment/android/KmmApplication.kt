package com.aravind.kmmassignment.android

import android.app.Application
import com.aravind.kmmassignment.di.initKoin

class KmmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}