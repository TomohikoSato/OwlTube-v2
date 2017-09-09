package com.sgr.owltube_v2

import android.app.Activity
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sgr.owltube_v2.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create()
                .inject(this)
        AndroidThreeTen.init(this)
    }
}