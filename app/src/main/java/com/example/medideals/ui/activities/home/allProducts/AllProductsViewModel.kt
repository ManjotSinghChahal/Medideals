package com.example.medideals.ui.activities.home.allProducts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult


class AllProductsViewModel : ViewModel() {
    var allProductsLiveData  : LiveData<PagedList<ShowAllProductResult>>
    var searchQuery : String = ""
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        allProductsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(search_query : String): LiveData<PagedList<ShowAllProductResult>>
    {
        searchQuery = search_query
        return allProductsLiveData
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, ShowAllProductResult> {

        val dataSourceFactory = object : DataSource.Factory<String, ShowAllProductResult>() {
            override fun create(): DataSource<String, ShowAllProductResult> {
                return AllProductsDataSource(searchQuery)
            }
        }
        return LivePagedListBuilder<String, ShowAllProductResult>(dataSourceFactory, config)
    }
}