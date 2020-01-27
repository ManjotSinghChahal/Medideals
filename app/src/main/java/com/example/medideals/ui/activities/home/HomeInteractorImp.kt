package com.example.medideals.ui.activities.home

import android.util.Log
import com.example.medideals.R
import com.example.medideals.data.model.addCard.AddCard
import com.example.medideals.data.model.addCart.AddCart
import com.example.medideals.data.model.addEnquire.AddEnquire
import com.example.medideals.data.model.addProduct.AddProduct
import com.example.medideals.data.model.allopathicModel.AllopathicModel
import com.example.medideals.data.model.bankDeatilsModel.BankDeatilsModel
import com.example.medideals.data.model.buySubscription.BuySubscription
import com.example.medideals.data.model.changeOrderStatus.ChangeOrderStatus
import com.example.medideals.data.model.contactUs.Contactus
import com.example.medideals.data.model.delProduct.DeleteProduct
import com.example.medideals.data.model.delProductCart.DelProductCart
import com.example.medideals.data.model.editCartModel.EditCartModel
import com.example.medideals.data.model.editDocNumber.EditDocNumber
import com.example.medideals.data.model.getBrands.GetBrands
import com.example.medideals.data.model.getCart.GetCart
import com.example.medideals.data.model.getCities.GetCities
import com.example.medideals.data.model.getProfile.GetProfile
import com.example.medideals.data.model.getStates.GetStates
import com.example.medideals.data.model.getSubscription.GetSubscription
import com.example.medideals.data.model.homeListings.HomeListings
import com.example.medideals.data.model.logout.Logout
import com.example.medideals.data.model.orderPlaced.OrderPlacedModel
import com.example.medideals.data.model.paymentDetailsModel.PaymentDetailsModel
import com.example.medideals.data.model.productDetail.ProductDetailModel
import com.example.medideals.data.model.responses.Responses
import com.example.medideals.data.model.showAllProduct.ShowAllProduct
import com.example.medideals.data.model.totalCount.TotalCount
import com.example.medideals.data.model.updateDeviceId.UpdateDeviceId
import com.example.medideals.data.model.updateLatLng.UpdateLatLng
import com.example.medideals.data.model.updateProduct.UpdateProduct
import com.example.medideals.data.model.updateProfile.UpdateProfile
import com.example.medideals.data.saveData.*
import com.example.medideals.utils.Constants.DEVICE_TYPE
import com.example.medideals.utils.Constants.MESSAGE
import com.example.medideals.utils.Constants.VENDOR_ID
import com.example.medideals.utils.SharedPrefUtil
import com.example.passerby.data.injectorApi.InjectorApi
import com.example.passerby.data.injectorApi.InterfaceApi
import com.example.passerby.ui.baseClasses.App
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.IOException

class HomeInteractorImp : HomeContract.HomeInteractor {



    lateinit var mApi: InterfaceApi
    lateinit var auth_token: String

    init {
        this.mApi = InjectorApi.provideApi()
        auth_token = "Bearer " + SharedPrefUtil.getInstance()?.getAuthToken()
    }



