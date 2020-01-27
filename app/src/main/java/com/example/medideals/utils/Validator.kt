package com.example.medideals.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.medideals.R
import com.example.medideals.data.saveData.AddProductModel

class Validator {



    fun registerValidator(username : String, email: String, mobile: String, root: View, context: Context): Boolean {

        if (username.trim().isEmpty()) {
            context.getString(R.string.name_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        else if (mobile.trim().isEmpty()) {
            context.getString(R.string.ph_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (mobile.length<10) {
            context.getString(R.string.ph_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        else if (email.trim().isEmpty()) {
            context.getString(R.string.email_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            context.getString(R.string.email_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        return true
    }


    fun addEnquireValidator(email: String, msg: String, root: View, context: Context): Boolean {


         if (email.trim().isEmpty()) {
            context.getString(R.string.email_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            context.getString(R.string.email_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (msg.trim().isEmpty()) {
             context.getString(R.string.msg_empty)?.let { GlobalHelper.snackBar(root, it) }
             return false
         }

        return true
    }

    fun contactUsValidator(username : String, mobile : String, email: String , msg: String, root: View, context: Context): Boolean {

        if (username.trim().isEmpty()) {
            context.getString(R.string.name_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        else if (mobile.trim().isEmpty()) {
            context.getString(R.string.ph_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (mobile.length<10) {
            context.getString(R.string.ph_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        else if (email.trim().isEmpty()) {
            context.getString(R.string.email_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            context.getString(R.string.email_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (msg.trim().isEmpty()) {
            context.getString(R.string.msg_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        return true
    }




    fun addInfoValidator(business_name : String, shop_no: String, street_name: String, city: String, lic_number: String,
                         gst_number: String, root: View, context: Context): Boolean {

        if (business_name.trim().isEmpty()) {
            context.getString(R.string.businessname_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (shop_no.trim().isEmpty()) {
            context.getString(R.string.shopNo_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (street_name.trim().isEmpty()) {
            context.getString(R.string.streetname_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (city.trim().isEmpty()) {
            context.getString(R.string.cityname_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (lic_number.trim().isEmpty()) {
            context.getString(R.string.licnum_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (gst_number.trim().isEmpty()) {
            context.getString(R.string.gstnum_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        return true
    }




    fun addProductValidator(addProdModel: AddProductModel, root: View, context: Context): Boolean {

        if (addProdModel.prodName.isEmpty()) {
            context.getString(R.string.prod_name)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodDesc.isEmpty()) {
            context.getString(R.string.prod_desc)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodCat.isEmpty() || addProdModel.prodCat.equals("Select product category")) {
            context.getString(R.string.sel_prod_cat)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodPic.isEmpty()) {
            context.getString(R.string.choose_files)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodRetailPrc.isEmpty()) {
            context.getString(R.string.max_retailPrice)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodDisPrc.isEmpty()) {
            context.getString(R.string.disPrice)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodTotalDis.isEmpty()) {
            context.getString(R.string.totalDis_price)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodInvQty.isEmpty()) {
            context.getString(R.string.inv_qty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodOrderQty.isEmpty()) {
            context.getString(R.string.min_order_qty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (addProdModel.prodCompName.isEmpty()) {
            context.getString(R.string.company_name)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }

        return true
    }









/*    fun emailValidator(email: String, root: View, context: Context): Boolean {
        if (email.trim().isEmpty()) {
            context.getString(R.string.email_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            context.getString(R.string.email_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        return true
    }

    fun passwordValidator(password: String, root: View, context: Context): Boolean {
        if (password.trim ().isEmpty()) {
            context.getString(R.string.password_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (password.trim ().length<6) {
            context.getString(R.string.password_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }


        return true
    }
    fun nameValidator(name: String, root: View, context: Context): Boolean {
        if (name.trim ().isEmpty()) {
            context.getString(R.string.name_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        else if (name.trim ().length<3) {
            context.getString(R.string.name_invalid)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        return true
    }

    fun otpValidator(otpLength: Int, root: View, context: Context): Boolean {
        if (otpLength<4) {
            context.getString(R.string.enter_otp_full)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        return true
    }

    fun bdayValidator(bday: String, root: View, context: Context): Boolean {
        if (bday.isEmpty()) {
            context.getString(R.string.select_dob)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        return true
    }



    fun imageValidator(image: String, root: View, context: Context): Boolean {
        if (image.trim ().isEmpty()) {
            context.getString(R.string.image_empty)?.let { GlobalHelper.snackBar(root, it) }
            return false
        }
        return true
    }*/




    companion object {
        private var sInstance: Validator? = null

        fun getsInstance(): Validator {
            if (sInstance == null) {
                sInstance = Validator()
            }
            return sInstance as Validator
        }
    }
}
