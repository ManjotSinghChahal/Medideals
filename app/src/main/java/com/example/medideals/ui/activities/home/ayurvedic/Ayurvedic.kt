package com.example.medideals.ui.activities.home.ayurvedic


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.utils.GlobalHelper


class Ayurvedic : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ayurvedic, container, false)

        GlobalHelper.setToolbar(getString(R.string.ayurvedic),homeMenuIconVis= true,searchFilterHomeVis = true)

        initialize(view)

        return view
    }


    fun initialize(view : View)
    {

        val recyclerviewViewCart= view.findViewById(R.id.recyclerview_ayurvedic) as RecyclerView


        recyclerviewViewCart.layoutManager =  LinearLayoutManager(activity)
        activity?.let {
            recyclerviewViewCart.adapter = Ayurvedic_Adapter(it)
        }
    }


}
