package com.app.cellapp

import android.app.Application
import android.content.Context
import com.app.cellapp.di.AppComponent
import com.app.cellapp.di.AppModule
import com.app.cellapp.di.DaggerAppComponent
import com.app.cellapp.di.ExcecutorModule


class App : Application() {

    companion object {
        private var instance: App? = null
        var component: AppComponent? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
        fun getInstance() = instance
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .excecutorModule(ExcecutorModule())
            .build()
    }
}