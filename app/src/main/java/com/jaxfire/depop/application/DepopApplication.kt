package com.jaxfire.depop.application

import android.app.Application
import com.jaxfire.depop.application.di.startDependencyInjectionFramework

class DepopApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startDependencyInjectionFramework(this)
    }
}