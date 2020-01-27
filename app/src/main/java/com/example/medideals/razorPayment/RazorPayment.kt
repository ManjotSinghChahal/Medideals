package com.example.medideals.razorPayment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.medideals.R
import com.example.medideals.utils.Constants.CHECKOUT_TYPE
import com.example.medideals.utils.Constants.EMAIL
import com.example.medideals.utils.Constants.PHONE_NUMBER
import com.example.medideals.utils.Constants.TOTAL_AMOUNT
import com.example.medideals.utils.Constants.TOTAL_ITEMS
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class RazorPayment : AppCompatActivity(), PaymentResultListener {

    var amount : String = ""
    var items : String = ""
    var email : String = ""
    var contact : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_razor_payment)

        Checkout.preload(this)

        val intent = intent.extras
        if (intent != null && intent.containsKey(CHECKOUT_TYPE)) {
           // amount = intent.getString(TOTAL_AMOUNT)
           items = intent.getString(TOTAL_ITEMS)
           email = intent.getString(EMAIL)
           contact = intent.getString(PHONE_NUMBER)

            var amount_total : Double = 0.0
            try {
                amount_total = intent.getString(TOTAL_AMOUNT).toDouble()*100
                amount = amount_total.toString()
            }catch (ex : Exception){}
        }

        startPayment()
    }

    fun startPayment() {
        /**
         * Instantiate Checkout
         */
        val checkout: Checkout = Checkout()

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            val options: JSONObject = JSONObject()

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Medideals.co.in")

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "total items: $items")
            //   options.put("order_id", "order_9A33XWu170gUtm")
            options.put("currency", "INR")

            val method: JSONObject = JSONObject()
            method.put("CARD", "false")
            method.put("emi", "false")
           /* method.put("CARD", "false")
            method.put("EMI", "false")
            method.put("WALLET", "true")
            method.put("UPI", "true")
            method.put("NETBANKING", "true")*/
            options.put("method", method)

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", "$amount")

            val preFill: JSONObject = JSONObject()
            preFill.put("email", email)
            preFill.put("contact", contact )
            options.put("prefill", preFill)





            checkout.open(this, options)


        } catch (e: Exception) {
            Log.e("RazorPayment", "Error in starting Razorpay Checkout", e)
        }
    }


    override fun onPaymentError(code: Int, response: String?) {
        try {

            Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show()
            Log.e("RazorPayment", "$code $response")

            val intent = Intent()
            intent.putExtra("status", "failure")
            setResult(0, intent)

            onBackPressed()

        } catch (e: Exception) {
            Log.e("RazorPayment", "Exception in onPaymentError", e)
        }

    }

    override fun onPaymentSuccess(razorpayPaymentID: String?) {
        try {

            Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()

            val intent = Intent()
            intent.putExtra("status", "success")
            intent.putExtra("trans_id", razorpayPaymentID)
            setResult(0, intent)

            onBackPressed()

        } catch (e: Exception) {
            Log.e("RazorPayment", "Exception in onPaymentSuccess", e)
        }

    }
}