    override fun homeListing(callback: HomeContract.OnHomeCompleteListener) {

        mApi.homeListings(SharedPrefUtil.getInstance()!!.getDeviceToken().toString()).enqueue(object : retrofit2.Callback<HomeListings> {

            override fun onResponse(call: Call<HomeListings>, response: Response<HomeListings>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onHomeListingsSucces(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<HomeListings>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

        })

    }

    override fun productDetail(product_id: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.productDetail(SharedPrefUtil.getInstance()!!.getDeviceToken().toString(),product_id).enqueue(object : retrofit2.Callback<ProductDetailModel> {

            override fun onResponse(call: Call<ProductDetailModel>, response: Response<ProductDetailModel>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onProductDetailSucces(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<ProductDetailModel>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }

    override fun addCart(product_id: String, quantity: String, screen: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.addCart(SharedPrefUtil.getInstance()!!.getDeviceToken().toString(),product_id,quantity).enqueue(object : retrofit2.Callback<AddCart> {

            override fun onResponse(call: Call<AddCart>, response: Response<AddCart>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onAddCartSucces(it,screen) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()

                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<AddCart>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }

    override fun getCart(screen : String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.getCart(SharedPrefUtil.getInstance()!!.getDeviceToken().toString()).enqueue(object : retrofit2.Callback<GetCart> {

            override fun onResponse(call: Call<GetCart>, response: Response<GetCart>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onGetCartSucces(screen, it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()

                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<GetCart>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }

    override fun delProductCartDetail(product_id: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.delProductCart(SharedPrefUtil.getInstance()!!.getDeviceToken().toString(),product_id).enqueue(object : retrofit2.Callback<DelProductCart> {

            override fun onResponse(call: Call<DelProductCart>, response: Response<DelProductCart>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onDelProducCartSucces(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()

                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<DelProductCart>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }

    override fun contactUs(name: String, email: String, phone: String, title: String, message: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.contactUs(name,email,phone,title,message).enqueue(object : retrofit2.Callback<Contactus> {

            override fun onResponse(call: Call<Contactus>, response: Response<Contactus>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onContactUsSucces(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()

                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<Contactus>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }

    override fun addEnquire(vendor_id: String, vendor_email: String, message: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.addEnquire(vendor_id,vendor_email,message).enqueue(object : retrofit2.Callback<AddEnquire> {

            override fun onResponse(call: Call<AddEnquire>, response: Response<AddEnquire>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onAddEnquireSucces(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<AddEnquire>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }

    override fun orderPlaced(vendor_id: String, page: String, callback: HomeContract.OnHomeCompleteListener) {



        mApi.orderPlaced(vendor_id,page).enqueue(object : retrofit2.Callback<OrderPlacedModel> {

            override fun onResponse(call: Call<OrderPlacedModel>, response: Response<OrderPlacedModel>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onOrderPlacedSucces(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<OrderPlacedModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

        })
      }

    override fun logout(callback: HomeContract.OnHomeCompleteListener) {

        mApi.logout(SharedPrefUtil.getInstance()!!.getUserId().toString(),"2").enqueue(object : retrofit2.Callback<Logout> {

            override fun onResponse(call: Call<Logout>, response: Response<Logout>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.logout(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()

                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<Logout>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }

    override fun addProduct(addPlacedModel: AddProductModel, callback: HomeContract.OnHomeCompleteListener) {

        var imagePartBody: MultipartBody.Part? = null
        val file = File(addPlacedModel.prodPic)
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        imagePartBody = MultipartBody.Part.createFormData("image", addPlacedModel.prodPic, requestBody)

        val vendorIdBody = RequestBody.create(MediaType.parse("text/plain"), SharedPrefUtil.getInstance()!!.getUserId().toString())
        val prodNameBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodName)
        val prodDescBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodDesc)
        val prodCatBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodCat)
        val prodRetailPrcBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodRetailPrc)
        val prodDisPrcBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodDisPrc)
        val prodTotalDisBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodTotalDis)
        val prodInvQtyBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodInvQty)
        val prodOrderQtyBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodOrderQty)
        val prodCompNameBody = RequestBody.create(MediaType.parse("text/plain"), addPlacedModel.prodCompName)

        mApi.addProduct(vendorIdBody,prodNameBody,prodDescBody,prodCatBody,prodRetailPrcBody,prodDisPrcBody,prodTotalDisBody,prodInvQtyBody,prodOrderQtyBody,prodCompNameBody,imagePartBody).enqueue(object : retrofit2.Callback<AddProduct> {

            override fun onResponse(call: Call<AddProduct>, response: Response<AddProduct>) {



                if (response.isSuccessful) {
                    response.body()?.let { callback.addProductSucces(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<AddProduct>, t: Throwable) {
                callback.onFailure(t.message.toString())
               }

        })
    }

    override fun updateDeviceID(callback: HomeContract.OnHomeCompleteListener) {

        mApi.updateDeviceId(SharedPrefUtil.getInstance()!!.getUserId().toString(),DEVICE_TYPE,SharedPrefUtil.getInstance()!!.getDeviceToken().toString()).enqueue(object : retrofit2.Callback<UpdateDeviceId> {

            override fun onResponse(call: Call<UpdateDeviceId>, response: Response<UpdateDeviceId>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.updateDeviceId(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<UpdateDeviceId>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

        })
    }

    override fun updateLatLng(lat: String, lng: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.updateLatLng(SharedPrefUtil.getInstance()!!.getUserId().toString(),lat,lng).enqueue(object : retrofit2.Callback<UpdateLatLng> {

            override fun onResponse(call: Call<UpdateLatLng>, response: Response<UpdateLatLng>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.updateLatLng(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<UpdateLatLng>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun showAllProduct(page: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.showAllProduct(SharedPrefUtil.getInstance()!!.getUserId().toString(),page).enqueue(object : retrofit2.Callback<ShowAllProduct> {

            override fun onResponse(call: Call<ShowAllProduct>, response: Response<ShowAllProduct>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onShowAllProductSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<ShowAllProduct>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun deleteProduct(prod_id: String, callback: HomeContract.OnHomeCompleteListener) {
        mApi.deleteProduct(SharedPrefUtil.getInstance()!!.getUserId().toString(),prod_id).enqueue(object : retrofit2.Callback<DeleteProduct> {

            override fun onResponse(call: Call<DeleteProduct>, response: Response<DeleteProduct>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onDeleteProductSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<DeleteProduct>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })    }

    override fun changeOrderStatus(screen : String, list_id: String, status: String, callback: HomeContract.OnHomeCompleteListener) {
        mApi.changeOrderStatus(list_id,status).enqueue(object : retrofit2.Callback<ChangeOrderStatus> {

            override fun onResponse(call: Call<ChangeOrderStatus>, response: Response<ChangeOrderStatus>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onChangeOrderStatusSuccess(screen,it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<ChangeOrderStatus>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun editDocNum(list_id: String, docNum: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.editDocNum(list_id,docNum).enqueue(object : retrofit2.Callback<EditDocNumber> {

            override fun onResponse(call: Call<EditDocNumber>, response: Response<EditDocNumber>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.oneditDocNumSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<EditDocNumber>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun updateProduct(saveProductInf: SaveProductInf, callback: HomeContract.OnHomeCompleteListener) {

        mApi.updateProduct(saveProductInf).enqueue(object : retrofit2.Callback<UpdateProduct> {

            override fun onResponse(call: Call<UpdateProduct>, response: Response<UpdateProduct>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onUpdateProductSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<UpdateProduct>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun bankDetails(callback: HomeContract.OnHomeCompleteListener) {

        // VENDOR_ID
        mApi.bankDeatils(SharedPrefUtil!!.getInstance()?.getUserId().toString()).enqueue(object : retrofit2.Callback<BankDeatilsModel> {

            override fun onResponse(call: Call<BankDeatilsModel>, response: Response<BankDeatilsModel>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onBankDetailsSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<BankDeatilsModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }


    override fun addCardDetails(addCardInfo: AddCardInfo, callback: HomeContract.OnHomeCompleteListener) {

        mApi.addCardDetails(addCardInfo).enqueue(object : retrofit2.Callback<AddCard> {

            override fun onResponse(call: Call<AddCard>, response: Response<AddCard>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onAddCardDetailsSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<AddCard>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun getSubscription(callback: HomeContract.OnHomeCompleteListener) {

        mApi.getSubscription(SharedPrefUtil!!.getInstance()?.getUserId().toString()).enqueue(object : retrofit2.Callback<GetSubscription> {

            override fun onResponse(call: Call<GetSubscription>, response: Response<GetSubscription>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onGetSubscriptionSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<GetSubscription>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun buySubscription(subscription_id: String, callback: HomeContract.OnHomeCompleteListener) {


        mApi.buySubscription(SharedPrefUtil!!.getInstance()?.getUserId().toString(),subscription_id).enqueue(object : retrofit2.Callback<BuySubscription> {

            override fun onResponse(call: Call<BuySubscription>, response: Response<BuySubscription>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onBuySubscriptionSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<BuySubscription>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun totalCount(callback: HomeContract.OnHomeCompleteListener) {

        // SharedPrefUtil!!.getInstance()?.getUserId().toString()
        mApi.totalCount(SharedPrefUtil.getInstance()!!.getUserId().toString()).enqueue(object : retrofit2.Callback<TotalCount> {

            override fun onResponse(call: Call<TotalCount>, response: Response<TotalCount>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onTotalCountSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<TotalCount>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun responses(callback: HomeContract.OnHomeCompleteListener) {

        mApi.responses(SharedPrefUtil.getInstance()!!.getUserId().toString()).enqueue(object : retrofit2.Callback<Responses> {

            override fun onResponse(call: Call<Responses>, response: Response<Responses>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onResponsesSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<Responses>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun allopathic(cat_id: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.allopathic(SharedPrefUtil!!.getInstance()?.getDeviceToken()!!,cat_id,"1","5000","","","","","","").enqueue(object : retrofit2.Callback<AllopathicModel> {

            override fun onResponse(call: Call<AllopathicModel>, response: Response<AllopathicModel>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onAllopathicSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        Log.e("cexx",body)
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<AllopathicModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun editCart(record: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.editCart(SharedPrefUtil!!.getInstance()?.getDeviceToken()!!,record).enqueue(object : retrofit2.Callback<EditCartModel> {

            override fun onResponse(call: Call<EditCartModel>, response: Response<EditCartModel>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onEditCartSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<EditCartModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun getProfile(screen: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.getProfile(SharedPrefUtil!!.getInstance()?.getUserId()!!).enqueue(object : retrofit2.Callback<GetProfile> {

            override fun onResponse(call: Call<GetProfile>, response: Response<GetProfile>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onGetProfileSuccess(screen,it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<GetProfile>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }


    override fun payment(paymentDetails: PaymentDetails, callback: HomeContract.OnHomeCompleteListener) {

        mApi.payment(paymentDetails).enqueue(object : retrofit2.Callback<PaymentDetailsModel> {

            override fun onResponse(call: Call<PaymentDetailsModel>, response: Response<PaymentDetailsModel>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onPaymentSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<PaymentDetailsModel>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun updateProfile(saveProfileData: SaveProfileData, callback: HomeContract.OnHomeCompleteListener) {

        mApi.updateProfile(saveProfileData).enqueue(object : retrofit2.Callback<UpdateProfile> {

            override fun onResponse(call: Call<UpdateProfile>, response: Response<UpdateProfile>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onUpdateProfileSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<UpdateProfile>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun getBrands(callback: HomeContract.OnHomeCompleteListener) {

        mApi.getBrands().enqueue(object : retrofit2.Callback<GetBrands> {

            override fun onResponse(call: Call<GetBrands>, response: Response<GetBrands>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onGetBrandsSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<GetBrands>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun getStates(callback: HomeContract.OnHomeCompleteListener) {

        mApi.getStates().enqueue(object : retrofit2.Callback<GetStates> {

            override fun onResponse(call: Call<GetStates>, response: Response<GetStates>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onGetStatesSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<GetStates>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

    override fun getCities(state_id: String, callback: HomeContract.OnHomeCompleteListener) {

        mApi.getCities(state_id).enqueue(object : retrofit2.Callback<GetCities> {

            override fun onResponse(call: Call<GetCities>, response: Response<GetCities>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onGetCitiesSuccess(it) }
                } else {
                    try {
                        val body = response.errorBody()!!.string()
                        val `object` = JSONObject(body)
                        val error = `object`.getString(MESSAGE)
                        callback.onFailure(error)
                    } catch (e: JSONException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    } catch (e: IOException) {
                        App.instance?.getString(R.string.some_error)?.let { callback.onFailure(it) }
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<GetCities>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }
        })
    }

}

