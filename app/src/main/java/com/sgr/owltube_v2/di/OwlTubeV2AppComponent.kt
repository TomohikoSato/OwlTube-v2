package com.sgr.owltube_v2.di

import com.sgr.owltube_v2.OwlTubeV2App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(AppModule::class, MainActivityModule::class))
interface OwlTubeV2AppComponent {

/*
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: OwlTubeV2App): Builder
        fun build(): OwlTubeV2AppComponent
    }
*/


    fun inject(app: OwlTubeV2App)
}

