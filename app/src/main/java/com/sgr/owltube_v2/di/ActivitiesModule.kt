package com.sgr.owltube_v2.di

import com.sgr.owltube_v2.frontend.MainActivity
import com.sgr.owltube_v2.frontend.top.TopFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun provideTopFragmentInjector(): TopFragment
}