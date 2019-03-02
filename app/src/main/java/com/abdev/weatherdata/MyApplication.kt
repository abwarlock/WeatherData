package com.abdev.weatherdata

import android.app.Application

class MyApplication : Application() {

    companion object {
        lateinit var application: Application
        fun getContext() = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}