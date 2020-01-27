package com.example.medideals.ui.activities.home.viewCart


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.data.model.delProductCart.DelProductCart
import com.example.medideals.data.model.editCartModel.EditCartModel
import com.example.medideals.data.model.getCart.GetCart
import com.example.medideals.razorPayment.RazorPayment
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.activities.home.checkout.Checkout
import com.example.medideals.ui.bottomSheet.LoginDialogFragment
import com.example.medideals.utils.Constants.TOTAL_AMOUNT
import com.example.medideals.utils.Constants.TOTAL_ITEMS
import com.example.medideals.utils.Constants.VIEW_CART
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_view_cart.view.*
import java.lang.Exception


class ViewCart : Fragment(), HomeContract.GetCartView, ViewCart_Adapter.DelProdCall {



    var recyclerviewViewCart: RecyclerView? = null
    var adptViewCart: ViewCart_Adapter? = null
    var presenter: HomeContract.HomePresenter? = null
    var record : String = ""
    var buttonCheckoutSel : Boolean = false
    // var getCartModel : GetCart? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_cart, container, false)

        GlobalHelper.setToolbar(getString(R.string.review_cart), homeBackIconVis = true)

        initialize(view)


        view.checkout_viewcart.setOnClickListener {
            buttonCheckoutSel = true
            presenter!!.editCart(record)
        }

        return view
    }


    fun initialize(view: View) {
        buttonCheckoutSel = false
        presenter = HomePresenterImp(this)
        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter!!.getCart(VIEW_CART)

        recyclerviewViewCart = view.findViewById(R.id.recyclerview_viewCart) as RecyclerView
        recyclerviewViewCart?.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        //  adptViewCart = getCartModel?.let { ViewCart_Adapter(activity as AppCompatActivity, it,this) }


        HomeActivity.homeBackIcon.setOnClickListener {
            buttonCheckoutSel = false
            presenter!!.editCart(record)
        }
    }

    override fun onGetCartViewSuccess(screen : String, getCart: GetCart) {
        try {
            GlobalHelper.hideProgress()
            activity?.let {
                if (getCart.status.equals("1"))
                {
                    if (buttonCheckoutSel)
                    {
                        if (SharedPrefUtil!!.getInstance()?.isLogin()!!)
                        {
                            val bundle = Bundle()
                            bundle.putString(VIEW_CART,VIEW_CART)
                            bundle.putString(TOTAL_AMOUNT,getCart.record.total)
                            bundle.putString(TOTAL_ITEMS,getCart.total_items.toString())
                            val frag = Checkout()
                            frag.arguments = bundle
                            activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,frag) }
                        }
                        else
                        {
                            val loginDialog = LoginDialogFragment()
                            loginDialog.show(activity!!.supportFragmentManager, "")
                        }


                    }
                    else
                    {
                        recyclerviewViewCart?.adapter = ViewCart_Adapter(it, getCart, this)
                        view!!.lin_checkout.visibility = View.VISIBLE

                        HomeActivity.relCartBack.visibility = View.VISIBLE
                        HomeActivity.cartCountHome.text = getCart.total_items.toString()
                        GlobalHelper.cartCount = getCart.total_items
                    }


                }
                else
                {
                    recyclerviewViewCart?.visibility = View.GONE
                    view!!.lin_checkout.visibility = View.GONE

                    HomeActivity.relCartBack.visibility = View.GONE
                    GlobalHelper.cartCount = 0
                }

            }


            view!!.totalPrice_viewCart.text = "Rs ${GlobalHelper.roundUpto2Dec(getCart.record.total)}"
            view!!.subtotalPrice_viewCart.text = "Rs ${GlobalHelper.roundUpto2Dec(getCart.record.subtotal)}"
        } catch (ex: Exception) {
        }
    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootGetCart, it) }
        } catch (ex: Exception) {
        }

    }

    override fun delProduct(status : String, product_id: String, record_: String, amount : String) {
        try {
           if (status.equals("delete"))
             presenter?.delProductCartDetail(product_id)
           else if (status.equals("editCart"))
           {
               record = record_
               view!!.totalPrice_viewCart.text = "Rs ${GlobalHelper.roundUpto2Dec(amount)}"
               view!!.subtotalPrice_viewCart.text = "Rs ${GlobalHelper.roundUpto2Dec(amount)}"
           }

        } catch (ex: Exception) {
        }

    }

    override fun onDelProducCartViewSuccess(delProductCart: DelProductCart) {
        try {
          //  GlobalHelper.hideProgress()
            delProductCart.message.let { GlobalHelper.snackBar(view!!.rootGetCart, it) }
            presenter!!.getCart(VIEW_CART)
        } catch (ex: Exception) {
        }
    }

    override fun onEditCartViewSuccess(editCartModel: EditCartModel) {
        try {
        if (buttonCheckoutSel)
            presenter!!.getCart(VIEW_CART)
           // activity?.let { GlobalHelper.replaceFragment(it,R.id.container_home,Checkout()) }
        else
            activity?.let { it.onBackPressed() }

        } catch (ex: Exception) {
        }
    }
}
