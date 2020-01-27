package com.example.medideals.ui.activities.home.orderReceived.orderDispatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.ui.activities.home.orderReceived.OrderReceivedDataSource

class OrderDispatchViewModel : ViewModel() {
    var orderDispatchLiveData  : LiveData<PagedList<OrderReceivedResult>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        orderDispatchLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<OrderReceivedResult>> = orderDispatchLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, OrderReceivedResult> {

        val dataSourceFactory = object : DataSource.Factory<String, OrderReceivedResult>() {
            override fun create(): DataSource<String, OrderReceivedResult> {
                return OrderDispatchDataSource()
            }
        }
        return LivePagedListBuilder<String, OrderReceivedResult>(dataSourceFactory, config)
    }
}