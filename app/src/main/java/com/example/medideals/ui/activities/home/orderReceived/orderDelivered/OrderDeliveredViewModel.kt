package com.example.medideals.ui.activities.home.orderReceived.orderDelivered

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.ui.activities.home.orderReceived.orderDispatch.OrderDispatchDataSource

class OrderDeliveredViewModel : ViewModel() {
    var orderDeliveredLiveData  : LiveData<PagedList<OrderReceivedResult>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        orderDeliveredLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(): LiveData<PagedList<OrderReceivedResult>> = orderDeliveredLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, OrderReceivedResult> {

        val dataSourceFactory = object : DataSource.Factory<String, OrderReceivedResult>() {
            override fun create(): DataSource<String, OrderReceivedResult> {
                return OrderDeliveredDataSource()
            }
        }
        return LivePagedListBuilder<String, OrderReceivedResult>(dataSourceFactory, config)
    }
}