package com.sgr.owltube_v2

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class OwlTubeV2App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingActivityInjector;

    @Override
    override fun onCreate() {
        super.onCreate()
/*
        DaggerOwlTubeV2AppComponent.create()
                .inject(this)
*/
    }
}