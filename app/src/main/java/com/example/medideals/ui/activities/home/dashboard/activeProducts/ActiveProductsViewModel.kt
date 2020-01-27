package com.example.medideals.ui.activities.home.dashboard.activeProducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult
import com.example.medideals.ui.activities.home.allProducts.AllProductsDataSource
import com.example.medideals.ui.activities.home.orderReceived.OrderReceivedDataSource




class ActiveProductsViewModel : ViewModel() {
    var activeProductsLiveData  : LiveData<PagedList<ShowAllProductResult>>
    var searchQuery : String = ""
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        activeProductsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(search_query : String): LiveData<PagedList<ShowAllProductResult>>
    {
        searchQuery = search_query
        return activeProductsLiveData
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, ShowAllProductResult> {

        val dataSourceFactory = object : DataSource.Factory<String, ShowAllProductResult>() {
            override fun create(): DataSource<String, ShowAllProductResult> {
                return ActiveProductsDataSource(searchQuery)
            }
        }
        return LivePagedListBuilder<String, ShowAllProductResult>(dataSourceFactory, config)
    }
}