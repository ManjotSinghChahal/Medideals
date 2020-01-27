package com.example.medideals.ui.activities.home.customer_enquiry_form


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.medideals.R
import com.example.medideals.data.model.addEnquire.AddEnquire
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.utils.Constants.VENDOR_ID
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import com.example.medideals.utils.Validator
import kotlinx.android.synthetic.main.fragment_contact_us.view.*
import kotlinx.android.synthetic.main.fragment_customer_enquiry_form.view.*


class CustomerEnquiryForm : Fragment(), HomeContract.AddEnquireView {


    var presenter: HomeContract.HomePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_customer_enquiry_form, container, false)


        initialize(view)
        clickListener(view)



        return view
    }

    fun initialize(view : View)
    {
        presenter = HomePresenterImp(this)
        GlobalHelper.setToolbar(getString(R.string.cust_enquiry_form),homeBackIconVis= true)

        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }
    }

    fun clickListener(view : View)
    {
        view.submit_addEnquire.setOnClickListener {


            if(Validator.getsInstance().addEnquireValidator(view.vendorEmail_addEnquire.text.toString().trim(),
                    view.msg_addEnquire.text.toString().trim(),view.rootAddEnquire, activity as AppCompatActivity))
            {
                presenter!!.addEnquire(SharedPrefUtil.getInstance()!!.getUserId().toString(),view.vendorEmail_addEnquire.text.toString().trim(),
                    view.msg_addEnquire.text.toString().trim())
            }
        }
    }

    override fun onAddEnquireViewSuccess(addEnquire: AddEnquire) {
        try {
            GlobalHelper.hideProgress()
            addEnquire.message.let { GlobalHelper.snackBar(view!!.rootAddEnquire, it) }
            view!!.vendorEmail_addEnquire.setText("")
            view!!.msg_addEnquire.setText("")
        } catch (ex: Exception) {
        }    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootAddEnquire, it) }
        } catch (ex: Exception) {
        }
    }


}
