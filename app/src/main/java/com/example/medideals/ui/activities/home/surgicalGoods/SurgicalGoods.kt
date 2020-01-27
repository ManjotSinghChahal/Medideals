package com.example.medideals.ui.activities.home.surgicalGoods


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


class SurgicalGoods : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_surgical_goods, container, false)

        GlobalHelper.setToolbar(getString(R.string.surgical_goods),homeMenuIconVis= true,searchFilterHomeVis = true)

        initialize(view)


        return view
    }

    fun initialize(view : View)
    {

        val recyclerview_surgicalGoods= view.findViewById(R.id.recyclerview_surgicalGoods) as RecyclerView


       /* recyclerview_surgicalGoods.layoutManager =  LinearLayoutManager(activity)
        activity?.let {
            recyclerview_surgicalGoods.adapter = Allopathic_Adapter(it, allopathicModel)
        }*/
    }


}
