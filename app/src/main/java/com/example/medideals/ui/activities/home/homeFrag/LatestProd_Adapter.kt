package com.example.medideals.ui.activities.home.homeFrag

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.homeListings.GetNewProduct
import com.example.medideals.ui.activities.home.productDetail.ProductDetail
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.GO_TO_CART
import com.example.medideals.utils.Constants.PRODUCT_QUANTITY
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.hotdeals_adpt.view.*
import kotlinx.android.synthetic.main.latestprod_adpt.view.*


class LatestProd_Adapter(val context: Context, val newProducts: List<GetNewProduct>,val callback : AddToCart) : RecyclerView.Adapter<LatestProd_Adapter.ViewHolder>()

{
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.latestprod_adpt,container,false))
    }

    override fun getItemCount(): Int {
        return if (newProducts.size >=4) 4 else newProducts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(newProducts)
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        fun bindViews(newProducts: List<GetNewProduct>)
        {
            val txt_disAmt_latestProd: TextView = itemView.findViewById(R.id.txt_disAmt_latestProd) as TextView
            val txt_buyNow_latProd: TextView = itemView.findViewById(R.id.txt_buyNow_latProd) as TextView
            val lin_prodDet_latestProd: LinearLayout = itemView.findViewById(R.id.lin_prodDet_latestProd) as LinearLayout
            val rel_buyNow_latProd: RelativeLayout = itemView.findViewById(R.id.rel_buyNow_latProd) as RelativeLayout


            txt_disAmt_latestProd.text = "Rs ${newProducts.get(adapterPosition).old_price}"
            itemView.txt_title_latestProd.text = newProducts.get(adapterPosition).title
            itemView.txt_amt_latestProd.text = "Rs ${newProducts.get(adapterPosition).price}"
            itemView.txt_brand_latestProd.text = newProducts.get(adapterPosition).barnd_name
            itemView.txt_dis_latestProd.text = "${newProducts.get(adapterPosition).discount}%"

           // GlobalHelper.manageBlinkEffect(itemView.txt_dis_latestProd)


            if (newProducts.get(adapterPosition).product_status.equals("not_added"))
            {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    rel_buyNow_latProd.setBackgroundColor(ContextCompat.getColor(context, R.color.colortheme) );
                } else {
                    rel_buyNow_latProd.setBackgroundColor(ContextCompat.getColor(context, R.color.colortheme));
                }
                txt_buyNow_latProd.text = context.getString(R.string.buy_now)
            }
            else
            {
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    rel_buyNow_latProd.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade) )
                } else {
                    rel_buyNow_latProd.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade))
                }
                txt_buyNow_latProd.text = context.getString(R.string.go_to_cart)
            }


            txt_disAmt_latestProd.paintFlags = txt_disAmt_latestProd.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG

            lin_prodDet_latestProd.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.PRODUCT_ID,newProducts.get(adapterPosition).product_id)
                bundle.putString(PRODUCT_QUANTITY,newProducts.get(adapterPosition).minquantity)
                bundle.putString(Constants.PRODUCT_NAME,newProducts.get(adapterPosition).title)
                val frag = ProductDetail()
                frag.arguments = bundle
                GlobalHelper.replaceFragment(context,R.id.container_home,frag)
            }

            itemView.rel_buyNow_latProd.setOnClickListener {

                if (itemView.txt_buyNow_latProd.text.toString().trim().equals(context.getString(R.string.buy_now)))
                {
                    itemView.rel_buyNow_latProd.setBackgroundColor(ContextCompat.getColor(context, R.color.color_green_shade))
                    itemView.txt_buyNow_latProd.text = context.getString(R.string.go_to_cart)
                    callback.onAddToCartSuccess(newProducts.get(adapterPosition).product_id,newProducts.get(adapterPosition).product_status,newProducts.get(adapterPosition).minquantity)
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