package com.sgr.owltube_v2.di

import android.arch.persistence.room.Room
import android.content.Context
import com.sgr.owltube_v2.BuildConfig
import com.sgr.owltube_v2.infra.dao.RecentlyWatchedDao
import com.sgr.owltube_v2.infra.dao.db.AppDatabase
import com.sgr.owltube_v2.infra.webapi.google.GoogleAPI
import com.sgr.owltube_v2.infra.webapi.youtube.YoutubeDataAPI
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Reusable
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Reusable
    @Provides
    fun providesOkHttp(context: Context): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(Cache(context.cacheDir, 10 * 1024 * 1024))
                    .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) BODY else NONE
                    })
                    .addNetworkInterceptor { chain ->
                        val response = chain.proceed(chain.request())
                        response.newBuilder()
                                .header("Cache-Control", "max-age=180") //3分キャッシュするようにする
                                .build()
                    }
                    .build()

    @Reusable
    @Provides
    @Named("youtube")
    fun provideYoutubeRetrofit(oktHttpClient: OkHttpClient, moshi: Moshi): Retrofit
            = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Reusable
    @Provides
    @Named("google")
    fun provideGoogleRetrofit(oktHttpClient: OkHttpClient): Retrofit
            = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("http://suggestqueries.google.com/complete/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Reusable
    @Provides
    fun provideYoutubeDataApi(@Named("youtube") retrofit: Retrofit): YoutubeDataAPI {
        return retrofit.create(YoutubeDataAPI::class.java)
    }

    @Reusable
    @Provides
    fun provideGoogleApi(@Named("google") retrofit: Retrofit): GoogleAPI {
        return retrofit.create(GoogleAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                .build()
    }

    @Reusable
    @Provides
    fun provideRecentlyWatchedDao(db: AppDatabase): RecentlyWatchedDao {
        return db.recentlyWatchedDao()
    }
}