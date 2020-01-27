package com.example.medideals.ui.activities.home.viewCart

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.getCart.GetCart
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.viewcart_adpt.view.*
import java.lang.Exception


class ViewCart_Adapter(val context: Context, val getcart: GetCart, val callback: DelProdCall) :
    RecyclerView.Adapter<ViewCart_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.viewcart_adpt, container, false))
    }

    override fun getItemCount(): Int {
        return getcart.record.data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(getcart)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(getcart: GetCart) {
            itemView.productName_viewCart.text = getcart.record.data.get(adapterPosition).product_name
            itemView.productPrice_viewCart.text = "Rs ${GlobalHelper.roundUpto2Dec(getcart.record.data.get(adapterPosition).price )}"
            itemView.qty_viewCart.setText(getcart.record.data.get(adapterPosition).quantity)
            itemView.minQty_viewCart.text = getcart.record.data.get(adapterPosition).min_quantity


            itemView.delProduct_viewCart.setOnClickListener {

              val  animShake : Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
              itemView.delProduct_viewCart.startAnimation(animShake)

                callback.delProduct("delete",getcart.record.data.get(adapterPosition).product_id, "","")
            }

            itemView.plus_viewCart.setOnClickListener {
                try {
                    var qty = itemView.qty_viewCart.text.toString().trim().toInt()
                    qty = qty + 1
                    itemView.qty_viewCart.setText(qty.toString())
                    getcart.record.data.get(adapterPosition).quantity = qty.toString()

                    var record : String = ""
                    var amount : Double = 0.0
                    for (i in 0..getcart.record.data.size-1)
                    {
                        if (record.isEmpty())
                            record = "${getcart.record.data.get(i).product_id}-${getcart.record.data.get(i).quantity}"
                        else
                            record = "${record},${getcart.record.data.get(i).product_id}-${getcart.record.data.get(i).quantity}"

                        amount += (getcart.record.data.get(i).per_price.toDouble() * getcart.record.data.get(i).quantity.toDouble())
                    }

                    callback.delProduct("editCart",getcart.record.data.get(adapterPosition).product_id, record,amount.toString())

                    var updatePrice : Float = getcart.record.data.get(adapterPosition).per_price.toFloat()*getcart.record.data.get(adapterPosition).quantity.toFloat()
                    itemView.productPrice_viewCart.text = "Rs ${GlobalHelper.roundUpto2Dec(updatePrice.toString())}"


                }catch (ex : Exception){ }
            }

            itemView.minus_viewCart.setOnClickListener {
                try {
                    var qty = itemView.qty_viewCart.text.toString().trim().toInt()
                    qty = qty - 1
                    if (qty >= getcart.record.data.get(adapterPosition).min_quantity.toInt())
                    {
                        itemView.qty_viewCart.setText(qty.toString())
                        getcart.record.data.get(adapterPosition).quantity = qty.toString()
                    }


                    var record : String = ""
                    var amount : Double = 0.0
                    for (i in 0..getcart.record.data.size-1)
                    {
                        if (record.isEmpty())
                            record = "${getcart.record.data.get(i).product_id}-${getcart.record.data.get(i).quantity}"
                        else
                            record = "${record},${getcart.record.data.get(i).product_id}-${getcart.record.data.get(i).quantity}"

                        amount += (getcart.record.data.get(i).per_price.toDouble() * getcart.record.data.get(i).quantity.toDouble())
                    }
                    callback.delProduct("editCart",getcart.record.data.get(adapterPosition).product_id, record,amount.toString())

                    var updatePrice : Float = getcart.record.data.get(adapterPosition).per_price.toFloat()*getcart.record.data.get(adapterPosition).quantity.toFloat()
                    itemView.productPrice_viewCart.text = "Rs ${GlobalHelper.roundUpto2Dec(updatePrice.toString())}"

                }catch (ex : Exception){}
            }

            itemView.qty_viewCart.addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    try {
                        var qty = itemView.qty_viewCart.text.toString().trim()
                        getcart.record.data.get(adapterPosition).quantity = qty

                        var record : String = ""
                        var amount : Double = 0.0
                        for (i in 0..getcart.record.data.size-1)
                        {
                            if (record.isEmpty())
                                record = "${getcart.record.data.get(i).product_id}-${getcart.record.data.get(i).quantity}"
                            else
                                record = "${record},${getcart.record.data.get(i).product_id}-${getcart.record.data.get(i).quantity}"

                            amount += (getcart.record.data.get(i).per_price.toDouble() * getcart.record.data.get(i).quantity.toDouble())
                        }

                        callback.delProduct("editCart",getcart.record.data.get(adapterPosition).product_id, record,amount.toString())

                        var updatePrice : Float = getcart.record.data.get(adapterPosition).per_price.toFloat()*getcart.record.data.get(adapterPosition).quantity.toFloat()
                        itemView.productPrice_viewCart.text = "Rs ${updatePrice}"


                    }catch (ex : Exception){ }
                }
            })

        }
    }


    interface DelProdCall {
        fun delProduct(status : String, product_id: String, record: String,amount : String)
    }


}