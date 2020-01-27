package com.example.medideals.ui.activities.home.homeFrag

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.homeListings.GetMostDiscounted
import com.example.medideals.ui.activities.home.productDetail.ProductDetail
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.GO_TO_CART
import com.example.medideals.utils.Constants.PRODUCT_QUANTITY
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.hotdeals_adpt.view.*
import kotlinx.android.synthetic.main.mostdis_adpt.view.*


class MostDis_Adapter(val context: Context, val mostDiscounted: List<GetMostDiscounted>, val callback : MostDis_Adapter.AddToCart) : RecyclerView.Adapter<MostDis_Adapter.ViewHolder>()

{
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.mostdis_adpt,container,false))
    }

    override fun getItemCount(): Int {
        return if (mostDiscounted.size >=4) 4 else mostDiscounted.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(mostDiscounted)
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        fun bindViews(mostDiscounted: List<GetMostDiscounted>)
        {
            val txt_disAmt_mostDis: TextView = itemView.findViewById(R.id.txt_disAmt_mostDis) as TextView
            val lin_prodDet_mostDis: LinearLayout = itemView.findViewById(R.id.lin_prodDet_mostDis) as LinearLayout

            itemView.txt_title_mostDis.text = mostDiscounted.get(adapterPosition).title
            itemView.txt_disAmt_mostDis.text = "Rs ${mostDiscounted.get(adapterPosition).old_price}"
            itemView.txt_amt_mostDis.text = "Rs ${mostDiscounted.get(adapterPosition).price}"
            itemView.txt_brand_mostDis.text = mostDiscounted.get(adapterPosition).barnd_name
            itemView.txt_dis_mostDis.text = "${mostDiscounted.get(adapterPosition).discount}%"

           // GlobalHelper.manageBlinkEffect(itemView.txt_dis_mostDis)

            if (mostDiscounted.get(adapterPosition).product_status.equals("not_added"))
            {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    itemView.rel_buyNow_mostDisProd.setBackgroundColor(ContextCompat.getColor(context, R.color.colortheme) );
                } else {
                    itemView.rel_buyNow_mostDisProd.setBackgroundColor(ContextCompat.getColor(context, R.color.colortheme));
                }
                itemView.txt_buyNow_mostDisProd.text = context.getString(R.string.buy_now)
            }
            else
            {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    itemView.rel_buyNow_mostDisProd.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade) )
                } else {
                    itemView.rel_buyNow_mostDisProd.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade))
                }
                itemView.txt_buyNow_mostDisProd.text = context.getString(R.string.go_to_cart)
            }

            txt_disAmt_mostDis.paintFlags = txt_disAmt_mostDis.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG

            lin_prodDet_mostDis.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.PRODUCT_ID,mostDiscounted.get(adapterPosition).product_id)
                bundle.putString(PRODUCT_QUANTITY,mostDiscounted.get(adapterPosition).minquantity)
                bundle.putString(Constants.PRODUCT_NAME,mostDiscounted.get(adapterPosition).title)
                val frag = ProductDetail()
                frag.arguments = bundle
                GlobalHelper.replaceFragment(context,R.id.container_home,frag)
            }

            itemView.rel_buyNow_mostDisProd.setOnClickListener {

                if (itemView.txt_buyNow_mostDisProd.text.toString().trim().equals(context.getString(R.string.buy_now)))
                {
                    itemView.rel_buyNow_mostDisProd.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade))
                    itemView.txt_buyNow_mostDisProd.text = context.getString(R.string.go_to_cart)
                    callback.onAddToCartSuccess(mostDiscounted.get(adapterPosition).product_id,mostDiscounted.get(adapterPosition).product_status,mostDiscounted.get(adapterPosition).minquantity)
                }
                else
                {
                    callback.onAddToCartSuccess("", GO_TO_CART,"")
                }

            }
        }
    }

    interface AddToCart
    {
        fun  onAddToCartSuccess(product_id : String, product_status : String,qty : String)
    }


}