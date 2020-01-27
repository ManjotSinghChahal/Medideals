package com.example.medideals.ui.activities.home.settings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.medideals.R
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_settings.view.*


class Settings : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)


        GlobalHelper.setToolbar(getString(R.string.settings),homeMenuIconVis= true)

        clickListener(view)

        if (SharedPrefUtil.getInstance()!!.getNotification()!!)
        {
            view.img_toggleLeft_settings.visibility = View.GONE
            view.img_toggleRight_settings.visibility = View.VISIBLE
        }
        else
        {
            view.img_toggleLeft_settings.visibility = View.VISIBLE
            view.img_toggleRight_settings.visibility = View.GONE
        }




        return view
    }


    fun clickListener(view : View)
    {
        view.rel_toggleLeft_settings.setOnClickListener {
            SharedPrefUtil.getInstance()!!.saveNotification(false)
            view.img_toggleLeft_settings.visibility = View.VISIBLE
            view.img_toggleRight_settings.visibility = View.GONE
        }

        view.rel_toggleRight_settings.setOnClickListener {
            SharedPrefUtil.getInstance()!!.saveNotification(true)
            view.img_toggleLeft_settings.visibility = View.GONE
            view.img_toggleRight_settings.visibility = View.VISIBLE
        }

    }


}
