package com.example.medideals.ui.activities.home.allProducts

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.showAllProduct.ShowAllProduct
import com.example.medideals.data.model.showAllProduct.ShowAllProductResult
import com.example.medideals.data.saveData.SaveProductInf
import com.example.medideals.ui.activities.home.addProduct.AddProduct
import com.example.medideals.utils.Constants.PRODUCT_INFO
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.allproducts_adpt.view.*
import kotlinx.android.synthetic.main.viewcart_adpt.view.*


class AllProducts_Adapter(val context : Context, var callback : DelProduct) : PagedListAdapter<ShowAllProductResult, AllProducts_Adapter.MyViewHolder>(DiffUtilCallBack()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.allproducts_adpt, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }

        holder.relDelProduct.setOnClickListener {
           // val  animShake : Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
           // holder.relDelProduct.startAnimation(animShake)

            callback.OnDelProductSuccess(getItem(position)!!.product_id)
        }

        holder.editProd_AllProducts.setOnClickListener {

            val bundle = Bundle()
            val saveProductInfo = SaveProductInf()
            saveProductInfo.vendor_id = SharedPrefUtil.getInstance()!!.getUserId().toString()
            saveProductInfo.product_id = getItem(position)!!.product_id
            saveProductInfo.product_name = getItem(position)!!.product_name
            saveProductInfo.product_description = getItem(position)!!.description
            saveProductInfo.category = ""
            saveProductInfo.image = getItem(position)!!.image
            saveProductInfo.maximum_retail_Price = getItem(position)!!.mrp
            saveProductInfo.discounted_price = getItem(position)!!.new_price
            saveProductInfo.discount_percent = ""
            saveProductInfo.quantity = getItem(position)!!.quantity
            saveProductInfo.minquantity = ""
            saveProductInfo.company_name = ""
            bundle.putParcelable(PRODUCT_INFO,saveProductInfo)

            val frag = AddProduct()
            frag.arguments = bundle
            GlobalHelper.replaceFragment(context,R.id.container_home,frag)
        }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val relDelProduct = itemView.findViewById(R.id.delProd_allProduct) as RelativeLayout
        val editProd_AllProducts = itemView.findViewById(R.id.editProd_AllProducts) as RelativeLayout

        fun bindPost(showAllProduct : ShowAllProductResult){
            with(showAllProduct){


                itemView.productName_showAllProduct.text = product_name
                itemView.productQty_showAllProduct.text = quantity
                itemView.productMrpPrice_showAllProduct.text = "Rs. ${mrp}"
                itemView.productNewPrice_showAllProduct.text = "Rs. ${new_price}"
                itemView.productDesc_showAllProduct.text = description

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    itemView.productDesc_showAllProduct.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT))
                } else {
                    itemView.productDesc_showAllProduct.setText(Html.fromHtml(description))
                }
            }


        }
    }

    interface DelProduct
    {
       fun  OnDelProductSuccess(prod_id : String)
    }
}

