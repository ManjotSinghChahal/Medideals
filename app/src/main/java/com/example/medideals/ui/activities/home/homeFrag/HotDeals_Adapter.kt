package com.example.medideals.ui.activities.home.homeFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R

import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.medideals.data.model.homeListings.HotDealsProduct
import com.example.medideals.ui.activities.home.productDetail.ProductDetail
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.PRODUCT_ID
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.hotdeals_adpt.view.*
import android.R.attr.start
import android.view.animation.Animation
import android.animation.ObjectAnimator
import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.graphics.Color
import com.example.medideals.utils.Constants.GO_TO_CART
import com.example.medideals.utils.Constants.PRODUCT_QUANTITY


class HotDeals_Adapter(val context: Context,val hotDealsProducts: List<HotDealsProduct>, val callback : AddToCart) : RecyclerView.Adapter<HotDeals_Adapter.ViewHolder>()
{
    /*var hotDealsList : List<HotDealsProduct>? = null
    init {
        hotDealsList = hotDealsProducts
    }*/
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.hotdeals_adpt,container,false))
    }

    override fun getItemCount(): Int {
         return if (hotDealsProducts.size >=4) 4 else hotDealsProducts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(hotDealsProducts)
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        fun bindViews(hotDealsProducts: List<HotDealsProduct>)
        {
            val amountTrips: TextView = itemView.findViewById(R.id.txt_disAmt_hotDeals) as TextView
            val lin_prodDet_hotDeals: LinearLayout = itemView.findViewById(R.id.lin_prodDet_hotDeals) as LinearLayout

            itemView.prod_hotDeals.text = hotDealsProducts.get(adapterPosition).title
            itemView.txt_Amt_hotDeals.text = "Rs ${hotDealsProducts.get(adapterPosition).price}"
            itemView.txt_dis_hotDeals.text = "${hotDealsProducts.get(adapterPosition).discount}%"
            itemView.txt_brand_hotDeals.text = hotDealsProducts.get(adapterPosition).barnd_name
            amountTrips.text = "Rs ${hotDealsProducts.get(adapterPosition).old_price}"

          //  GlobalHelper.manageBlinkEffect(itemView.txt_dis_hotDeals)



            if (hotDealsProducts.get(adapterPosition).product_status.equals("not_added"))
            {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    itemView.rel_buyNow_hotDeals.setBackgroundColor(ContextCompat.getColor(context, R.color.colortheme) )
                } else {
                    itemView.rel_buyNow_hotDeals.setBackgroundColor(ContextCompat.getColor(context, R.color.colortheme))
                }
                itemView.txt_buyNow_hotDeals.text = context.getString(R.string.buy_now)
            }
            else
            {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    itemView.rel_buyNow_hotDeals.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade) )
                } else {
                    itemView.rel_buyNow_hotDeals.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade))
                }
                itemView.txt_buyNow_hotDeals.text = context.getString(R.string.go_to_cart)
            }


            amountTrips.paintFlags = amountTrips.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG

            lin_prodDet_hotDeals.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(PRODUCT_ID,hotDealsProducts.get(adapterPosition).product_id)
                bundle.putString(PRODUCT_QUANTITY,hotDealsProducts.get(adapterPosition).minquantity)
                bundle.putString(Constants.PRODUCT_NAME,hotDealsProducts.get(adapterPosition).title)
                val frag = ProductDetail()
                frag.arguments = bundle
                GlobalHelper.replaceFragment(context,R.id.container_home,frag)
           }


            itemView.rel_buyNow_hotDeals.setOnClickListener {

                if (itemView.txt_buyNow_hotDeals.text.toString().trim().equals(context.getString(R.string.buy_now)))
                {
                    itemView.rel_buyNow_hotDeals.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade))
                    itemView.txt_buyNow_hotDeals.text = context.getString(R.string.go_to_cart)
                    callback.onAddToCartSuccess(hotDealsProducts.get(adapterPosition).product_id,hotDealsProducts.get(adapterPosition).product_status,hotDealsProducts.get(adapterPosition).minquantity)
                }
                else{
                    callback.onAddToCartSuccess("",GO_TO_CART,"")
                }

            }
        }
    }

    interface AddToCart
    {
      fun  onAddToCartSuccess(product_id : String, product_status : String,qty: String)
    }
}