package com.example.medideals.ui.activities.home.orderPlaced


import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog

import com.example.medideals.R
import com.example.medideals.data.model.changeOrderStatus.ChangeOrderStatus
import com.example.medideals.data.model.editDocNumber.EditDocNumber
import com.example.medideals.data.model.orderPlaced.OrderPlacedModel
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.activities.home.orderReceived.OrderReceived
import com.example.medideals.utils.Constants.LAST_ORDER
import com.example.medideals.utils.Constants.ORDERS_PLACED
import com.example.medideals.utils.Constants.SEARCH_QUERY
import com.example.medideals.utils.Constants.TOTAL_ORDERS_PLACED
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_order_placed.view.*
import kotlinx.android.synthetic.main.fragment_order_received.view.*


class OrderPlaced : Fragment() , HomeContract.OrderPlacedView, OrderPlaced_Adapter.OrderPlacedInterface {



    var presenter: HomeContract.HomePresenter? = null
    var recyclerviewOrderPlaced: RecyclerView? = null
    var orderPlacedViewModel: OrderPlacedViewModel? = null
    var orderPlaced_Adapter: OrderPlaced_Adapter? = null

    var searchQuery : String = ""
    var title : String = ""

    companion object {
        var currentPageOrderPlaced: Int = 1

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_order_placed, container, false)



        initialize(view)
        clickListener(view)

