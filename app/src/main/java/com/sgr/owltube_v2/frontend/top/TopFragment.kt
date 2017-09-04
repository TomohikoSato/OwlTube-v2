package com.sgr.owltube_v2.frontend.top

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R

import com.sgr.owltube_v2.dummy.DummyContent
import com.sgr.owltube_v2.dummy.DummyContent.DummyItem
import com.sgr.owltube_v2.infra.webapi.YoutubeDataAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*


class TopFragment : Fragment() {
    private lateinit var listener: OnListFragmentInteractionListener

    companion object {
        fun newInstance(): TopFragment {
            return TopFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val httpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = BODY
                }).build()

        val youtubeDataApi = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(YoutubeDataAPI::class.java)


        youtubeDataApi.popular(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { popular -> Log.d("hoge", popular.toString()) }

        return (inflater.inflate(R.layout.fragment_item_list, container, false) as RecyclerView).apply {
            adapter = TopItemRecyclerViewAdapter(DummyContent.ITEMS, listener)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: DummyItem)
    }
}