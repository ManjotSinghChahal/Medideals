package com.example.medideals.ui.activities.login

import android.util.Log
import com.example.medideals.R
import com.example.medideals.data.model.otp.Otp
import com.example.medideals.data.model.registerModel.RegisterModel
import com.example.medideals.data.model.resendOtp.ResendOtp
import com.example.medideals.data.model.verifyOtp.VerifyOtp
import com.example.medideals.utils.Constants.MESSAGE
import com.example.medideals.utils.SharedPrefUtil
import com.example.passerby.data.injectorApi.InjectorApi
import com.example.passerby.data.injectorApi.InterfaceApi
import com.example.passerby.ui.baseClasses.App
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException


class LoginInteractorImp : LoginContract.LoginInteractor {



    lateinit var mApi: InterfaceApi
    lateinit var auth_token: String

    init {
        this.mApi = InjectorApi.provideApi()
        auth_token = "Bearer " + SharedPrefUtil.getInstance()?.getAuthToken()
    }


    override fun otp(country_code: String, phone: String, callback: LoginContract.OnLoginCompleteListener) {

        mApi.otp(country_code, phone).enqueue(object : retrofit2.Callback<Otp> {

            override fun onResponse(call: Call<Otp>, response: Response<Otp>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onOtpSucces(it) }
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

            override fun onFailure(call: Call<Otp>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })

    }

    override fun verifyOtp(user_id: String, otp: String, callback: LoginContract.OnLoginCompleteListener) {

        mApi.verifyOtp(user_id, otp).enqueue(object : retrofit2.Callback<VerifyOtp> {

            override fun onResponse(call: Call<VerifyOtp>, response: Response<VerifyOtp>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onVerifyOtpSucces(it) }
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

            override fun onFailure(call: Call<VerifyOtp>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
     }

    override fun resendOtp(user_id: String, callback: LoginContract.OnLoginCompleteListener) {

        mApi.resendOtp(user_id).enqueue(object : retrofit2.Callback<ResendOtp> {

            override fun onResponse(call: Call<ResendOtp>, response: Response<ResendOtp>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onResendOtpSucces(it) }
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

            override fun onFailure(call: Call<ResendOtp>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }


    override fun register(country_code: String, phone: String, username: String, email: String, callback: LoginContract.OnLoginCompleteListener) {

        mApi.register(country_code,phone,username,email).enqueue(object : retrofit2.Callback<RegisterModel> {

            override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {


                if (response.isSuccessful) {
                    response.body()?.let { callback.onRegisterSuccess(it) }
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

            override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                callback.onFailure(t.message.toString())

            }

        })
    }


    override fun addInfo(user_id : String, user_type : String, cat_id : String, firm_name : String, firm_address1 : String,
           firm_address2 : String, firm_address3 : String, drug_licence : String, gst_number : String, callback: LoginContract.OnLoginCompleteListener)
    {

        mApi.addInformation(user_id,user_type,cat_id,firm_name,firm_address1,firm_address2,firm_address3,drug_licence,gst_number).enqueue(object : retrofit2.Callback<JSONObject> {

            override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {

                if (response.isSuccessful) {
                    response.body()?.let { callback.onAddInfoSuccess(it) }
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

            override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                callback.onFailure(t.message.toString())
            }

        })
    }

    }





