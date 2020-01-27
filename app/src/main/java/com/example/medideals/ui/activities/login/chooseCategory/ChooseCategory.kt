package com.example.medideals.ui.activities.login.chooseCategory


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.medideals.R
import com.example.medideals.data.saveData.AddInfoData
import com.example.medideals.ui.activities.login.addInformation.AddInformation
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.INFO_DATA
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_choose_category.view.*


class ChooseCategory : Fragment() {

    var catCount: Int = 0
    var infoData: AddInfoData? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_choose_category, container, false)

        initialize(view)
        clickListener(view)






        return view
    }

    fun initialize(view: View) {
        catCount = 0
        val bundle = arguments
        if (bundle != null && bundle.containsKey(INFO_DATA)) {
             infoData = bundle.getParcelable(INFO_DATA)

            infoData!!.cat_allopathic = false
            infoData!!.cat_ayurvedic = false
            infoData!!.cat_fmcg = false
            infoData!!.cat_thirdParty = false
            infoData!!.cat_surgicalGoods = false
            infoData!!.cat_generics = false



        }


    }


    fun clickListener(view: View) {

        view.backBtn_chooseCategory.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }

        view.proceedNext_chooseCat.setOnClickListener {
            activity?.let {

                if (view.img_allopathic_chooseCat.visibility == View.VISIBLE)
                  infoData!!.cat_allopathic = true
                if (view.img_ayurvedic_chooseCategory.visibility == View.VISIBLE)
                    infoData!!.cat_ayurvedic = true
                if (view.img_fmcg_chooseCategory.visibility == View.VISIBLE)
                    infoData!!.cat_fmcg = true
                if (view.img_thirdParty_chooseCategory.visibility == View.VISIBLE)
                    infoData!!.cat_thirdParty = true
                if (view.img_surgicalFoods_chooseCategory.visibility == View.VISIBLE)
                    infoData!!.cat_surgicalGoods = true
                if (view.img_generics_chooseCategory.visibility == View.VISIBLE)
                    infoData!!.cat_generics = true



                val frag = AddInformation()
                val bundle = Bundle()
                bundle!!.putParcelable(INFO_DATA, infoData)
                frag.arguments = bundle
                GlobalHelper.replaceFragment(it, R.id.container_login, frag)

            }
        }


        view.allopathic_chooseCategory.setOnClickListener {
            showHide(view.img_allopathic_chooseCat, view)
        }

        view.ayurvedic_chooseCategory.setOnClickListener {
            showHide(view.img_ayurvedic_chooseCategory, view)
        }

        view.fmcg_chooseCategory.setOnClickListener {
            showHide(view.img_fmcg_chooseCategory, view)
        }

        view.thirdParty_chooseCategory.setOnClickListener {
            showHide(view.img_thirdParty_chooseCategory, view)
        }

        view.surgicalFoods_chooseCategory.setOnClickListener {
            showHide(view.img_surgicalFoods_chooseCategory, view)
        }

        view.generics_chooseCategory.setOnClickListener {
            showHide(view.img_generics_chooseCategory, view)
        }


    }


    fun showHide(view: View, itemView: View) {

        if (view.visibility == View.VISIBLE) {
            view.visibility = View.GONE
            if (catCount > 0) catCount--
        } else {
            catCount++
            view.visibility = View.VISIBLE
        }

        if (catCount > 0) itemView.proceedNext_chooseCat.visibility = View.VISIBLE
        else itemView.proceedNext_chooseCat.visibility = View.GONE


    }


}
