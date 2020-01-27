package com.example.medideals.ui.activities.home.subscription

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.getSubscription.GetSubscription
import kotlinx.android.synthetic.main.subscription_adapter.view.*


class SubscriptionAdapter(val context: Context, val getSubscription: GetSubscription,val callback : SelBuySubscription) :
    RecyclerView.Adapter<SubscriptionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.subscription_adapter, container, false))
    }

    override fun getItemCount(): Int {
        return getSubscription.record.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(getSubscription)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(subscription: GetSubscription) {

            itemView.txt_plan_subscr.text = "Plan ${adapterPosition + 1}"
            itemView.timePeriod_sunscr.text = subscription.record.get(adapterPosition).time
            itemView.amount_sunscr.text = "Rs.${subscription.record.get(adapterPosition).amount}/-"

            itemView.rel_buyNow_subscr.setOnClickListener {
               callback.OnSelBuySubscription(subscription.record.get(adapterPosition).time)
            }
        }
    }

    interface SelBuySubscription
    {
        fun OnSelBuySubscription(subscription : String)
    }

}