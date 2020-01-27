package com.example.medideals.ui.activities.login.chooseAccountType


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import com.example.medideals.R
import com.example.medideals.data.saveData.AddInfoData
import com.example.medideals.ui.activities.login.chooseCategory.ChooseCategory
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.INFO_DATA
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_choose_account_type.view.*


class ChooseAccountType : Fragment() {

    var infoData: AddInfoData? = null
    var accCount: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_choose_account_type, container, false)


        initialize(view)
        clickListener(view)

        return view
    }

    fun initialize(view: View) {
        val bundle = arguments
        if (bundle != null && bundle.containsKey(INFO_DATA)) {
            infoData = bundle.getParcelable(INFO_DATA)

            infoData!!.acc_wholeseller = false
            infoData!!.acc_retailer = false
            infoData!!.acc_pcd = false
            infoData!!.acc_thirdParty = false
            infoData!!.acc_fmcg = false
            infoData!!.acc_doctor = false

            accCount = 0

        }


    }


    fun clickListener(view: View) {

        view.img_backBtn_chooseAccType.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }

        view.proceedNext_accType.setOnClickListener {
            activity?.let {


                if (view.img_wholeSeller_chooseAccType.visibility == View.VISIBLE)
                    infoData!!.acc_wholeseller = true
                if (view.img_retailer_chooseAccType.visibility == View.VISIBLE)
                    infoData!!.acc_retailer = true
                if (view.img_pcd_chooseAccType.visibility == View.VISIBLE)
                    infoData!!.acc_pcd = true
                if (view.img_thirdParty_chooseAccType.visibility == View.VISIBLE)
                    infoData!!.acc_thirdParty = true
                if (view.img_fmcg_chooseAccType.visibility == View.VISIBLE)
                    infoData!!.acc_fmcg = true
                if (view.img_doctor_chooseAccType.visibility == View.VISIBLE)
                    infoData!!.acc_doctor = true

                val frag = ChooseCategory()
                val bundle = Bundle()
                bundle!!.putParcelable(INFO_DATA,infoData)
                frag.arguments = bundle
                GlobalHelper.replaceFragment(it, R.id.container_login, frag)
            }
        }


        view.wholeseller_chooseAccType.setOnClickListener {
            showHide(view.img_wholeSeller_chooseAccType, view)
        }

        view.retailer_chooseAccType.setOnClickListener {
            showHide(view.img_retailer_chooseAccType, view)
        }

        view.company_chooseAccType.setOnClickListener {
            showHide(view.img_pcd_chooseAccType, view)
        }

        view.thirdParty_chooseAccType.setOnClickListener {
            showHide(view.img_thirdParty_chooseAccType, view)
        }

        view.fmcg_chooseAccType.setOnClickListener {
            showHide(view.img_fmcg_chooseAccType, view)
        }

        view.doctor_chooseAccType.setOnClickListener {
            showHide(view.img_doctor_chooseAccType, view)
        }


    }


    fun showHide(view: View, itemView: View) {

        if (view.visibility == View.VISIBLE) {
            view.visibility = View.GONE
            if (accCount > 0) accCount--
        } else {
            if (accCount >= 2) {
                Toast.makeText(activity, "You can choose maximum two account types ", Toast.LENGTH_SHORT).show()
            } else {
                accCount++
                view.visibility = View.VISIBLE
            }


        }

        if (accCount > 0) itemView.proceedNext_accType.visibility = View.VISIBLE
        else itemView.proceedNext_accType.visibility = View.GONE


    }

}
