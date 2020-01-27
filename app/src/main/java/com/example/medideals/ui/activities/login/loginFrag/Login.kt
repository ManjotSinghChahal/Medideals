package com.example.medideals.ui.activities.login.loginFrag


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.medideals.R
import com.example.medideals.data.model.otp.Otp
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.login.LoginContract
import com.example.medideals.ui.activities.login.LoginPresenterImp
import com.example.medideals.ui.activities.login.verificationCode.VerificationCode
import com.example.medideals.ui.activities.login.registerFrag.Register
import com.example.medideals.utils.Constants.COUNTRYCODE_INDIA
import com.example.medideals.utils.Constants.LOGIN_TYPE
import com.example.medideals.utils.Constants.PHONE_NUMBER
import com.example.medideals.utils.Constants.SCREEN
import com.example.medideals.utils.Constants.USER_ID
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_login.view.edt_mobile_login


class Login : Fragment() , LoginContract.OtpView{


    var presenter: LoginContract.LoginPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)



        initialize(view)



        return view
    }


    fun initialize(view : View)
    {

        presenter = LoginPresenterImp(this)

        view.send_otp_login.setOnClickListener {
            GlobalHelper.showProgress(activity as AppCompatActivity)
          presenter?.otp(COUNTRYCODE_INDIA,edt_mobile_login.text.toString().trim())
        }

        view.signup_login.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.beginTransaction().replace(R.id.container_login,Register()).addToBackStack(null).commit()
            }
        }

        view.edt_mobile_login.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                if (view.edt_mobile_login.length()==10)
                {
                    view.rel_cross_login.visibility = View.VISIBLE
                    view.send_otp_login.visibility = View.VISIBLE
                }
                else
                {
                    view.rel_cross_login.visibility = View.GONE
                    view.send_otp_login.visibility = View.GONE
                }

                if (view.edt_mobile_login.length()>0)
                   view.txt_countrycode_login.visibility = View.VISIBLE
                else
                   view.txt_countrycode_login.visibility = View.GONE

            }

        })

        view.rel_cross_login.setOnClickListener {
            view.edt_mobile_login.setText("")
        }

    }

    override fun onOtpViewSuccess(otp: Otp) {
        try {
            GlobalHelper.hideProgress()
            activity?.let {

                Log.e("print",otp.otp.toString())
                if (otp.message.equals("Invalid login"))
                  otp.message.let { GlobalHelper.snackBar(view!!.rootOtp, it) }
                else
                {
                    val bundle = Bundle()
                    bundle.putString(USER_ID,otp.user_id)
                    bundle.putString(PHONE_NUMBER,view!!.edt_mobile_login.text.toString().trim())
                    bundle.putString(SCREEN,LOGIN_TYPE)
                    bundle.putString("otp",otp.otp.toString())
                    val frag = VerificationCode()
                    frag.arguments = bundle
                    GlobalHelper.replaceFragment(it,R.id.container_login,frag)
                }
            }
        }catch (ex : Exception){}    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootOtp, it) }
        }catch (ex : Exception){}    }


}
