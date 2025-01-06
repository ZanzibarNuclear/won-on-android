package com.worldofnuclear.wonapp

import android.app.Application
import com.worldofnuclear.wonapp.data.AppContainer
import com.worldofnuclear.wonapp.data.DefaultAppContainer

class WonApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}