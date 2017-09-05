package com.sgr.owltube_v2

import android.app.Activity
import android.app.Application
import com.sgr.owltube_v2.di.DaggerOwlTubeV2AppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class OwlTubeV2App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingActivityInjector;

    override fun onCreate() {
        super.onCreate()
        DaggerOwlTubeV2AppComponent.create()
                .inject(this)
    }
}