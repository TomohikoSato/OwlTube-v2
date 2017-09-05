package com.sgr.owltube_v2.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(MainActivityModule::class, AndroidSupportInjectionModule::class))
interface OwlTubeV2AppComponent {

}