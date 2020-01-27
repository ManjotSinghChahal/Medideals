package com.example.medideals.ui.activities.home.orderPlaced

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.CHANGE_ORDER_STATUS
import kotlinx.android.synthetic.main.orderplaced_adpt.view.*




class OrderPlaced_Adapter(val context: Context,val callback : OrderPlacedInterface) :
    PagedListAdapter<com.example.medideals.data.model.orderPlaced.Result, OrderPlaced_Adapter.MyViewHolder>(
        DiffUtilOrderPlaced()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.orderplaced_adpt, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }

        holder.changeOrderStatusOrderPlaced.setOnClickListener {
            callback.OnOrderPlacedSuccess(getItem(position)!!.details.productDetails.get(0).list_id,"1")
        }



    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val changeOrderStatusOrderPlaced = itemView.findViewById(R.id.changeOrderStatus_orderPlaced) as RelativeLayout

        fun bindPost(orderPlaced: com.example.medideals.data.model.orderPlaced.Result ) {
            with(orderPlaced) {

                itemView.invoiceNo_orderPlaced.text = orderPlaced.order_id
                itemView.date_orderPlaced.text = orderPlaced.date
                itemView.phone_orderPlaced.text = orderPlaced.phone
                itemView.totalAmt_orderPlaced.text = "Rs ${orderPlaced.total_amount}"
                // itemView.moneyStatus_orderPlaced.text = orderPlaced.result.get(adapterPosition).money_status

                itemView.billTo_orderPlaced.text = orderPlaced.details.billTo
                itemView.addressBillTo_orderPlaced.text = orderPlaced.details.bill_address
                itemView.shipTo_orderPlaced.text = orderPlaced.details.shipTo
                itemView.addressShipTo_orderPlaced.text = orderPlaced.details.shipaddress
                itemView.email_orderPlaced.text = orderPlaced.details.email
                itemView.totalPrice_orderPlaced.text = "Rs ${orderPlaced.details.total_price}"

                if (orderPlaced.details.productDetails.size>0)
                {
                    itemView.prodName_orderPlaced.text = orderPlaced.details.productDetails.get(0).product_name
                    itemView.prodPrice_orderPlaced.text = "Rs ${orderPlaced.details.productDetails.get(0).per_price}"
                    itemView.productQty_orderPlaced.text = orderPlaced.details.productDetails.get(0).quantity
                    itemView.productComp_orderPlaced.text = orderPlaced.details.productDetails.get(0).company
                    //  itemView.orderStatus_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.productDetails.get(0).
                    //   itemView.docketNo_orderPlaced.text = orderPlaced.result.get(adapterPosition).details
                }

                itemView.rel_expand_orderPlaced.setOnClickListener {


                    if (itemView.arrow_down_orderPlaced.visibility == View.VISIBLE) {
                        itemView.arrow_down_orderPlaced.visibility = View.GONE
                        itemView.arrow_up_orderPlaced.visibility = View.VISIBLE
                        itemView.lin_orderDetails_orderPlaced.visibility = View.VISIBLE
                        itemView.view_orderPlaced.visibility = View.GONE
                    } else {
                        itemView.arrow_down_orderPlaced.visibility = View.VISIBLE
                        itemView.arrow_up_orderPlaced.visibility = View.GONE
                        itemView.lin_orderDetails_orderPlaced.visibility = View.GONE
                        itemView.view_orderPlaced.visibility = View.VISIBLE
                    }
                }


            }
        }
    }

    interface OrderPlacedInterface
    {
        fun OnOrderPlacedSuccess(list_id : String, status : String)
    }


}


















































/*

class OrderPlaced_Adapter(
    val context: Context, val orderPlaced: OrderPlacedModel
) : RecyclerView.Adapter<OrderPlaced_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.orderplaced_adpt, container, false))
    }

    override fun getItemCount(): Int {
        return orderPlaced.result.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews() {

            itemView.invoiceNo_orderPlaced.text = orderPlaced.result.get(adapterPosition).order_id
            itemView.date_orderPlaced.text = orderPlaced.result.get(adapterPosition).date
            itemView.phone_orderPlaced.text = orderPlaced.result.get(adapterPosition).phone
            itemView.totalAmt_orderPlaced.text = orderPlaced.result.get(adapterPosition).total_amount
           // itemView.moneyStatus_orderPlaced.text = orderPlaced.result.get(adapterPosition).money_status

            itemView.billTo_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.billTo
            itemView.addressBillTo_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.bill_address
            itemView.shipTo_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.shipTo
            itemView.addressShipTo_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.shipaddress
            itemView.email_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.email
            itemView.totalPrice_orderPlaced.text = "Rs ${orderPlaced.result.get(adapterPosition).details.total_price}"

            if (orderPlaced.result.get(adapterPosition).details.productDetails.size>0)
            {
            itemView.prodName_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.productDetails.get(0).product_name
            itemView.prodPrice_orderPlaced.text = "Rs ${orderPlaced.result.get(adapterPosition).details.productDetails.get(0).per_price}"
            itemView.productQty_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.productDetails.get(0).quantity
            itemView.productComp_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.productDetails.get(0).company
          //  itemView.orderStatus_orderPlaced.text = orderPlaced.result.get(adapterPosition).details.productDetails.get(0).
         //   itemView.docketNo_orderPlaced.text = orderPlaced.result.get(adapterPosition).details
            } 

            itemView.rel_expand_orderPlaced.setOnClickListener {


                if (itemView.arrow_down_orderPlaced.visibility == View.VISIBLE) {
                    itemView.arrow_down_orderPlaced.visibility = View.GONE
                    itemView.arrow_up_orderPlaced.visibility = View.VISIBLE
                    itemView.lin_orderDetails_orderPlaced.visibility = View.VISIBLE
                    itemView.view_orderPlaced.visibility = View.GONE
                } else {
                    itemView.arrow_down_orderPlaced.visibility = View.VISIBLE
                    itemView.arrow_up_orderPlaced.visibility = View.GONE
                    itemView.lin_orderDetails_orderPlaced.visibility = View.GONE
                    itemView.view_orderPlaced.visibility = View.VISIBLE
                }
            }



        }
    }

}*/
