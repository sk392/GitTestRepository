package com.example.gittestapplication

import android.app.Application
import com.example.gittestapplication.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(appModules)
        }
    }
}