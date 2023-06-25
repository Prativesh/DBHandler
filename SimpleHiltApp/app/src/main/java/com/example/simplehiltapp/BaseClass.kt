package com.example.simplehiltapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseClass : Application(){

    var value=5
    override fun onCreate() {
        super.onCreate()
    }


}