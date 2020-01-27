package com.example.medideals.ui.activities.home.contactUs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.medideals.R
import com.example.medideals.data.model.contactUs.Contactus
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.Validator
import kotlinx.android.synthetic.main.fragment_contact_us.view.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class ContactUs : Fragment(), HomeContract.ContactUsView {


    var presenter: HomeContract.HomePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_us, container, false)

        initialize(view)
        clickListener(view)

        return view
    }

    fun initialize(view: View) {
        presenter = HomePresenterImp(this)
        GlobalHelper.setToolbar("", homeMenuIconVis = true)

    }

    fun clickListener(view: View) {

        view.rel_submit_contactUs.setOnClickListener {

            if (Validator.getsInstance().contactUsValidator(
                    view.edt_name_contactUs.text.toString().trim(),
                    view.edt_contact_no_contactUs.text.toString().trim(),
                    view.edt_email_contactUs.text.toString().trim(),
                    view.edt_msg_contactUs.text.toString().trim(),
                    view.rootContactUs, activity as AppCompatActivity
                )
            ) {
                GlobalHelper.showProgress(activity as AppCompatActivity)
                presenter!!.contactUs(
                    view.edt_name_contactUs.text.toString().trim(),
                    view.edt_email_contactUs.text.toString().trim(),
                    view.edt_contact_no_contactUs.text.toString().trim(),
                    "request",
                    view.edt_msg_contactUs.text.toString().trim()
                )

            }
        }

    }


    override fun onContactUsViewSuccess(contactus: Contactus) {
        try {
            GlobalHelper.hideProgress()
            contactus.message.let { GlobalHelper.snackBar(view!!.rootContactUs, it) }
            view!!.edt_name_contactUs.setText("")
            view!!.edt_email_contactUs.setText("")
            view!!.edt_contact_no_contactUs.setText("")
            view!!.edt_msg_contactUs.setText("")
        } catch (ex: Exception) {
        }
    }


    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootContactUs, it) }
        } catch (ex: Exception) {
        }
    }
}



