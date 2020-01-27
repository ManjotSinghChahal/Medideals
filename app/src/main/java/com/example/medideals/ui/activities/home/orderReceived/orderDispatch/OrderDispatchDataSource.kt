package com.example.medideals.ui.activities.home.orderReceived.orderDispatch

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.ui.activities.home.allProducts.networking.ApiClient
import com.example.medideals.ui.activities.home.allProducts.networking.ApiService
import com.example.medideals.ui.activities.home.orderReceived.OrderReceived
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderDispatchDataSource : PageKeyedDataSource<String, OrderReceivedResult>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)


    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, OrderReceivedResult>) {
        GlobalScope.launch {
            try {
                val response = apiService.fetchOrderDispatch(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                when{
                    response.isSuccessful -> {

                        OrderReceived.currentPageOrderRec   = OrderReceived.currentPageOrderRec+1

                        val listing = response.body()?.result
                        val items: MutableList<OrderReceivedResult> = listing?.map { it } as MutableList<OrderReceivedResult>
                        callback.onResult(items ?: listOf(), null, OrderReceived.currentPageOrderRec.toString())
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, OrderReceivedResult>) {
        GlobalScope.launch {
            try {
                val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                when{
                    response.isSuccessful -> {

                        OrderReceived.currentPageOrderRec = OrderReceived.currentPageOrderRec+1

                        val listing = response.body()?.result
                        val items: MutableList<OrderReceivedResult> = listing?.map { it } as MutableList<OrderReceivedResult>
                        callback.onResult(items ?: listOf(), OrderReceived.currentPageOrderRec.toString())
                    }

                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, OrderReceivedResult>) {
        GlobalScope.launch {
            try {
                val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = "1").await()
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