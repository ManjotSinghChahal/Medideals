package com.example.passerby.data.injectorApi


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
import com.example.medideals.data.model.otp.Otp
import com.example.medideals.data.model.paymentDetailsModel.PaymentDetailsModel
import com.example.medideals.data.model.productDetail.ProductDetailModel
import com.example.medideals.data.model.registerModel.RegisterModel
import com.example.medideals.data.model.resendOtp.ResendOtp
import com.example.medideals.data.model.responses.Responses
import com.example.medideals.data.model.showAllProduct.ShowAllProduct
import com.example.medideals.data.model.totalCount.TotalCount
import com.example.medideals.data.model.updateDeviceId.UpdateDeviceId
import com.example.medideals.data.model.updateLatLng.UpdateLatLng
import com.example.medideals.data.model.updateProduct.UpdateProduct
import com.example.medideals.data.model.updateProfile.UpdateProfile
import com.example.medideals.data.model.verifyOtp.VerifyOtp
import com.example.medideals.data.saveData.AddCardInfo
import com.example.medideals.data.saveData.PaymentDetails
import com.example.medideals.data.saveData.SaveProductInf
import com.example.medideals.data.saveData.SaveProfileData
import retrofit2.Call
import retrofit2.http.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.Multipart

interface InterfaceApi {

    @FormUrlEncoded
    @POST("newLogin")
    fun otp(@Field("country_code") country_code: String, @Field("contact_no") contact_no: String): Call<Otp>

    @FormUrlEncoded
    @POST("verifyOtp")
    fun verifyOtp(@Field("user_id") user_id: String, @Field("otp") otp: String): Call<VerifyOtp>

    @FormUrlEncoded
    @POST("resendOtp")
    fun resendOtp(@Field("user_id") user_id: String): Call<ResendOtp>

    @FormUrlEncoded
    @POST("newRegister")
    fun register(@Field("country_code") country_code: String,
                 @Field("contact_no") contact_no: String,
                 @Field("user_name") user_name: String,
                 @Field("email") email: String): Call<RegisterModel>

    @FormUrlEncoded
    @POST("addInformation")
    fun addInformation(@Field("user_id") user_id: String,
                 @Field("user_type") user_type: String,
                 @Field("cat_id") cat_id: String,
                 @Field("firm_name") firm_name: String,
                 @Field("firm_address1") firm_address1: String,
                 @Field("firm_address2") firm_address2: String,
                 @Field("firm_address3") firm_address3: String,
                 @Field("drug_licence") drug_licence: String,
                 @Field("gst_number") gst_number: String): Call<JSONObject>


    @FormUrlEncoded
    @POST("home")
    fun homeListings(@Field("device_id") device_id: String): Call<HomeListings>


    @FormUrlEncoded
    @POST("get_product_detail")
    fun productDetail(@Field("device_id") device_id: String, @Field("product_id") product_id: String): Call<ProductDetailModel>


    @FormUrlEncoded
    @POST("add_cart")
    fun addCart(@Field("device_id") device_id: String, @Field("product_id") product_id: String, @Field("quantity") quantity: String): Call<AddCart>

    @FormUrlEncoded
    @POST("get_cart")
    fun getCart(@Field("device_id") device_id: String): Call<GetCart>

    @FormUrlEncoded
    @POST("delete_cart")
    fun delProductCart(@Field("device_id") device_id: String,@Field("product_id") product_id: String): Call<DelProductCart>


    @FormUrlEncoded
    @POST("contact_us")
    fun contactUs(@Field("name") name: String,
                        @Field("email") email: String,
                        @Field("phone") phone: String,
                        @Field("title") title: String,
                        @Field("message") message: String): Call<Contactus>

    @FormUrlEncoded
    @POST("addEnquirie")
    fun addEnquire(@Field("vendor_id") vendor_id: String,@Field("vendor_email") vendor_email: String, @Field("message") message: String): Call<AddEnquire>

