package com.example.medideals.ui.activities.home.productDetail


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.example.medideals.R
import com.example.medideals.data.model.addCart.AddCart
import com.example.medideals.data.model.productDetail.ProductDetailModel
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.activities.home.viewCart.ViewCart
import com.example.medideals.utils.Constants.PRODUCT_DETAIL
import com.example.medideals.utils.Constants.PRODUCT_ID
import com.example.medideals.utils.Constants.PRODUCT_NAME
import com.example.medideals.utils.Constants.PRODUCT_QUANTITY
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_product_detail.view.*
import java.lang.Exception


class ProductDetail : Fragment() , HomeContract.ProductDetailView{



    var presenter: HomeContract.HomePresenter? = null
    var product_id : String = ""
    var product_qty : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_product_detail, container, false)




        initialize(view)
        clickListener(view)

        return view
    }

    fun initialize(view : View)
    {


        val bundle = arguments
        if (bundle!=null && bundle.containsKey(PRODUCT_ID))
        {
            product_id = bundle?.getString(PRODUCT_ID)
            product_qty = bundle?.getString(PRODUCT_QUANTITY)
            val product_name = bundle?.getString(PRODUCT_NAME)
            GlobalHelper.setToolbar(product_name,homeBackIconVis=true)
            presenter = HomePresenterImp(this)
            GlobalHelper.showProgress(activity as AppCompatActivity)
            presenter!!.productDetail(product_id)
        }



    }

    fun clickListener(view : View)
    {
        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }

        view.rel_contShopping_prodDetail.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }

        view.rel_addToCart_prodDetail.setOnClickListener {
            if (addCart_productDetail.text.toString().trim().equals(getString(R.string.buy_now)))
            {
                GlobalHelper.showProgress(activity as AppCompatActivity)
                presenter!!.addCart(product_id,product_qty,PRODUCT_DETAIL)
            }
            else{
                GlobalHelper.replaceFragment(activity as AppCompatActivity, R.id.container_home, ViewCart())
            }

        }
    }


    override fun onProductDetailViewSuccess(productDetail: ProductDetailModel) {


        try {
            GlobalHelper.hideProgress()
            updateView(productDetail)
        }catch (ex : Exception){}


    }

    override fun onAddCartViewSuccess(addCart: AddCart) {
        try {
            GlobalHelper.hideProgress()
            addCart.message.let { GlobalHelper.snackBar(view!!.rootProductDetail,it) }
            if (addCart.status.equals("1"))
            {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    view!!.rel_addToCart_prodDetail.setBackgroundColor(ContextCompat.getColor(activity as AppCompatActivity, R.color.color_green_shade) )
                } else {
                    view!!.rel_addToCart_prodDetail.setBackgroundColor(ContextCompat.getColor(activity as AppCompatActivity, R.color.color_green_shade))
                }
                view!!.addCart_productDetail.text = getString(R.string.go_to_cart)
            }
        }catch (ex : Exception){}
    }

    override fun onFailure(error: String) {

        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootProductDetail, it) }
        }catch (ex : Exception){}

    }


    fun updateView(productDetail: ProductDetailModel)
    {
        maxPrice_productDetail?.text = "Rs ${productDetail.record.old_price}"
        disPrice_productDetail?.text = "Rs ${productDetail.record.price}"
        qty_productDetail?.text = productDetail.record.min_quantity
        dis_productDetail?.text = "${productDetail.record.discount}%"
        catName_productDetail?.text = productDetail.record.cat_name
        loc_productDetail?.text = productDetail.record.location


        if(productDetail.record.product_status.equals("not_added"))
        {
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                view!!.rel_addToCart_prodDetail.setBackgroundColor(ContextCompat.getColor(activity as AppCompatActivity, R.color.colorthemelight) );
            } else {
                view!!.rel_addToCart_prodDetail.setBackgroundColor(ContextCompat.getColor(activity as AppCompatActivity, R.color.colorthemelight));
            }
            view!!.addCart_productDetail.text = getString(R.string.buy_now)
        }
        else
        {
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                view!!.rel_addToCart_prodDetail.setBackgroundColor(ContextCompat.getColor(activity as AppCompatActivity, R.color.color_green_shade) )
            } else {
                view!!.rel_addToCart_prodDetail.setBackgroundColor(ContextCompat.getColor(activity as AppCompatActivity, R.color.color_green_shade))
            }
            view!!.addCart_productDetail.text = getString(R.string.go_to_cart)
        }

    }


}
