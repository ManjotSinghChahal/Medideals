package com.example.medideals.ui.activities.login

import com.example.medideals.data.model.otp.Otp
import com.example.medideals.data.model.registerModel.RegisterModel
import com.example.medideals.data.model.resendOtp.ResendOtp
import com.example.medideals.data.model.verifyOtp.VerifyOtp
import com.example.passerby.ui.baseClasses.BaseContract
import org.json.JSONObject

interface LoginContract : BaseContract
{

    interface LoginPresenter
    {
        fun otp(country_code : String, phone : String)
        fun register(country_code : String, phone : String, username : String, email : String)
        fun verifyOtp(user_id : String, otp : String)
        fun resendOtp(user_id : String)
        fun addInfo(user_id : String, user_type : String, cat_id : String, firm_name : String, firm_address1 : String,
                    firm_address2 : String, firm_address3 : String, drug_licence : String, gst_number : String)



    }

    interface LoginInteractor
    {
        fun otp(country_code : String, phone : String, callback: OnLoginCompleteListener)
        fun verifyOtp(user_id : String, otp : String, callback: OnLoginCompleteListener)
        fun resendOtp(user_id : String, callback: OnLoginCompleteListener)
        fun register(country_code : String, phone : String, username : String, email : String, callback: OnLoginCompleteListener)
        fun addInfo(user_id : String, user_type : String, cat_id : String, firm_name : String, firm_address1 : String,
                    firm_address2 : String, firm_address3 : String, drug_licence : String, gst_number : String, callback: OnLoginCompleteListener)

    }


    interface OnLoginCompleteListener : BaseContract.BaseOnCompleteListener
    {
        fun onOtpSucces(otp: Otp)
        fun onVerifyOtpSucces(verifyOtp: VerifyOtp)
        fun onResendOtpSucces(resendOtp: ResendOtp)
        fun onRegisterSuccess(registerModel: RegisterModel)
        fun onAddInfoSuccess(jsonData: JSONObject)

    }

    interface OtpView : BaseContract.BaseView
    {
        fun onOtpViewSuccess(otp: Otp)
    }
    interface AddInfoView : BaseContract.BaseView
    {
        fun onAddInfoViewSuccess(jsonData: JSONObject)
    }

    interface VerifyOtpView : BaseContract.BaseView
    {
        fun onResendOtpViewSuccess(resendOtp: ResendOtp)
        fun onVerifyOtpViewSuccess(verifyOtp: VerifyOtp)
    }
    interface RegisterView : BaseContract.BaseView
    {
        fun onRegisterViewSuccess(registerModel: RegisterModel)
    }




}