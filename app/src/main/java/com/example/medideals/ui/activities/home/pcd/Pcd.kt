package com.example.medideals.ui.activities.home.pcd


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.medideals.R
import com.example.medideals.utils.GlobalHelper


class Pcd : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pcd, container, false)


        GlobalHelper.setToolbar(getString(R.string.pcd_party),homeMenuIconVis= true,searchFilterHomeVis = true)



        return view
    }


}
