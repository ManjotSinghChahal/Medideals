package com.example.medideals.ui.activities.home.dashboard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.medideals.R
import com.example.medideals.data.model.showAllProduct.ShowAllProduct
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.allProducts.AllProducts
import com.example.medideals.ui.activities.home.orderPlaced.OrderPlaced
import com.example.medideals.ui.activities.home.orderReceived.OrderReceived
import com.example.medideals.ui.activities.home.subscription.Subscription
import com.example.medideals.utils.Constants.LAST_ORDER
import com.example.medideals.utils.Constants.SEARCH_QUERY
import com.example.medideals.utils.Constants.TOTAL_ACTIVE_PRODUCTS
import com.example.medideals.utils.Constants.TOTAL_INACTIVE_PRODUCTS
import com.example.medideals.utils.Constants.TOTAL_LISTED_PRODUCTS
import com.example.medideals.utils.Constants.TOTAL_ORDERS_DELIVERED
import com.example.medideals.utils.Constants.TOTAL_ORDERS_DISPATCH
import com.example.medideals.utils.Constants.TOTAL_ORDERS_PLACED
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RECEIVED
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RECEIVED_BUY
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RETURN
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RETURN_BUY
import com.example.medideals.utils.Constants.TOTAL_PRODUCTS_RECEIVED
import com.example.medideals.utils.Constants.TOTAL_PRODUCTS_RETURN
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.GlobalHelper.USER_TYPE
import kotlinx.android.synthetic.main.fragment_dashboard.view.*


class Dashboard : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        initialize(view)
        clickListener(view)


        return view
    }

    fun initialize(view : View)
    {
        GlobalHelper.setToolbar(getString(R.string.dashboard),homeBackIconVis= true)
        if (USER_TYPE.equals("Retailer"))
          view.lin_sellingDetails.visibility = View.GONE
    }

    fun clickListener(view : View)
    {
        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }

        view.totalListedProd_sellDet.setOnClickListener {
           val bundle = Bundle()
            bundle.putString(TOTAL_LISTED_PRODUCTS,TOTAL_LISTED_PRODUCTS)
            bundle.putString(SEARCH_QUERY,"")
            val frag = AllProducts()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }



        view.totalActiveProd_sellDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ACTIVE_PRODUCTS,TOTAL_ACTIVE_PRODUCTS)
            bundle.putString(SEARCH_QUERY,"")
            val frag = AllProducts()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalInActiveProd_sellDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_INACTIVE_PRODUCTS,TOTAL_INACTIVE_PRODUCTS)
            bundle.putString(SEARCH_QUERY,"")
            val frag = AllProducts()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalOrdersRec_sellDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ORDERS_RECEIVED,TOTAL_ORDERS_RECEIVED)
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }


        view.totalOrdersDispatch_sellDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ORDERS_DISPATCH,TOTAL_ORDERS_DISPATCH)
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalOrdersDel_sellDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ORDERS_DELIVERED,TOTAL_ORDERS_DELIVERED)
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalOrdersReturn_sellDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ORDERS_RETURN,TOTAL_ORDERS_RETURN)
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }


        view.subscription_sellDet.setOnClickListener {
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,Subscription()) }
        }


        view.totalOrdersPlaced_buyDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ORDERS_PLACED,TOTAL_ORDERS_PLACED)
            bundle.putString(SEARCH_QUERY,"")
            val frag = OrderPlaced()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalOrdersReceived_buyDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ORDERS_RECEIVED_BUY,TOTAL_ORDERS_RECEIVED_BUY)
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalOrdersReturn_buyDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_ORDERS_RETURN_BUY,TOTAL_ORDERS_RETURN_BUY)
            bundle.putString(SEARCH_QUERY,"")
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalProductsReceived_buyDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_PRODUCTS_RECEIVED,TOTAL_PRODUCTS_RECEIVED)
            bundle.putString(SEARCH_QUERY,"")
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.totalProductsReturn_buyDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(TOTAL_PRODUCTS_RETURN,TOTAL_PRODUCTS_RETURN)
            bundle.putString(SEARCH_QUERY,"")
            val frag = OrderReceived()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }

        view.subscDate_buyDet.setOnClickListener {
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,Subscription()) }
        }

        view.lastOrder_buyDet.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(LAST_ORDER,LAST_ORDER)
            bundle.putString(SEARCH_QUERY,"")
            val frag = OrderPlaced()
            frag.arguments = bundle
            activity?.let {  GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
        }
    }


}
