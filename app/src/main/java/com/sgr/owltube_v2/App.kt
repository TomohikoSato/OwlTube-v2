package com.sgr.owltube_v2

import android.app.Activity
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sgr.owltube_v2.di.AppModule
import com.sgr.owltube_v2.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
                .inject(this)
        AndroidThreeTen.init(this)
    }
}