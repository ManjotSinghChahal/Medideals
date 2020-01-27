package com.example.medideals.ui.activities.login.verificationCode


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medideals.R
import com.example.medideals.data.model.resendOtp.ResendOtp
import com.example.medideals.data.model.verifyOtp.VerifyOtp
import com.example.medideals.data.saveData.AddInfoData
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.login.LoginContract
import com.example.medideals.ui.activities.login.LoginPresenterImp
import com.example.medideals.ui.activities.login.chooseAccountType.ChooseAccountType
import com.example.medideals.utils.Constants.INFO_DATA
import com.example.medideals.utils.Constants.LOGIN_TYPE
import com.example.medideals.utils.Constants.PHONE_NUMBER
import com.example.medideals.utils.Constants.SCREEN
import com.example.medideals.utils.Constants.USER_ID
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_verification_code.*
import kotlinx.android.synthetic.main.fragment_verification_code.view.*
import kotlinx.android.synthetic.main.fragment_verification_code.view.rootVerifyOtp


class VerificationCode : Fragment(), LoginContract.VerifyOtpView {


    var presenter: LoginContract.LoginPresenter? = null
    var user_id: String = ""
    var screen: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_verification_code, container, false)


        initialize(view)
        clickListener(view)

        return view
    }


    fun initialize(view: View) {
        presenter = LoginPresenterImp(this)

        val bundle = arguments
        if (bundle != null && bundle.containsKey(USER_ID)) {
            user_id = bundle.getString(USER_ID)
            screen = bundle.getString(SCREEN)
            val otp = bundle.getString("otp")

            view.numberDesc_verificationCode.text = "${getString(R.string.code_sent)} ${bundle.getString(PHONE_NUMBER)}"
        }
    }

    fun clickListener(view: View) {

        view.rel_verifyOtp.setOnClickListener {

            var otp =
                view.edt_otp_1.text.toString().trim() + view.edt_otp_2.text.toString().trim() + view.edt_otp_3.text.toString().trim() + view.edt_otp_4.text.toString().trim()
            Log.e("printOtp", otp)
            if (otp.length < 4) {
                "Please enter otp".let { GlobalHelper.snackBar(view!!.rootVerifyOtp, it) }
            } else
            {
                GlobalHelper.showProgress(activity as AppCompatActivity)
                presenter?.verifyOtp(user_id, otp)
                GlobalHelper.hideKeyBoard(activity as AppCompatActivity,view!!.rootVerifyOtp)
            }

        }

        view.lin_resendOtp.setOnClickListener {
            GlobalHelper.showProgress(activity as AppCompatActivity)
            presenter?.resendOtp(user_id)
        }

        view.backBtn_verifyCode.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }


        view.edt_otp_1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (view.edt_otp_1.length() == 1) {
                    view.edt_otp_2.requestFocus()
                }
            }

        })


        view.edt_otp_2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (view.edt_otp_2.length() == 1) {
                    view.edt_otp_3.requestFocus()
                }
            }

        })


        view.edt_otp_3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (view.edt_otp_3.length() == 1) {
                    view.edt_otp_4.requestFocus()
                }
            }

        })

        view.edt_otp_4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (view.edt_otp_4.length() == 1) {
                    //  view.edt_otp_2.requestFocus()
                }
            }

        })

    }

    override fun onResendOtpViewSuccess(resendOtp: ResendOtp) {
        try {
            GlobalHelper.hideProgress()
            resendOtp.message.let { GlobalHelper.snackBar(view!!.rootVerifyOtp, it) }
          //  Toast.makeText(activity,"Please enter OTP: ${resendOtp.otp}",Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
        }
    }

    override fun onVerifyOtpViewSuccess(verifyOtp: VerifyOtp) {
        try {
            GlobalHelper.hideProgress()
            verifyOtp.message.let { GlobalHelper.snackBar(view!!.rootVerifyOtp, it) }
            if (verifyOtp.message.equals("Invalid OTP.")) {
            } else {
                activity?.let {

                    SharedPrefUtil.getInstance()!!.saveUserId(verifyOtp.vendor_id)

                    if (screen.equals(LOGIN_TYPE))
                    {
                        SharedPrefUtil.getInstance()!!.setLogin(true)
                        SharedPrefUtil.getInstance()!!.saveNotification(true)
                        val intent = Intent(activity, HomeActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    }
                    else
                    {
                        val infoData = AddInfoData()
                        infoData.user_id = user_id
                        val frag = ChooseAccountType()
                        val bundle = Bundle()
                        bundle.putParcelable(INFO_DATA,infoData)
                        frag.arguments = bundle
                        GlobalHelper.replaceFragment(it, R.id.container_login, frag)
                    }

                }
            }

        } catch (ex: Exception) {
        }
    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootVerifyOtp, it) }
        } catch (ex: Exception) {
        }
    }


}