      /*  http://maps.google.com/maps/api/staticmap?center=48.858235,2.294571&zoom=15&size=200x200&sensor=false&key=AIzaSyBekuoU7PS1tJHWtuBx5dlwg33T96UK2oE
        http://maps.google.com/maps/api/staticmap?center=30.704649,76.717873&zoom=15&size=200x200&sensor=false&key=AIzaSyBekuoU7PS1tJHWtuBx5dlwg33T96UK2oE
        https://maps.googleapis.com/maps/api/staticmap?center=40.714%2c%20-73.998&zoom=12&size=400x400&key=AIzaSyBekuoU7PS1tJHWtuBx5dlwg33T96UK2oE

        https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/-122.4241,37.78,14.25,0,60/600x600?access_token=pk.eyJ1IjoibWFuam90Y2hhaGFsIiwiYSI6ImNqemxnaTZxbTA0bXQzZHQ2aHBsMnR6bHYifQ.64lqpvxAHhQzp4KMbpX3qw
       */
        return view
    }

    fun initialize(view : View)
    {
        currentPageOrderPlaced = 1

        orderPlacedViewModel = ViewModelProviders.of(this).get(OrderPlacedViewModel::class.java)

        val bundle = arguments
        if (bundle!=null && bundle.containsKey(TOTAL_ORDERS_PLACED))
        {
            title = TOTAL_ORDERS_PLACED
            GlobalHelper.setToolbar(getString(R.string.total_orders_placed),homeBackIconVis= true)
        }
        else if (bundle!=null && bundle.containsKey(LAST_ORDER))
        {
            title = LAST_ORDER
            GlobalHelper.setToolbar(getString(R.string.last_order),homeBackIconVis= true)
        }
        else
            GlobalHelper.setToolbar(getString(R.string.order_placed),homeBackIconVis= true)

        observeLiveData("")

      //  GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter = HomePresenterImp(this)
       // presenter?.orderPlaced(SharedPrefUtil.getInstance()!!.getUserId().toString(),"1")

        recyclerviewOrderPlaced = view.findViewById(R.id.recyclerview_orderPlaced) as RecyclerView
        recyclerviewOrderPlaced?.layoutManager =  LinearLayoutManager(activity)
       // orderPlaced_Adapter = activity?.let { OrderPlaced_Adapter(it, this) }
        orderPlaced_Adapter = activity?.let { OrderPlaced_Adapter(it,this) }
        recyclerviewOrderPlaced!!.adapter = orderPlaced_Adapter
        /*activity?.let {
            recyclerviewOrderPlaced.adapter = OrderPlaced_Adapter(it)
        }*/

        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }
    }

    fun clickListener(view : View)
    {
        HomeActivity.searchHome.setOnClickListener {
            HomeActivity.relLaySearchHome.visibility = View.VISIBLE
        }

        HomeActivity.relHomeSearchBack.setOnClickListener {

            HomeActivity.relLaySearchHome.visibility = View.GONE
            GlobalHelper.hideKeyBoard(activity as AppCompatActivity,view!!.rootOrderPlaced)
            HomeActivity.relHomeSearchEdt.setText("")
            searchQuery = ""

            val bundle = Bundle()
            bundle.putString(title,title)
            bundle.putString(SEARCH_QUERY,"")
            val frag = OrderPlaced()
            frag.arguments = bundle
            activity!!.supportFragmentManager.popBackStack()

            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.container_home,
                frag
            ).addToBackStack(null).commit()

        }

        HomeActivity.relHomeSearchClose.setOnClickListener {
            HomeActivity.relHomeSearchEdt.setText("")
        }



        HomeActivity.relHomeSearchEdt.setOnEditorActionListener { txtview, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                //  HomeActivity.relLaySearchHome.visibility = View.GONE
                GlobalHelper.hideKeyBoard(activity as AppCompatActivity,view!!.rootOrderPlaced)
                searchQuery = txtview.text.toString().trim()

                val bundle = Bundle()
                bundle.putString(title,title)
                bundle.putString(SEARCH_QUERY,"")
                val frag = OrderPlaced()
                frag.arguments = bundle
                activity!!.supportFragmentManager.popBackStack()

                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.container_home,
                    frag
                ).addToBackStack(null).commit()

            }
            true
        }

    }

    override fun onOrderPlacedViewSuccess(orderPlaced: OrderPlacedModel) {

        try {
            GlobalHelper.hideProgress()
            if (orderPlaced.status.equals("1"))
            {
               /* activity?.let {
                    if (orderPlaced!=null && orderPlaced.result.size>0)
                    recyclerviewOrderPlaced?.adapter = OrderPlaced_Adapter(it,orderPlaced)
                }*/
            }

        }catch (ex : Exception){}

    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootOrderPlaced, it) }
        } catch (ex: Exception) {
        }
    }


    private fun observeLiveData(search_query : String) {
        //observe live data emitted by view model
        orderPlacedViewModel!!.getPosts(search_query).observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            orderPlaced_Adapter?.let { it.submitList(items) }
        })

    }


    fun updateOrderStatus(list_id : String)
    {
        MaterialDialog.Builder(activity as AppCompatActivity)
            .title(getString(R.string.change_order_status))
            // .content("Enter")
            .inputType(InputType.TYPE_CLASS_TEXT)
            .positiveText(getString(R.string.save))
            .negativeText(getString(R.string.cancel))
            .input("","", object : MaterialDialog.InputCallback
            {
                override fun onInput(dialog: MaterialDialog, input: CharSequence?) {

                    if (input.toString().trim().equals(""))
                    {
                        GlobalHelper.snackBar(view!!.rootOrderReceived,"Please enter text")
                    }
                    else
                    {
                        GlobalHelper.showProgress(activity as AppCompatActivity)
                        presenter!!.changeOrderStatus(ORDERS_PLACED,list_id,input.toString().trim())
                    }


                }

            }).show()
    }

    override fun onChangeOrderStatusViewSuccess(changeOrderStatus: ChangeOrderStatus) {
        try {
            GlobalHelper.hideProgress()
            GlobalHelper.snackBar(view!!.rootOrderPlaced, changeOrderStatus.message)

            activity?.let { it.onBackPressed()
                GlobalHelper.replaceFragment(it,R.id.container_home, OrderPlaced()) }
        }catch (ex : Exception){}
    }

    override fun OnOrderPlacedSuccess(list_id: String, status: String) {
        updateOrderStatus(list_id)
    }
}
