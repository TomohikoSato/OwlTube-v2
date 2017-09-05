package com.sgr.owltube_v2.di

import com.sgr.owltube_v2.frontend.top.TopFragment
import dagger.android.AndroidInjector
import dagger.Subcomponent

interface TopFragmentSubcomponent : AndroidInjector<TopFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TopFragment>()
}