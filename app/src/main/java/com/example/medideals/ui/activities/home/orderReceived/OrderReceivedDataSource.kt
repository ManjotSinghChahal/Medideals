package com.example.medideals.ui.activities.home.orderReceived

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedModel
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.ui.activities.home.allProducts.networking.ApiClient
import com.example.medideals.ui.activities.home.allProducts.networking.ApiService
import com.example.medideals.utils.Constants.ORDER_RECEIVED
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RECEIVED_BUY
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RETURN_BUY
import com.example.medideals.utils.Constants.TOTAL_PRODUCTS_RECEIVED
import com.example.medideals.utils.Constants.TOTAL_PRODUCTS_RETURN
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class OrderReceivedDataSource(val screen_handle : String) : PageKeyedDataSource<String, OrderReceivedResult>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)


    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, OrderReceivedResult>) {
        GlobalScope.launch {
            try {
                if (screen_handle.equals(ORDER_RECEIVED))
                {
                    val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    callback.onResult(responseGet(response) ?: listOf(), null, OrderReceived.currentPageOrderRec.toString())
                }
                else if (screen_handle.equals(TOTAL_ORDERS_RECEIVED_BUY))
                {
                    val response = apiService.fetchOrderReceivedBuy(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    callback.onResult(responseGet(response) ?: listOf(), null, OrderReceived.currentPageOrderRec.toString())
                }
                else if (screen_handle.equals(TOTAL_ORDERS_RETURN_BUY))
                {
                    val response = apiService.fetchOrderReturnBuy(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    callback.onResult(responseGet(response) ?: listOf(), null, OrderReceived.currentPageOrderRec.toString())
                }

                else if (screen_handle.equals(TOTAL_PRODUCTS_RECEIVED))
                {
                  //  val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                  //  callback.onResult(responseGet(response) ?: listOf(), null, OrderReceived.currentPageOrderRec.toString())
                }

                else if (screen_handle.equals(TOTAL_PRODUCTS_RETURN))
                {
                  //  val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                  //  callback.onResult(responseGet(response) ?: listOf(), null, OrderReceived.currentPageOrderRec.toString())
                }


            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, OrderReceivedResult>) {
        GlobalScope.launch {
            try {
                /*val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                when{
                    response.isSuccessful -> {

                        OrderReceived.currentPageOrderRec = OrderReceived.currentPageOrderRec+1

                        val listing = response.body()?.result
                        val items: MutableList<OrderReceivedResult> = listing?.map { it } as MutableList<OrderReceivedResult>
                        callback.onResult(items ?: listOf(), OrderReceived.currentPageOrderRec.toString())
                    }

                }*/
                if (screen_handle.equals(ORDER_RECEIVED))
                {
                    val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    callback.onResult(responseGet(response) ?: listOf(),  OrderReceived.currentPageOrderRec.toString())
                }
                else if (screen_handle.equals(TOTAL_ORDERS_RECEIVED_BUY))
                {
                    val response = apiService.fetchOrderReceivedBuy(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    callback.onResult(responseGet(response) ?: listOf(),  OrderReceived.currentPageOrderRec.toString())
                }
                else if (screen_handle.equals(TOTAL_ORDERS_RETURN_BUY))
                {
                    val response = apiService.fetchOrderReturnBuy(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    callback.onResult(responseGet(response) ?: listOf(),  OrderReceived.currentPageOrderRec.toString())
                }

                else if (screen_handle.equals(TOTAL_PRODUCTS_RECEIVED))
                {
                    //  val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    //  callback.onResult(responseGet(response) ?: listOf(),  OrderReceived.currentPageOrderRec.toString())
                }

                else if (screen_handle.equals(TOTAL_PRODUCTS_RETURN)) {
                    //  val response = apiService.fetchOrderReceived(vendor_id = SharedPrefUtil.getInstance()!!.getUserId(),page_no = OrderReceived.currentPageOrderRec.toString()).await()
                    //   callback.onResult(responseGet(response) ?: listOf(),  OrderReceived.currentPageOrderRec.toString())
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

    fun responseGet(response: retrofit2.Response<OrderReceivedModel>) : List<OrderReceivedResult>?
    {
        when{
            response.isSuccessful -> {

                OrderReceived.currentPageOrderRec   = OrderReceived.currentPageOrderRec+1

                val listing = response.body()?.result
                val items: MutableList<OrderReceivedResult> = listing?.map { it } as MutableList<OrderReceivedResult>

                return items

            }
        }
        return null
    }

}