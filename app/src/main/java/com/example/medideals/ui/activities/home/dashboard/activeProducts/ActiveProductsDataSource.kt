package com.example.medideals.ui.activities.home.dashboard.activeProducts

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult
import com.example.medideals.ui.activities.home.allProducts.AllProducts
import com.example.medideals.ui.activities.home.allProducts.networking.ApiClient
import com.example.medideals.ui.activities.home.allProducts.networking.ApiService
import com.example.medideals.ui.activities.home.orderReceived.OrderReceived
import com.example.medideals.utils.Constants.VENDOR_ID
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActiveProductsDataSource(val search_query : String) : PageKeyedDataSource<String, ShowAllProductResult>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)


    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, ShowAllProductResult>) {
        GlobalScope.launch {
            try {

                val response = apiService.fetchActiveProducts(vendor_id =SharedPrefUtil.getInstance()!!.getUserId(),page_no = AllProducts.currentPage.toString(),search = search_query).await()
                when{
                    response.isSuccessful -> {

                        AllProducts.currentPage = AllProducts.currentPage+1

                        val listing = response.body()?.result
                        val items: MutableList<ShowAllProductResult> = listing?.map { it } as MutableList<ShowAllProductResult>
                        callback.onResult(items ?: listOf(), null, AllProducts.currentPage.toString())
                    }
                }

            }catch (exception : Exception){
                Log.e("printError",exception.message)
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, ShowAllProductResult>) {
        GlobalScope.launch {
            try {
                val response = apiService.fetchActiveProducts(vendor_id =SharedPrefUtil.getInstance()!!.getUserId(),page_no = AllProducts.currentPage.toString(),search = search_query).await()
                when{
                    response.isSuccessful -> {

                        AllProducts.currentPage = AllProducts.currentPage+1

                        val listing = response.body()?.result
                        val items: MutableList<ShowAllProductResult> = listing?.map { it } as MutableList<ShowAllProductResult>
                        callback.onResult(items ?: listOf(), AllProducts.currentPage.toString())
                    }

                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, ShowAllProductResult>) {
        GlobalScope.launch {
            try {
                val response = apiService.fetchActiveProducts(vendor_id =SharedPrefUtil.getInstance()!!.getUserId(),page_no = "1",search = search_query).await()
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