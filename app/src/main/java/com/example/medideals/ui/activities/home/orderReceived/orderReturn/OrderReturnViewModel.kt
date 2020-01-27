package com.example.medideals.ui.activities.home.orderReceived.orderReturn

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult

class OrderReturnViewModel : ViewModel() {
    var orderReturnLiveData  : LiveData<PagedList<OrderReceivedResult>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        orderReturnLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<OrderReceivedResult>> = orderReturnLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, OrderReceivedResult> {

        val dataSourceFactory = object : DataSource.Factory<String, OrderReceivedResult>() {
            override fun create(): DataSource<String, OrderReceivedResult> {
                return OrderReturnDataSource()
            }
        }
        return LivePagedListBuilder<String, OrderReceivedResult>(dataSourceFactory, config)
    }
}