package com.sgr.owltube_v2.frontend.search.result

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.sgr.owltube_v2.common.ext.Logger
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.frontend.common.RequestStatus
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.SearchHistoryRepository
import com.sgr.owltube_v2.infra.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(
        private val searchRepository: SearchRepository,
        private val searchHistoryRepository: SearchHistoryRepository) {
    val searchResultVideos: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()
    val status: ObservableField<RequestStatus> = ObservableField<RequestStatus>(RequestStatus.INIT)

    fun search(query: String) {
        status.set(RequestStatus.REQUESTING)
        searchHistoryRepository.addSearchHistory(SearchHistory(query))
                .subscribeOn(Schedulers.io())
                .subscribe()

        searchRepository.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos ->
                    status.set(RequestStatus.SUCCESS)
                    searchResultVideos.clear()
                    searchResultVideos.addAll(videos)
                }, { t ->
                    status.set(RequestStatus.ERROR)
                    Logger.w(t)
                })
    }
}