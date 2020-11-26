package com.jaxfire.depop.application.di

import android.app.Application
import com.jaxfire.depop.application.di.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

private val appModules = listOf(appModule)

fun startDependencyInjectionFramework(application: Application) {
    startKoin {
        androidContext(application)
        modules(appModules)
    }
}