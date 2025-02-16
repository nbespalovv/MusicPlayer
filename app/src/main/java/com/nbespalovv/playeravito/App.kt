package com.nbespalovv.playeravito

import android.app.Application
import com.nbespalovv.playeravito.di.AppComponent
import com.nbespalovv.playeravito.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
        private set
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}