package com.example.medideals.ui.activities.home.allProducts.networking

import com.example.medideals.data.model.allopathicModel.AllopathicModel
import com.example.medideals.data.model.orderPlaced.OrderPlacedModel
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedModel
import com.example.medideals.data.model.showAllProduct.ShowAllProduct
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("showAllProduct")
    fun fetchPosts(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null,
        @Field("search") search: String? = null
        ) : Deferred<Response<ShowAllProduct>>


    @FormUrlEncoded
    @POST("orderReceived")
    fun fetchOrderReceived(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null
    ) : Deferred<Response<OrderReceivedModel>>

    @FormUrlEncoded
    @POST("orderReceive")
    fun fetchOrderReceivedBuy(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null
    ) : Deferred<Response<OrderReceivedModel>>

    @FormUrlEncoded
    @POST("orderReturns")
    fun fetchOrderReturnBuy(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null
    ) : Deferred<Response<OrderReceivedModel>>


    @FormUrlEncoded
    @POST("orderDispatched")
    fun fetchOrderDispatch(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null
    ) : Deferred<Response<OrderReceivedModel>>

    @FormUrlEncoded
    @POST("orderDelivered")
    fun fetchOrderDelivered(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null
    ) : Deferred<Response<OrderReceivedModel>>


    @FormUrlEncoded
    @POST("orderReturn")
    fun fetchOrderReturn(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null
    ) : Deferred<Response<OrderReceivedModel>>


    @FormUrlEncoded
    @POST("activeProduct")
    fun fetchActiveProducts(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null,
        @Field("search") search: String? = null
    ) : Deferred<Response<ShowAllProduct>>

    @FormUrlEncoded
    @POST("inActiveProduct")
    fun fetchInActiveProducts(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null,
        @Field("search") search: String? = null
    ) : Deferred<Response<ShowAllProduct>>

    @FormUrlEncoded
    @POST("search")
    fun allopathic(@Field("device_id") device_id: String,
                   @Field("cat_id") cat_id: String,
                   @Field("minPrice") minPrice: String,
                   @Field("maxPrice") maxPrice: String,
                   @Field("search") search: String,
                   @Field("discount") discount: String,
                   @Field("brand") brand: String,
                   @Field("city") city: String,
                   @Field("state") state: String,
                   @Field("page_no") page_no: String
    ): Deferred<Response<AllopathicModel>>

    @FormUrlEncoded
    @POST("orderPlaced")
    fun fetchorderPlaced(
        @Field("vendor_id") vendor_id: String? = null,
        @Field("page_no") page_no: String? = null,
        @Field("search") search: String? = null
    ) : Deferred<Response<OrderPlacedModel>>



}