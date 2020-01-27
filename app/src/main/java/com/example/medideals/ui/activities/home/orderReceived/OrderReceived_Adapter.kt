package com.example.medideals.ui.activities.home.orderReceived

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.utils.Constants.CHANGE_ORDER_STATUS
import com.example.medideals.utils.Constants.EDIT_DOC_NUM
import kotlinx.android.synthetic.main.fragment_add_product.view.*
import kotlinx.android.synthetic.main.orderplaced_adpt.view.*
import kotlinx.android.synthetic.main.orderreceived_adpt.view.*


class OrderReceived_Adapter(val context: Context, val callback : OrderRecInterface) :
    PagedListAdapter<OrderReceivedResult, OrderReceived_Adapter.MyViewHolder>(
        DiffUtilCallBackOrderRec()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.orderreceived_adpt, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }

        holder.changeOrderStatus.setOnClickListener {
            callback.OnOrderRecSuccess(CHANGE_ORDER_STATUS,getItem(position)!!.list_id,"1")
        }

        holder.editDocNum.setOnClickListener {
            callback.OnOrderRecSuccess(EDIT_DOC_NUM,getItem(position)!!.list_id,"1234")
        }


    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val changeOrderStatus = itemView.findViewById(R.id.relChangeOrderStatus) as RelativeLayout
        val editDocNum = itemView.findViewById(R.id.relEditDocNum) as RelativeLayout

        fun bindPost(orederRec: OrderReceivedResult) {
            with(orederRec) {

                itemView.orderNum_OrderRec.text = order_id
                itemView.custNum_OrderRec.text = customer_number
                itemView.date_OrderRec.text = date
                itemView.orderStatus_OrderRec.text = order_status
                itemView.docNum_OrderRec.text = docket_number
                itemView.moneyStatus_orderRec.text = money_status

                itemView.billTo_orderRec.text = details.billTo
                itemView.billToAdd_orderRec.text = details.bill_address
                itemView.shipTo_orderRec.text = details.shipTo
                itemView.shipToAdd_orderRec.text = details.shipaddress
                itemView.email_orderRec.text = details.email

                itemView.prodName_orderRec.text = details.product_name
                itemView.prodQty_orderRec.text = details.quantity
                itemView.company_orderRec.text = details.company
                itemView.prodPrice_orderRec.text = "Rs. ${details.per_price}"
                itemView.prodTotalAmt_orderRec.text = "Rs. ${details.total_price}"

                itemView.rel_expand_orderReceived.setOnClickListener {


                    if (itemView.arrow_down_orderReceived.visibility == View.VISIBLE) {
                        itemView.arrow_down_orderReceived.visibility = View.GONE
                        itemView.arrow_up_orderReceived.visibility = View.VISIBLE
                        itemView.lin_orderDetails_orderReceived.visibility = View.VISIBLE
                        itemView.view_orderReceived.visibility = View.GONE
                    } else {
                        itemView.arrow_down_orderReceived.visibility = View.VISIBLE
                        itemView.arrow_up_orderReceived.visibility = View.GONE
                        itemView.lin_orderDetails_orderReceived.visibility = View.GONE
                        itemView.view_orderReceived.visibility = View.VISIBLE
                    }
                }




            }

        }


    }

    interface OrderRecInterface
    {
        fun OnOrderRecSuccess(type : String, list_id : String, status : String)
    }


}





