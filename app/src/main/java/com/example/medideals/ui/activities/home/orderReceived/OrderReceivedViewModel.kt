package com.example.medideals.ui.activities.home.orderReceived

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.orderPlaced.OrderPlacedModel
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult
import com.example.medideals.ui.activities.home.allProducts.AllProductsDataSource

class OrderReceivedViewModel : ViewModel() {
    var orderReceivedLiveData  : LiveData<PagedList<OrderReceivedResult>>
    var screen_handle : String = ""

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        orderReceivedLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(screen : String): LiveData<PagedList<OrderReceivedResult>>
    {
        screen_handle = screen
       return orderReceivedLiveData
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, OrderReceivedResult> {

        val dataSourceFactory = object : DataSource.Factory<String, OrderReceivedResult>() {
            override fun create(): DataSource<String, OrderReceivedResult> {
                return OrderReceivedDataSource(screen_handle)
            }
        }
        return LivePagedListBuilder<String, OrderReceivedResult>(dataSourceFactory, config)
    }
}