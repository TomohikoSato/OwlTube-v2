package com.sgr.owltube_v2.di

import com.sgr.owltube_v2.frontend.MainActivity
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import com.sgr.owltube_v2.frontend.search.history.SearchHistoryFragment
import com.sgr.owltube_v2.frontend.search.result.SearchResultActivity
import com.sgr.owltube_v2.frontend.search.search.SearchActivity
import com.sgr.owltube_v2.frontend.top.TopFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class UIModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun provideTopFragmentInjector(): TopFragment

    @ContributesAndroidInjector
    abstract fun providePlayerActivityInjector(): PlayerActivity

    @ContributesAndroidInjector
    abstract fun provideSearchResultActivityInjector(): SearchResultActivity

    @ContributesAndroidInjector
    abstract fun provideSearchHistoryFragmentInjector(): SearchHistoryFragment

    @ContributesAndroidInjector
    abstract fun provideSearchActivityInjector(): SearchActivity
}