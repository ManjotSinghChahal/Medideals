package com.example.medideals.ui.activities.home.showResponses


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.data.model.responses.Responses
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_show_responses.view.*


class ShowResponses : Fragment(), HomeContract.ResponsesView {


    var presenter: HomeContract.HomePresenter? = null
    var recyclerviewResponses : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_show_responses, container, false)

        GlobalHelper.setToolbar(getString(R.string.show_responses),homeBackIconVis= true)

        initialize(view)

        return view
    }


    fun initialize(view : View)
    {
        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter = HomePresenterImp(this)
        presenter?.responses()

        recyclerviewResponses = view.findViewById(R.id.recyclerview_showResponses) as RecyclerView
        recyclerviewResponses!!.layoutManager =  LinearLayoutManager(activity)


        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }
    }

    override fun onResponsesViewSuccess(responses: Responses) {
        try {
            GlobalHelper.hideProgress()
            activity?.let {
                recyclerviewResponses!!.adapter = ShowResponses_Adapter(it,responses)
            }
        } catch (ex: Exception) {
        }
    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
          //  error.let { GlobalHelper.snackBar(view!!.rootResponses, it) }
        } catch (ex: Exception) {
        }
    }
}
