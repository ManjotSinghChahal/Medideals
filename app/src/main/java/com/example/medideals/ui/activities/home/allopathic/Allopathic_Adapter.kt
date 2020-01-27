package com.example.medideals.ui.activities.home.allopathic

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.allopathicModel.AllopathicModel
import com.example.medideals.data.model.allopathicModel.Result
import com.example.medideals.data.model.orderReceivedModel.OrderReceivedResult
import com.example.medideals.ui.activities.home.orderReceived.DiffUtilCallBackOrderRec
import com.example.medideals.ui.activities.home.viewCart.ViewCart
import com.example.medideals.utils.Constants
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.allopathic_adpt.view.*
import kotlinx.android.synthetic.main.hotdeals_adpt.view.*
import kotlinx.android.synthetic.main.orderreceived_adpt.view.*


class Allopathic_Adapter(val context: Context, val callback : AllopathicInterface) :
    PagedListAdapter<Result, Allopathic_Adapter.MyViewHolder>(
        DiffUtilcallbackAllopathic()
    ) {

    private val dataSet = arrayListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.allopathic_adpt, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }


        if (getItem(position)!!.product_status.equals("not_added"))
        {
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.relLay_cartStatusAllopathic.background = context.resources.getDrawable(R.drawable.rounded_theme_full)
            } else {
                holder.relLay_cartStatusAllopathic.background = context.resources.getDrawable(R.drawable.rounded_theme_full)
            }
            holder.cartStatusAllopathic.text = context.getString(R.string.buy_now)
        }
        else
        {
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                holder.relLay_cartStatusAllopathic.background = context.resources.getDrawable(R.drawable.rounded_green)
            } else {
                holder.relLay_cartStatusAllopathic.background = context.resources.getDrawable(R.drawable.rounded_green)
            }
            holder.cartStatusAllopathic.text = context.getString(R.string.go_to_cart)
        }

        holder.relLay_cartStatusAllopathic.setOnClickListener {

             if (getItem(position)!!.product_status.equals("not_added"))
            {
                callback.OnAllopathicSuccess(getItem(position)!!.product_id,getItem(position)!!.minquantity)
            }
            else
             {
                 GlobalHelper.replaceFragment(context, R.id.container_home, ViewCart())
             }


        }


    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val relLay_cartStatusAllopathic = itemView.findViewById(R.id.relLay_cartStatus_allopathic) as RelativeLayout
        val cartStatusAllopathic = itemView.findViewById(R.id.cartStatus_allopathic) as TextView

        fun bindPost(allopathic: Result ) {
            with(allopathic) {

                itemView.disPrice_allopathic.text = "Rs. ${price}"
                itemView.mrpPrice_allopathic.text = "Rs. ${old_price}"
                itemView.mrpPrice_allopathic.paintFlags = itemView.mrpPrice_allopathic.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG
                itemView.dis_allopathic.text = "${discount}%"

                itemView.title_allopathic.text = title
                itemView.brandName_allopathic.text = barnd_name
                itemView.minQty_allopathic.text = minquantity




            }
        }
    }

    interface AllopathicInterface
    {
        fun OnAllopathicSuccess(product_id : String,qty : String)
    }

    override fun submitList(pagedList: PagedList<Result>?) {
        pagedList?.addWeakCallback(listOf(), object : PagedList.Callback() {
            override fun onChanged(position: Int, count: Int) {
                dataSet.clear()
                dataSet.addAll(pagedList)
            }

            override fun onInserted(position: Int, count: Int) {
                dataSet.clear()
                dataSet.addAll(pagedList)
            }

            override fun onRemoved(position: Int, count: Int) {
                dataSet.clear()
                dataSet.addAll(pagedList)
            }
        })
        super.submitList(pagedList)
    }

}







/*
class Allopathic_Adapter(
    val context: Context
) : RecyclerView.Adapter<Allopathic_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.allopathic_adpt, container, false))
    }

    override fun getItemCount(): Int {
        return allopathicModel.result.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews() {

            val mrpPrice_allopathic: TextView = itemView.findViewById(R.id.mrpPrice_allopathic) as TextView

            mrpPrice_allopathic.paintFlags = mrpPrice_allopathic.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG

        }
    }

}*/
