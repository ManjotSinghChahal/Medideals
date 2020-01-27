package com.example.medideals.ui.activities.home.myAccount


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.medideals.R
import com.example.medideals.data.model.totalCount.TotalCount
import com.example.medideals.razorPayment.RazorPayment
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.activities.home.addProduct.AddProduct
import com.example.medideals.ui.activities.home.allProducts.AllProducts
import com.example.medideals.ui.activities.home.bankDetails.BankDetails
import com.example.medideals.ui.activities.home.customer_enquiry_form.CustomerEnquiryForm
import com.example.medideals.ui.activities.home.dashboard.Dashboard
import com.example.medideals.ui.activities.home.orderPlaced.OrderPlaced
import com.example.medideals.ui.activities.home.orderReceived.OrderReceived
import com.example.medideals.ui.activities.home.showResponses.ShowResponses
import com.example.medideals.ui.activities.home.subscription.Subscription
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_my_account.view.*


class MyAccount : Fragment(), HomeContract.MyAccountView {


    var presenter: HomeContract.HomePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_my_account, container, false)

        GlobalHelper.setToolbar(getString(R.string.my_acc),homeMenuIconVis= true)
        presenter = HomePresenterImp(this)
        presenter!!.totalCount()

        clickListener(view)


        return view
    }

    fun clickListener(view : View)
    {

        view.addProduct_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,AddProduct()) }
        }

        view.enquiryForm_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,CustomerEnquiryForm()) }
        }

        view.orderPlaced_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,OrderPlaced()) }
        }

        view.orderReceived_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,OrderReceived()) }
        }

        view.showResponses_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,ShowResponses()) }
        }

        view.allProduct_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,AllProducts()) }
        }

        view.wholesellerZone_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,Dashboard()) }
        }

        view.bankDetails_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,BankDetails()) }
           /* activity?.let {
                it.startActivity(Intent(it, RazorPayment::class.java))
            }*/
        }

        view.subscription_myAcc.setOnClickListener {
            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,Subscription()) }
        }
    }

    override fun onTotalCountViewSuccess(totalCount: TotalCount) {

    }

    override fun onFailure(error: String) {

    }


}
