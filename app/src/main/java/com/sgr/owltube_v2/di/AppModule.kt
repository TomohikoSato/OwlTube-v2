package com.sgr.owltube_v2.di

import android.content.Context
import com.sgr.owltube_v2.infra.webapi.YoutubeDataAPI
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun providesOkHttp(context: Context): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(Cache(context.cacheDir, 10 * 1024 * 1024))
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HEADERS })
                    .build()


    // TODO: YoutubeかGoogleか向先替えれるように設定したい
    @Singleton
    @Provides
    fun provideRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi): Retrofit
            = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Singleton
    @Provides
    fun provideYoutubeDataApi(retrofit: Retrofit): YoutubeDataAPI {
        return retrofit.create(YoutubeDataAPI::class.java)
    }

}