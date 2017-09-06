package com.sgr.owltube_v2.di

import com.sgr.owltube_v2.App
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        UIModule::class))
interface AppComponent {
    fun inject(app: App)
}

