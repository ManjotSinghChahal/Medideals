package com.example.medideals.ui.activities.home.checkout


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.example.medideals.R
import com.example.medideals.data.model.getProfile.GetProfile
import com.example.medideals.data.model.paymentDetailsModel.PaymentDetailsModel
import com.example.medideals.data.saveData.PaymentDetails
import com.example.medideals.razorPayment.RazorPayment
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.bottomSheet.LoginDialogFragment
import com.example.medideals.utils.Constants.CHECKOUT_TYPE
import com.example.medideals.utils.Constants.EMAIL
import com.example.medideals.utils.Constants.PHONE_NUMBER
import com.example.medideals.utils.Constants.TOTAL_AMOUNT
import com.example.medideals.utils.Constants.TOTAL_ITEMS
import com.example.medideals.utils.Constants.VIEW_CART
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.fragment_checkout.view.*
import kotlinx.android.synthetic.main.fragment_checkout.view.rootCheckout




class Checkout : Fragment(), HomeContract.CheckoutView {



    var presenter: HomeContract.HomePresenter? = null
    var payDetails = PaymentDetails()
    var amount : String = ""
    var items : String = ""
    var email : String = ""
    var contact : String = ""

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)


        initialize(view)
        clickListener(view)






        return view
    }

    fun initialize(view : View)
    {
        GlobalHelper.setToolbar(getString(R.string.checkout), homeBackIconVis = true)
        presenter = HomePresenterImp(this)
        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter!!.getProfile(CHECKOUT_TYPE)

        val bundle = arguments
        if (bundle!=null && bundle.containsKey(VIEW_CART))
        {
            amount = bundle.getString(TOTAL_AMOUNT)
            items = bundle.getString(TOTAL_ITEMS)
        }


    }

    fun clickListener(view : View)
    {
        view.edt_mobile_checkout.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                if (view.edt_mobile_checkout.length() == 10) {
                    view.rel_cross_checkout.visibility = View.VISIBLE
                } else {
                    view.rel_cross_checkout.visibility = View.GONE
                }

                if (view.edt_mobile_checkout.length() > 0)
                    view.txt_countrycode_checkout.visibility = View.VISIBLE
                else
                    view.txt_countrycode_checkout.visibility = View.GONE

            }

        })

        view.rel_cross_checkout.setOnClickListener {
            view.edt_mobile_checkout.setText("")
        }

        view.next_checkout.setOnClickListener {


            if (view.next_checkout.text.toString().trim().equals(getString(R.string.payInfo_checkout))) {

                if (view.businessName_checkout.text.toString().trim().isEmpty())
                     GlobalHelper.snackBar(view.rootCheckout,getString(R.string.enter_business_name))
                else if (view.email_checkout.text.toString().trim().isEmpty())
                    GlobalHelper.snackBar(view.rootCheckout,getString(R.string.enter_email_address))
                else if (view.edt_mobile_checkout.text.toString().trim().isEmpty())
                    GlobalHelper.snackBar(view.rootCheckout,getString(R.string.enter_phone))
                else if (view.shopNo_checkout.text.toString().trim().isEmpty())
                    GlobalHelper.snackBar(view.rootCheckout,getString(R.string.enter_shop_plot_no))
                else if (view.street_checkout.text.toString().trim().isEmpty())
                    GlobalHelper.snackBar(view.rootCheckout,getString(R.string.enter_colony_street_loc))
                else if (view.city_checkout.text.toString().trim().isEmpty())
                    GlobalHelper.snackBar(view.rootCheckout,getString(R.string.enter_city))
                else if (view.country_checkout.text.toString().trim().isEmpty())
                    GlobalHelper.snackBar(view.rootCheckout,getString(R.string.enter_country))
                else
                {
                    payDetails = PaymentDetails()
                    payDetails.user_id = SharedPrefUtil!!.getInstance()?.getUserId().toString()
                    payDetails.firm_name = view.businessName_checkout.text.toString().trim()
                    payDetails.email = view.email_checkout.text.toString().trim()
                    payDetails.contact_no = view.edt_mobile_checkout.text.toString().trim()
                    payDetails.house_no = view.street_checkout.text.toString().trim()
                    payDetails.locality = view.city_checkout.text.toString().trim()
                    payDetails.payment_type = "cashOnDelivery"

                    email = view.email_checkout.text.toString().trim()
                    contact = view.edt_mobile_checkout.text.toString().trim()

                    setPaymentView(view)
                }

            }
            else if (view.next_checkout.text.toString().trim().equals(getString(R.string.proceedPay_checkout)))
            {
                val bundle = Intent(activity, RazorPayment::class.java)
                bundle.putExtra(CHECKOUT_TYPE,CHECKOUT_TYPE)
                bundle.putExtra(TOTAL_AMOUNT,amount)
                bundle.putExtra(TOTAL_ITEMS,items)
                bundle.putExtra(EMAIL,email)
                bundle.putExtra(PHONE_NUMBER,contact)
                startActivityForResult(bundle,0)
            }
            else if (view.next_checkout.text.toString().trim().equals(getString(R.string.contin)))
            {
                activity?.let {
                    it.finishAffinity()
                    it.startActivity(Intent(it,HomeActivity::class.java))
                    it.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                }
            }

        }

        view.bankTransfer_checkout.setOnClickListener {
            val bundle = Intent(activity, RazorPayment::class.java)
            bundle.putExtra(CHECKOUT_TYPE,CHECKOUT_TYPE)
            bundle.putExtra(TOTAL_AMOUNT,amount)
            bundle.putExtra(TOTAL_ITEMS,items)
            bundle.putExtra(EMAIL,email)
            bundle.putExtra(PHONE_NUMBER,contact)
            startActivityForResult(bundle,0)
        }
    }

    fun setShippingView(view : View)
    {
        view.img_shipping_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.colortheme))
        view.imgPayment_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.gray))
        view.imgConfirm_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.gray))
        view.view_shipping.visibility = View.VISIBLE
        view.view_payment.visibility = View.GONE
        view.view_confirm.visibility = View.GONE
        view.next_checkout.text = getString(R.string.payInfo_checkout)
        view.lin_shippingAddress_checkout.visibility = View.VISIBLE
        view.lin_payment_checkout.visibility = View.GONE
    }

    fun setPaymentView(view : View)
    {
        view.img_shipping_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.gray))
        view.imgPayment_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.colortheme))
        view.imgConfirm_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.gray))
        view.view_shipping.visibility = View.GONE
        view.view_payment.visibility = View.VISIBLE
        view.view_confirm.visibility = View.GONE
        view.next_checkout.text = getString(R.string.proceedPay_checkout)
        view.lin_shippingAddress_checkout.visibility = View.GONE
        view.lin_payment_checkout.visibility = View.VISIBLE
    }

    fun setConfirmView(view : View)
    {
        view.img_shipping_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.gray))
        view.imgPayment_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.gray))
        view.imgConfirm_checkout.setColorFilter(ContextCompat.getColor(activity as AppCompatActivity, R.color.colortheme))
        view.view_shipping.visibility = View.GONE
        view.view_payment.visibility = View.GONE
        view.view_confirm.visibility = View.GONE
        view.next_checkout.text = getString(R.string.contin)
        view.lin_shippingAddress_checkout.visibility = View.GONE
        view.lin_payment_checkout.visibility = View.GONE
    }

    override fun onGetProfileViewSuccess(getProfile: GetProfile) {
        try {
              GlobalHelper.hideProgress()
           // getProfile.message.let { GlobalHelper.snackBar(view!!.rootCheckout, it) }
            setValues(view!!, getProfile)

        } catch (ex: Exception) {
        }
    }

    override fun onFailure(error: String) {
        try {
              GlobalHelper.hideProgress()
              GlobalHelper.snackBar(view!!.rootCheckout, error)
        } catch (ex: Exception) {
        }
    }

    fun setValues(view : View, getProfile: GetProfile)
    {
        view.businessName_checkout.setText(getProfile.record.firm_name)
        view.email_checkout.setText(getProfile.record.email)
        view.edt_mobile_checkout.setText(getProfile.record.contact_no)
        view.shopNo_checkout.setText(getProfile.record.shop_no)
        view.street_checkout.setText(getProfile.record.street_name)
        view.city_checkout.setText(getProfile.record.city)
        view.country_checkout.setText("India")

    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

         try {
             if (data!=null && data.hasExtra("status"))
             {
                 if (data.getStringExtra("status").equals("success"))
                 {
                    payDetails.tranx_id = data.getStringExtra("trans_id")
                     GlobalHelper.showProgress(activity as AppCompatActivity)
                 }
                 else if (data.getStringExtra("status").equals("failure"))
                 {
                     /*payDetails.tranx_id = "12345"
                     GlobalHelper.showProgress(activity as AppCompatActivity)
                     presenter!!.payment(payDetails)*/
                 }
             }
         }catch (ex : Exception){}
    }

    override fun onPaymentViewSuccess(paymentDetailsModel: PaymentDetailsModel) {

        try {
            GlobalHelper.hideProgress()
            if (paymentDetailsModel.status.equals("1"))
            {
                setConfirmView(view!!)
            }
        }catch (ex : Exception){}
    }


}
