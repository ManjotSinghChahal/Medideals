package com.example.medideals.ui.activities.home.orderPlaced

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.medideals.data.model.orderPlaced.Result
import com.example.medideals.ui.activities.home.allProducts.networking.ApiClient
import com.example.medideals.ui.activities.home.allProducts.networking.ApiService
import com.example.medideals.ui.activities.home.allopathic.Allopathic
import com.example.medideals.ui.activities.home.orderPlaced.OrderPlaced.Companion.currentPageOrderPlaced
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class OrderPlacedDataSource(val search_query : String) : PageKeyedDataSource<String, Result>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)


    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Result>) {
        GlobalScope.launch {
            try {

                val response = apiService.fetchorderPlaced(
                    SharedPrefUtil!!.getInstance()?.getUserId()!!,
                    currentPageOrderPlaced.toString(), search_query).await()
                when{
                    response.isSuccessful -> {

                        currentPageOrderPlaced = currentPageOrderPlaced +1

                        val listing = response.body()?.result
                        val items: MutableList<Result> = listing?.map { it } as MutableList<Result>
                        callback.onResult(items ?: listOf(), null, currentPageOrderPlaced.toString())
                    }
                }






            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")

            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Result>) {
        GlobalScope.launch {
            try {
                //  val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = currentPageAllopathic.toString()).await()
                val response = apiService.fetchorderPlaced(
                    SharedPrefUtil!!.getInstance()?.getUserId()!!,
                    currentPageOrderPlaced.toString(),search_query).await()

                when{
                    response.isSuccessful -> {

                        currentPageOrderPlaced = currentPageOrderPlaced +1

                        val listing = response.body()?.result
                        val items: MutableList<Result> = listing?.map { it } as MutableList<Result>
                        callback.onResult(items ?: listOf(), currentPageOrderPlaced.toString())
                    }

                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Result>) {
        GlobalScope.launch {
            try {
                val response = apiService.fetchorderPlaced(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = "1",search = search_query).await()
                when{
                    response.isSuccessful -> {
                        /* val listing : MutableList<ShowAllProducts> = response.body().result
                         val redditPosts = listing
                         callback.onResult(redditPosts)*/

                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }


}