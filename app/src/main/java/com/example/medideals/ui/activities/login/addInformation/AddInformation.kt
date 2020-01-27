package com.example.medideals.ui.activities.login.addInformation


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.medideals.R
import com.example.medideals.data.saveData.AddInfoData
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.login.LoginContract
import com.example.medideals.ui.activities.login.LoginPresenterImp
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.INFO_DATA
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import com.example.medideals.utils.Validator
import kotlinx.android.synthetic.main.fragment_add_information.view.*
import org.json.JSONObject


class AddInformation : Fragment() , LoginContract.AddInfoView {

    var presenter: LoginContract.LoginPresenter? = null
    var user_id : String = ""
    var user_type : String = ""
    var cat_id : String = ""
    var infoData: AddInfoData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_information, container, false)

        initialize(view)
        clickListener(view)



        return view
    }

    fun initialize(view : View)
    {
        presenter = LoginPresenterImp(this)

        val bundle = arguments
        if (bundle!!.containsKey(INFO_DATA))
        {


        infoData = bundle!!.getParcelable(INFO_DATA)

            user_id = infoData!!.user_id

        if (infoData!!.cat_allopathic)
            cat_id = "1"
        if (infoData!!.cat_ayurvedic)
        {
            if (cat_id.equals(""))
                cat_id = "2"
            else
                cat_id = cat_id+",2"
        }
        if (infoData!!.cat_fmcg)
        {
            if (cat_id.equals(""))
                cat_id = "3"
            else
                cat_id = cat_id+",3"
        }
        if (infoData!!.cat_thirdParty)
        {
            if (cat_id.equals(""))
                cat_id = "4"
            else
                cat_id = cat_id+",4"
        }
        if (infoData!!.cat_surgicalGoods)
        {
            if (cat_id.equals(""))
                cat_id = "5"
            else
                cat_id = cat_id+",5"
        }
         if (infoData!!.cat_generics)
        {
            if (cat_id.equals(""))
                cat_id = "6"
            else
                cat_id = cat_id+",6"
        }

        if (infoData!!.acc_wholeseller)
            user_type = "Wholeseller"
        else if (infoData!!.acc_retailer)
            user_type = "Retailer"
        else if (infoData!!.acc_pcd)
            user_type = "PCD Company"
        else if (infoData!!.acc_thirdParty)
            user_type = "Third Party Manufacturer"
        else if (infoData!!.acc_fmcg)
            user_type = "FMCG"
        else if (infoData!!.acc_doctor)
            user_type = "Doctor"




        }
    }

    fun clickListener(view : View)
    {

        view.signup_addInfo.setOnClickListener {

            if (Validator.getsInstance().addInfoValidator(
                    view.business_AddInfo.text.toString().trim(),
                    view.shop_AddInfo.text.toString().trim(),
                    view.street_AddInfo.text.toString().trim(),
                    view.city_AddInfo.text.toString().trim(),
                    view.lic_AddInfo.text.toString().trim(),
                    view.gst_AddInfo.text.toString().trim(),
                    view.rootAddInfo, activity as AppCompatActivity))
            {
                GlobalHelper.showProgress(activity as AppCompatActivity)
                presenter?.addInfo(user_id,user_type,cat_id,view.business_AddInfo.text.toString().trim(),view.shop_AddInfo.text.toString().trim(),
                view.street_AddInfo.text.toString().trim(),view.city_AddInfo.text.toString().trim(),view.lic_AddInfo.text.toString().trim(), view.gst_AddInfo.text.toString().trim())
        }
        }
    }

    override fun onAddInfoViewSuccess(jsonData: JSONObject) {

      //  Response{protocol=http/1.1, code=200, message=OK, url=http://medideals.co.in/cdg/medideals/Api/addInformation}
        try {
            GlobalHelper.hideProgress()
            SharedPrefUtil.getInstance()!!.setLogin(true)
            SharedPrefUtil.getInstance()!!.saveNotification(true)
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
        activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }catch (ex : Exception){}
    }

    override fun onFailure(error: String) {

        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootAddInfo,it) }
        }catch (ex : Exception){}
    }


}
