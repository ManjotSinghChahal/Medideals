package com.example.medideals.ui.activities.home.subscription


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.data.model.buySubscription.BuySubscription
import com.example.medideals.data.model.getSubscription.GetSubscription
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_subscription.view.*


class Subscription : Fragment(), HomeContract.SubscriptionView, SubscriptionAdapter.SelBuySubscription {



    var recyclerviewSubscription: RecyclerView? = null
    var presenter: HomeContract.HomePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_subscription, container, false)

        initialize(view)
        clickListener(view)

        return view
    }

    fun initialize(view: View) {
        GlobalHelper.setToolbar(getString(R.string.subscription), homeBackIconVis = true)


        presenter = HomePresenterImp(this)
        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter?.getSubscription()

        recyclerviewSubscription = view.findViewById(R.id.recyclerview_subscription) as RecyclerView
        recyclerviewSubscription?.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?




    }

    fun clickListener(view: View) {
        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }
    }

    override fun onGetSubscriptionViewSuccess(getSubscription: GetSubscription) {
        try {
            GlobalHelper.hideProgress()
            GlobalHelper.snackBar(view!!.rootSubscription, getSubscription.message)
            recyclerviewSubscription!!.adapter = SubscriptionAdapter(activity as AppCompatActivity,getSubscription,this)
        } catch (ex: Exception) {
        }
    }

    override fun onBuySubscriptionViewSuccess(buySubscription: BuySubscription) {
        try {
            GlobalHelper.hideProgress()
            GlobalHelper.snackBar(view!!.rootSubscription, buySubscription.message)
        } catch (ex: Exception) {
        }
    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
             error.let { GlobalHelper.snackBar(view!!.rootSubscription, it) }
        } catch (ex: Exception) {
        }
    }


    override fun OnSelBuySubscription(subscription: String) {
          GlobalHelper.showProgress(activity as AppCompatActivity)
          presenter!!.buySubscription(subscription)
    }

}
