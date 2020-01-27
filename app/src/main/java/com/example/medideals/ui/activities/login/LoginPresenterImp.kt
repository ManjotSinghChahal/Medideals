package com.example.medideals.ui.activities.login

import com.example.medideals.R
import com.example.medideals.data.model.otp.Otp
import com.example.medideals.data.model.registerModel.RegisterModel
import com.example.medideals.data.model.resendOtp.ResendOtp
import com.example.medideals.data.model.verifyOtp.VerifyOtp
import com.example.passerby.ui.baseClasses.App
import org.json.JSONObject


class LoginPresenterImp() : LoginContract.LoginPresenter, LoginContract.OnLoginCompleteListener {


    lateinit var interactor: LoginContract.LoginInteractor
    var otpView: LoginContract.OtpView? = null
    var verifyOtpView: LoginContract.VerifyOtpView? = null
    var registeView: LoginContract.RegisterView? = null
    var addInfoView: LoginContract.AddInfoView? = null


    constructor(otp_view: LoginContract.OtpView) : this() {
        interactor = LoginInteractorImp()
        otpView = otp_view
    }

    constructor(verifyotp_view: LoginContract.VerifyOtpView) : this() {
        interactor = LoginInteractorImp()
        verifyOtpView = verifyotp_view
    }

    constructor(register_view: LoginContract.RegisterView) : this() {
        interactor = LoginInteractorImp()
        registeView = register_view
    }

    constructor(addInfo_view: LoginContract.AddInfoView) : this() {
        interactor = LoginInteractorImp()
        addInfoView = addInfo_view
    }


    override fun otp(country_code: String, phone: String) {
        if (App.hasNetwork()) {
            interactor.otp(country_code, phone, this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }


    override fun verifyOtp(user_id: String, otp: String) {
        if (App.hasNetwork()) {
            interactor.verifyOtp(user_id, otp, this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }

    override fun resendOtp(user_id: String) {
        if (App.hasNetwork()) {
            interactor.resendOtp(user_id, this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }

    override fun register(country_code: String, phone: String, username: String, email: String) {
        if (App.hasNetwork()) {
            interactor.register(country_code, phone, username, email, this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }

    override fun addInfo(user_id: String, user_type: String, cat_id: String, firm_name: String, firm_address1: String,
        firm_address2: String, firm_address3: String, drug_licence: String, gst_number: String)
    {
        if (App.hasNetwork()) {
            interactor.addInfo(user_id, user_type, cat_id, firm_name,firm_address1,firm_address2,firm_address3,drug_licence, gst_number,this)
        } else {
            onFailure(App.instance!!.getString(R.string.no_internet))
        }
    }

    override fun onAddInfoSuccess(jsonData: JSONObject) {
        jsonData?.let { addInfoView?.onAddInfoViewSuccess(it) }
    }

    override fun onRegisterSuccess(registerModel: RegisterModel) {
        registerModel?.let { registeView?.onRegisterViewSuccess(it) }
    }

    override fun onVerifyOtpSucces(verifyOtp: VerifyOtp) {
        verifyOtp?.let { verifyOtpView?.onVerifyOtpViewSuccess(it) }
    }

    override fun onResendOtpSucces(resendOtp: ResendOtp) {
        resendOtp?.let { verifyOtpView?.onResendOtpViewSuccess(it) }
    }

    override fun onOtpSucces(otp: Otp) {
        otp?.let { otpView?.onOtpViewSuccess(it) }
    }


    override fun onFailure(error: String) {

        if (otpView != null)
            otpView?.onFailure(error)
        else if (verifyOtpView != null)
            verifyOtpView?.onFailure(error)
        else if (addInfoView != null)
            addInfoView?.onFailure(error)


    }


}