    @FormUrlEncoded
    @POST("orderPlaced")
    fun orderPlaced(@Field("vendor_id") vendor_id: String, @Field("page_no") page_no: String): Call<OrderPlacedModel>

    @FormUrlEncoded
    @POST("update_device_id")
    fun updateDeviceId(@Field("vendor_id") vendor_id: String, @Field("device_type") device_type: String,@Field("device_id") device_id: String): Call<UpdateDeviceId>

    @FormUrlEncoded
    @POST("update_lat_long")
    fun updateLatLng(@Field("vendor_id") vendor_id: String, @Field("latitude") latitude: String,@Field("longitude") longitude: String): Call<UpdateLatLng>

    @FormUrlEncoded
    @POST("logout")
    fun logout(@Field("vendor_id") vendor_id: String, @Field("login_status") login_status: String): Call<Logout>

    @FormUrlEncoded
    @POST("showAllProduct")
    fun showAllProduct(@Field("vendor_id") vendor_id: String, @Field("page_no") page_no: String): Call<ShowAllProduct>


    @POST("editProduct")
    fun updateProduct(@Body updateProd : SaveProductInf): Call<UpdateProduct>

    @FormUrlEncoded
    @POST("getBankDetail")
    fun bankDeatils(@Field("vendor_id") vendor_id: String): Call<BankDeatilsModel>

    @FormUrlEncoded
    @POST("total_counts")
    fun totalCount(@Field("vendor_id") vendor_id: String): Call<TotalCount>

    @FormUrlEncoded
    @POST("getEnquirie")
    fun responses(@Field("vendor_id") vendor_id: String): Call<Responses>

    @FormUrlEncoded
    @POST("get_profile")
    fun getProfile(@Field("vendor_id") vendor_id: String): Call<GetProfile>


    @POST("place_order")
    fun payment(@Body paymentDetails: PaymentDetails): Call<PaymentDetailsModel>

    @POST("edit_profile")
    fun updateProfile(@Body saveProfileData: SaveProfileData): Call<UpdateProfile>

    @GET("get_brands")
    fun getBrands(): Call<GetBrands>

    @GET("get_states")
    fun getStates(): Call<GetStates>

    @FormUrlEncoded
    @POST("get_cities")
    fun getCities(@Field("state_id") state_id: String): Call<GetCities>




