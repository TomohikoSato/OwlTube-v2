package com.sgr.owltube_v2.di

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()


    // TODO: YoutubeかGitHubか向先替えれるように設定したい
    @Singleton
    @Provides
    fun provideRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi): Retrofit
            = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
}