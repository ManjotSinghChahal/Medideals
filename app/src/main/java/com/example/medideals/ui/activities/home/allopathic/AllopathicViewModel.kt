package com.example.medideals.ui.activities.home.allopathic

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.medideals.data.model.allopathicModel.Result
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.ui.activities.home.orderReceived.OrderReceivedDataSource


class AllopathicViewModel : ViewModel() {
    var allopathicLiveData  : LiveData<PagedList<Result>>
    var cat_id : String = ""
    var searchQuery : String = ""
    var minPrice : String = ""
    var maxPrice : String = ""
    var search : String = ""
    var dis : String = ""
    var brand : String = ""
    var state : String = ""
    var city : String = ""

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        allopathicLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts(catId : String,search_query: String, minPrice_ : String, maxPrice_ : String, search_ : String, dis_ : String,
                 brand_ : String, state_ : String, city_ : String): LiveData<PagedList<Result>>
    {

        cat_id = catId
        searchQuery = search_query
        minPrice = minPrice_
        maxPrice = maxPrice_
        search = search_
        dis = dis_
        brand = brand_
        state = state_
        city = city_
        return allopathicLiveData

    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, Result> {

        val dataSourceFactory = object : DataSource.Factory<String, Result>() {
            override fun create(): DataSource<String, Result> {
                return AllopathicDataSource(cat_id,searchQuery,minPrice,maxPrice,search,dis,brand,state,city)
            }
        }
        return LivePagedListBuilder<String, Result>(dataSourceFactory, config)
    }
}