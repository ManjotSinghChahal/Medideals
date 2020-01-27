package com.example.medideals.ui.activities.home.orderPlaced

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.orderPlaced.Result
import com.example.medideals.ui.activities.home.allopathic.AllopathicDataSource

class OrderPlacedViewModel : ViewModel() {
    var orderPlacedLiveData  : LiveData<PagedList<Result>>
    var searchQuery : String = ""


    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        orderPlacedLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(search_query : String): LiveData<PagedList<Result>>
    {
        searchQuery = search_query
        return orderPlacedLiveData
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, Result> {

        val dataSourceFactory = object : DataSource.Factory<String, Result>() {
            override fun create(): DataSource<String, Result> {
                return OrderPlacedDataSource(searchQuery)
            }
        }
        return LivePagedListBuilder<String, Result>(dataSourceFactory, config)
    }
}