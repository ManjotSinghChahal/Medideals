package com.example.medideals.ui.activities.home.orderReceived


import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog

import com.example.medideals.R
import com.example.medideals.data.model.changeOrderStatus.ChangeOrderStatus
import com.example.medideals.data.model.editDocNumber.EditDocNumber
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.activities.home.orderReceived.orderDelivered.OrderDeliveredViewModel
import com.example.medideals.ui.activities.home.orderReceived.orderDispatch.OrderDispatchViewModel
import com.example.medideals.ui.activities.home.orderReceived.orderReturn.OrderReturnViewModel
import com.example.medideals.utils.Constants.CHANGE_ORDER_STATUS
import com.example.medideals.utils.Constants.EDIT_DOC_NUM
import com.example.medideals.utils.Constants.ORDER_RECEIVED
import com.example.medideals.utils.Constants.TOTAL_ORDERS_DELIVERED
import com.example.medideals.utils.Constants.TOTAL_ORDERS_DISPATCH
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RECEIVED
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RECEIVED_BUY
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RETURN
import com.example.medideals.utils.Constants.TOTAL_ORDERS_RETURN_BUY
import com.example.medideals.utils.Constants.TOTAL_PRODUCTS_RECEIVED
import com.example.medideals.utils.Constants.TOTAL_PRODUCTS_RETURN
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_order_received.view.*


class OrderReceived : Fragment(), OrderReceived_Adapter.OrderRecInterface , HomeContract.OrderReceivedView{



    lateinit var orderReceivedViewModel: OrderReceivedViewModel
    lateinit var orderdispatchViewModel: OrderDispatchViewModel
    lateinit var orderDeliveredViewModel: OrderDeliveredViewModel
    lateinit var orderReturnViewModel: OrderReturnViewModel

    var recyclerviewOrderReceived: RecyclerView? = null
    var orderReceived_Adapter : OrderReceived_Adapter? = null
    var presenter: HomeContract.HomePresenter? = null

    companion object
    {
        var currentPageOrderRec : Int = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_order_received, container, false)



        initialize(view)

        return view
    }


    fun initialize(view : View)
    {
        currentPageOrderRec = 1
        orderReceivedViewModel = ViewModelProviders.of(this).get(OrderReceivedViewModel::class.java)
        orderdispatchViewModel = ViewModelProviders.of(this).get(OrderDispatchViewModel::class.java)
        orderDeliveredViewModel = ViewModelProviders.of(this).get(OrderDeliveredViewModel::class.java)
        orderReturnViewModel = ViewModelProviders.of(this).get(OrderReturnViewModel::class.java)


        recyclerviewOrderReceived = view.findViewById(R.id.recyclerview_orderReceived) as RecyclerView
        recyclerviewOrderReceived!!.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        orderReceived_Adapter = activity?.let { OrderReceived_Adapter(it,this) }
        recyclerviewOrderReceived!!.adapter = orderReceived_Adapter


        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter = HomePresenterImp(this)


        val bundle = arguments
        if (bundle!=null && bundle.containsKey(TOTAL_ORDERS_RECEIVED))
        {
            GlobalHelper.setToolbar(getString(R.string.total_orders_rec),homeBackIconVis= true)
            observeLiveData(ORDER_RECEIVED)
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_ORDERS_DISPATCH))
        {
            GlobalHelper.setToolbar(getString(R.string.total_orders_dispatch),homeBackIconVis= true)
            observeDispatchLiveData()
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_ORDERS_DELIVERED))
        {
            GlobalHelper.setToolbar(getString(R.string.total_orders_del),homeBackIconVis= true)
            observeDeliveredLiveData()
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_ORDERS_RETURN))
        {
            GlobalHelper.setToolbar(getString(R.string.total_orders_return),homeBackIconVis= true)
            observeReturnLiveData()
        }
        //----------checked-------------------------
        else if (bundle!=null && bundle.containsKey(TOTAL_ORDERS_RECEIVED_BUY))
        {
            GlobalHelper.setToolbar(getString(R.string.total_orders_rec),homeBackIconVis= true)
            observeLiveData(TOTAL_ORDERS_RECEIVED_BUY)
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_ORDERS_RETURN_BUY))
        {
            GlobalHelper.setToolbar(getString(R.string.total_orders_return),homeBackIconVis= true)
            observeLiveData(TOTAL_ORDERS_RETURN_BUY)
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_PRODUCTS_RECEIVED))
        {
            GlobalHelper.setToolbar(getString(R.string.total_prod_rec),homeBackIconVis= true)
            observeLiveData(TOTAL_PRODUCTS_RECEIVED)
        }
        else if (bundle!=null && bundle.containsKey(TOTAL_PRODUCTS_RETURN))
        {
            GlobalHelper.setToolbar(getString(R.string.total_prod_return),homeBackIconVis= true)
            observeLiveData(TOTAL_PRODUCTS_RETURN)
        }

        else
        {
            GlobalHelper.setToolbar(getString(R.string.order_rec),homeBackIconVis= true)
            observeLiveData(ORDER_RECEIVED)
        }




        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }
    }

    private fun observeLiveData(screen: String) {
        //observe live data emitted by view model
        orderReceivedViewModel.getPosts(screen).observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            orderReceived_Adapter?.let { it.submitList(items) }
        })

    }

    private fun observeDispatchLiveData() {
        //observe live data emitted by view model
        orderdispatchViewModel.getPosts().observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            orderReceived_Adapter?.let { it.submitList(items) }
        })

    }

    private fun observeReturnLiveData() {
        //observe live data emitted by view model
        orderReturnViewModel.getPosts().observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            orderReceived_Adapter?.let { it.submitList(items) }
        })

    }

    private fun observeDeliveredLiveData() {
        //observe live data emitted by view model
        orderDeliveredViewModel.getPosts().observe(this, Observer { items ->
            GlobalHelper.hideProgress()
            orderReceived_Adapter?.let { it.submitList(items) }
        })

    }


    override fun OnOrderRecSuccess(type: String, list_id: String, status: String) {


        if (type.equals(CHANGE_ORDER_STATUS))
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
                            presenter!!.changeOrderStatus(ORDER_RECEIVED,list_id,input.toString().trim())
                        }


                    }

                }).show()

        }
        else if (type.equals(EDIT_DOC_NUM))
        {
            MaterialDialog.Builder(activity as AppCompatActivity)
                .title(getString(R.string.edit_doc_num))
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
                            presenter!!.editDocNum(list_id,input.toString().trim())
                        }
                    }

                }).show()
        }
    }

    override fun onChangeOrderStatusViewSuccess(changeOrderStatus: ChangeOrderStatus) {
        try {
            GlobalHelper.hideProgress()
            GlobalHelper.snackBar(view!!.rootOrderReceived, changeOrderStatus.message)

            activity?.let { it.onBackPressed()
                GlobalHelper.replaceFragment(it,R.id.container_home,OrderReceived()) }
        }catch (ex : Exception){}
    }

    override fun oneditDocNumViewSuccess(editDocNumber: EditDocNumber) {
        try {
            GlobalHelper.hideProgress()
            GlobalHelper.snackBar(view!!.rootOrderReceived, editDocNumber.message)

            activity?.let { it.onBackPressed()
                GlobalHelper.replaceFragment(it,R.id.container_home,OrderReceived()) }
        }catch (ex : Exception){}
    }
    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
            error.let { GlobalHelper.snackBar(view!!.rootOrderReceived, it) }
        } catch (ex: Exception) {
        }
    }
}
