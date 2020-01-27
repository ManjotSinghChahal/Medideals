package com.example.medideals.ui.activities.login.registerFrag


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.medideals.R
import com.example.medideals.data.model.registerModel.RegisterModel
import com.example.medideals.ui.activities.login.LoginContract
import com.example.medideals.ui.activities.login.LoginPresenterImp
import com.example.medideals.ui.activities.login.verificationCode.VerificationCode
import com.example.medideals.ui.activities.login.loginFrag.Login
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.COUNTRYCODE_INDIA
import com.example.medideals.utils.Constants.PHONE_NUMBER
import com.example.medideals.utils.Constants.REGISTER_TYPE
import com.example.medideals.utils.Constants.SCREEN
import com.example.medideals.utils.Constants.USER_ID
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.Validator
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class Register : Fragment() , LoginContract.RegisterView{


    var presenter: LoginContract.LoginPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

         initialize(view)

        return view
    }


    fun initialize(view : View)
    {
        presenter = LoginPresenterImp(this)

        view.signup_signup.setOnClickListener {

            if (Validator.getsInstance().registerValidator(
                    view.username_register.text.toString().trim(),
                    view.email_register.text.toString().trim(),
                    view.edt_mobile_register.text.toString().trim(),
                    view.rootRegister,activity as AppCompatActivity
                ))
            {
                GlobalHelper.showProgress(activity as AppCompatActivity)
                presenter?.register(
                    COUNTRYCODE_INDIA,
                    view.edt_mobile_register.text.toString().trim(),
                    view.username_register.text.toString().trim(),
                    view.email_register.text.toString().trim())
            }


        }

        view.login_register.setOnClickListener {
            activity?.let {
                it.supportFragmentManager.beginTransaction().replace(R.id.container_login,Login()).addToBackStack(null).commit()
            }
        }

        view.edt_mobile_register.addTextChangedListener(object : TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                if (view.edt_mobile_register.length()==10)
                {
                    view.rel_cross_register.visibility = View.VISIBLE
                }
                else
                {
                    view.rel_cross_register.visibility = View.GONE
                }

                if (view.edt_mobile_register.length()>0)
                    view.txt_countrycode_register.visibility = View.VISIBLE
                else
                    view.txt_countrycode_register.visibility = View.GONE

            }

        })

        view.rel_cross_register.setOnClickListener {
            view.edt_mobile_register.setText("")
        }

    }

    override fun onRegisterViewSuccess(registerModel: RegisterModel) {
        try {
            GlobalHelper.hideProgress()
            registerModel.message.let { GlobalHelper.snackBar(view!!.rootRegister, it) }
            if (registerModel.message.equals("Email id already exist")) { }
            else if(registerModel.message.equals("Phone no already exist")) { }
            else
            {
                Log.e("printOTP",registerModel.otp.toString())

                activity?.let {
                    val bundle = Bundle()
                    bundle.putString(USER_ID,registerModel.id)
                    bundle.putString(SCREEN, REGISTER_TYPE)
                    bundle.putString(PHONE_NUMBER,view!!.edt_mobile_register.text.toString().trim())
                    bundle.putString("otp",registerModel.otp.toString())
                    val frag = VerificationCode()
                    frag.arguments = bundle
                    GlobalHelper.replaceFragment(it,R.id.container_login,frag)
                }

            }
        }catch (ex : Exception){}
    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootRegister, it) }
        }catch (ex : Exception){}      }


}
