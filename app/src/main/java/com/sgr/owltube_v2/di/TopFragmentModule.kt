package com.sgr.owltube_v2.di

import android.support.v4.app.Fragment
import com.sgr.owltube_v2.frontend.top.TopFragment
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module
import dagger.android.support.FragmentKey

@Module(subcomponents = arrayOf(TopFragmentSubcomponent::class))
abstract class TopFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(TopFragment::class)
    abstract fun bindYourFragmentInjectorFactory(builder: TopFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}
