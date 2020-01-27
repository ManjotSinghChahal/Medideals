package com.example.medideals.ui.activities.home.generics


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.ui.activities.home.allopathic.Allopathic_Adapter
import com.example.medideals.utils.GlobalHelper


class Generics : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_generics, container, false)


        GlobalHelper.setToolbar(getString(R.string.generics),homeMenuIconVis= true,searchFilterHomeVis = true)

        initialize(view)


        return view
    }

    fun initialize(view : View)
    {

        val recyclerview_generics= view.findViewById(R.id.recyclerview_generics) as RecyclerView


        /*recyclerview_generics.layoutManager =  LinearLayoutManager(activity)
        activity?.let {
            recyclerview_generics.adapter = Allopathic_Adapter(it, allopathicModel)
        }*/
    }


}

