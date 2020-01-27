package com.example.medideals.ui.bottomSheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.medideals.R
import com.example.medideals.ui.activities.login.LoginActivity
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.LOGOUT
import com.example.medideals.utils.Constants.REGISTER_TYPE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.login_dialog.view.*

class LoginDialogFragment() : BottomSheetDialogFragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.login_dialog,container,false)


        view.rel_login_viewCart.setOnClickListener {
            val intent = Intent(activity as AppCompatActivity, LoginActivity::class.java)
            intent.putExtra(LOGOUT, LOGOUT)
            startActivity(intent)
            activity!!.finish()
            activity!!.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        view.rel_register_viewCart.setOnClickListener {
            val intent = Intent(activity as AppCompatActivity, LoginActivity::class.java)
            intent.putExtra(REGISTER_TYPE, REGISTER_TYPE)
            startActivity(intent)
            activity!!.finish()
            activity!!.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }



        return view
    }


}