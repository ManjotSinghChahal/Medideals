package com.example.medideals.ui.activities.home.dashboard.inActiveProducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult
import com.example.medideals.ui.activities.home.dashboard.activeProducts.ActiveProductsDataSource


class InActiveProductsViewModel : ViewModel() {
    var inActiveProductsLiveData  : LiveData<PagedList<ShowAllProductResult>>
    var searchQuery : String = ""
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        inActiveProductsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(search_query : String): LiveData<PagedList<ShowAllProductResult>>
    {
        searchQuery = search_query
        return inActiveProductsLiveData
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, ShowAllProductResult> {

        val dataSourceFactory = object : DataSource.Factory<String, ShowAllProductResult>() {
            override fun create(): DataSource<String, ShowAllProductResult> {
                return InActiveProductsDataSource(searchQuery)
            }
        }
        return LivePagedListBuilder<String, ShowAllProductResult>(dataSourceFactory, config)
    }


}