    @FormUrlEncoded
    @POST("edit_cart")
    fun editCart(@Field("device_id") device_id: String, @Field("record") record: String): Call<EditCartModel>

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
                   ): Call<AllopathicModel>

    @FormUrlEncoded
    @POST("buySubscription")
    fun buySubscription(@Field("vendor_id") vendor_id: String, @Field("subscription_id") subscription_id: String): Call<BuySubscription>

    @FormUrlEncoded
    @POST("getSubscription")
    fun getSubscription(@Field("vendor_id") vendor_id: String): Call<GetSubscription>

    @POST("addBankDetail")
    fun addCardDetails(@Body addCardInfo: AddCardInfo): Call<AddCard>


    @FormUrlEncoded
    @POST("deleteProduct")
    fun deleteProduct(@Field("vendor_id") vendor_id: String, @Field("product_id") product_id: String): Call<DeleteProduct>

    @FormUrlEncoded
    @POST("changeOrderStatus")
    fun changeOrderStatus(@Field("list_id") list_id: String, @Field("orders_status") orders_status: String): Call<ChangeOrderStatus>

    @FormUrlEncoded
    @POST("addEditDocketNumber")
    fun editDocNum(@Field("list_id") list_id: String, @Field("docket_number") docket_number: String): Call<EditDocNumber>

    @Multipart
    @POST("addProduct")
    fun addProduct(
        @Part("vendor_id") vendor_id: RequestBody, @Part("product_name") product_name: RequestBody,
        @Part("product_description") product_description: RequestBody, @Part("category") category: RequestBody,
        @Part("maximum_retail_Price") maximum_retail_Price: RequestBody, @Part("discounted_price") discounted_price: RequestBody,
        @Part("discount_percent") discount_percent: RequestBody, @Part("quantity") quantity: RequestBody,
        @Part("minquantity") minquantity: RequestBody, @Part("company_name") company_name: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<AddProduct>



/*    @FormUrlEncoded
    @POST("edit_cart")
    fun editCart(@Field("device_id") country_code: String,
                 @Field("product_id") product_id: String,
                 @Field("quantity") email: String): Call<RegisterModel>*/

    /*@FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String, @Field("password") password: String, @Field("deviceToken") deviceToken: String, @Field(
            "latitude"
        ) latitude: String, @Field("longitude") longitude: String
    ): Call<LoginModel>


    @FormUrlEncoded
    @POST("sendotp")
    fun email_register(@Field("email") email: String, @Field("deviceType") deviceType: String, @Field("deviceToken") deviceToken: String): Call<RegisterEmailModel>


    @FormUrlEncoded
    @POST("verifyOtp")
    fun otp(@Field("email") email: String, @Field("otp") otp: String): Call<OtpModel>


    @Multipart
    @POST("updateProfile")
    fun addProfile(
        @Part("email") email: RequestBody, @Part("password") password: RequestBody, @Part("name") name: RequestBody, @Part(
            "dob"
        ) dob: RequestBody, @Part("latitude") latitude: RequestBody, @Part("longitude") longitude: RequestBody, @Part("location") location: RequestBody, @Part image: MultipartBody.Part
    ): Call<AddProfile>


    @Multipart
    @POST("updateProfile")
    fun addProfile2(
        @Part("email") email: RequestBody, @Part("password") password: RequestBody, @Part("name") name: RequestBody, @Part(
            "dob"
        ) dob: RequestBody, @Part("latitude") latitude: RequestBody, @Part("longitude") longitude: RequestBody, @Part("location") location: RequestBody
    ): Call<AddProfile>


    @FormUrlEncoded
    @PUT("UpdateSettings")
    fun updateSetting(
        @Header("Authorization") authHeader: String, @Field("passerByNotification") passerByNotification: String, @Field(
            "messageNotification"
        ) messageNotification: String
    ): Call<UpdateSetting>


    @GET("getSettings")
    fun getSetting(@Header("Authorization") authHeader: String): Call<GetSetting>

    @FormUrlEncoded
    @PUT("UpdateName")
    fun updateName(@Header("Authorization") authHeader: String, @Field("name") name: String): Call<UpdateName>

    @FormUrlEncoded
    @PUT("updateEmail")
    fun updateEmail(@Header("Authorization") authHeader: String, @Field("email") name: String): Call<UpdateEmail>


    @GET("getMyProfile")
    fun getMyProfile(@Header("Authorization") authHeader: String): Call<GetUserProfile>

    @GET("privacyPolicy")
    fun privacyPolicy(@Header("Authorization") authHeader: String): Call<PrivacyPolicyModel>

    @GET("termsCondition")
    fun termsConditions(@Header("Authorization") authHeader: String): Call<TermsConditionsModel>


    @POST("logout")
    fun logout(@Header("Authorization") authHeader: String): Call<Logout>


    @FormUrlEncoded
    @POST("forgetPassword")
    fun forgotPassword(@Field("email") email: String): Call<ForgotPassword>

    @Multipart
    @POST("updateProfileImage")
    fun updateProfileImage(@Header("Authorization") authHeader: String, @Part image: MultipartBody.Part): Call<UpdateProfileImage>


    @FormUrlEncoded
    @PUT("updateBio")
    fun addBio(@Header("Authorization") authHeader: String, @Field("bio") bio: String): Call<AddBioModel>

    @Multipart
    @POST("uploadImage")
    fun addImage(@Header("Authorization") authHeader: String, @Part image: MultipartBody.Part): Call<AddImage>



    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteImage", hasBody = true)
    fun deleteImage(@Header("Authorization") authHeader: String, @Field("imageId") imageId: String): Call<DeleteImage>


    @GET("viewProfile")
    fun userDetail(@Header("Authorization") authHeader: String, @Query("id") bio: String): Call<UserDetailModel>
*/




}