package com.example.medideals.ui.activities.home.allopathic

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.medideals.data.model.allopathicModel.Result
import com.example.medideals.ui.activities.home.allProducts.networking.ApiClient
import com.example.medideals.ui.activities.home.allProducts.networking.ApiService
import com.example.medideals.ui.activities.home.allopathic.Allopathic.Companion.currentPageAllopathic
import com.example.medideals.utils.Constants
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

class AllopathicDataSource(val cat_id : String,val searchQuery : String,val minPrice_ : String,val maxPrice_ : String,val search_ : String,val dis_ : String,
                           val  brand_ : String,val state_ : String,val city_ : String) : PageKeyedDataSource<String, Result>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Result>) {

        GlobalScope.launch {
            try {

                val response = apiService.allopathic(SharedPrefUtil!!.getInstance()?.getDeviceToken()!!,cat_id,minPrice_,maxPrice_,searchQuery,dis_,brand_,city_,state_,currentPageAllopathic.toString()).await()
                when{
                    response.isSuccessful -> {

                        currentPageAllopathic   = currentPageAllopathic+1

                        val listing = response.body()?.result
                        val items: MutableList<Result> = listing?.map { it } as MutableList<Result>
                        Log.e("trfded",items.size.toString())
                        callback.onResult(items ?: listOf(), null, currentPageAllopathic.toString())
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
                val response = apiService.allopathic(SharedPrefUtil!!.getInstance()?.getDeviceToken()!!,cat_id,minPrice_,maxPrice_,searchQuery,dis_,brand_,city_,state_,
                    currentPageAllopathic.toString()).await()

                when{
                    response.isSuccessful -> {

                        currentPageAllopathic = currentPageAllopathic+1

                        val listing = response.body()?.result
                        val items: MutableList<Result> = listing?.map { it } as MutableList<Result>
                        callback.onResult(items ?: listOf(), currentPageAllopathic.toString())
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