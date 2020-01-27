package com.example.medideals.ui.activities.home.bankDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medideals.R
import com.example.medideals.data.model.bankDeatilsModel.BankDeatilsModel
import kotlinx.android.synthetic.main.bankdetails_adpt.view.*


class BankDetailsAdapter(
    val context: Context, val bankDeatilsModel: BankDeatilsModel
) : RecyclerView.Adapter<BankDetailsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.bankdetails_adpt, container, false))
    }

    override fun getItemCount(): Int {
        return bankDeatilsModel.message.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews() {

            if (bankDeatilsModel.message.size>0)
            {
               if (adapterPosition==0)
               {
                   itemView.id_bankDetails.visibility = View.VISIBLE
                   itemView.bankName_bankDetails.visibility = View.VISIBLE
                   itemView.bankAcc_bankDetails.visibility = View.VISIBLE
                   itemView.ifscCode_bankDetails.visibility = View.VISIBLE
                   itemView.bankAdd_bankDetails.visibility = View.VISIBLE
                   itemView.phNo_bankDetails.visibility = View.VISIBLE
                   itemView.upiNo_bankDetails.visibility = View.VISIBLE
                   itemView.InsertDate_bankDetails.visibility = View.VISIBLE
                   itemView.firmNo_bankDetails.visibility = View.VISIBLE
                   itemView.vendorId_bankDetails.visibility = View.VISIBLE
               }
               else
               {
                   itemView.id_bankDetails.visibility = View.GONE
                   itemView.bankName_bankDetails.visibility = View.GONE
                   itemView.bankAcc_bankDetails.visibility = View.GONE
                   itemView.ifscCode_bankDetails.visibility = View.GONE
                   itemView.bankAdd_bankDetails.visibility = View.GONE
                   itemView.phNo_bankDetails.visibility = View.GONE
                   itemView.upiNo_bankDetails.visibility = View.GONE
                   itemView.InsertDate_bankDetails.visibility = View.GONE
                   itemView.firmNo_bankDetails.visibility = View.GONE
                   itemView.vendorId_bankDetails.visibility = View.GONE
               }

            }

            itemView.txt_idBankDet.text = bankDeatilsModel.message.get(adapterPosition).id
            itemView.txt_bankName_BankDet.text = bankDeatilsModel.message.get(adapterPosition).bank_name
            itemView.txt_bankAcc_BankDet.text = bankDeatilsModel.message.get(adapterPosition).bank_account
            itemView.txt_ifscCode_BankDet.text = bankDeatilsModel.message.get(adapterPosition).ifsc_code
            itemView.txt_bankAdd_BankDet.text = bankDeatilsModel.message.get(adapterPosition).bank_address
            itemView.txt_phNo_BankDet.text = bankDeatilsModel.message.get(adapterPosition).phone_number
            itemView.txt_upiNo_BankDet.text = bankDeatilsModel.message.get(adapterPosition).upi_number
            itemView.txt_insertDate_BankDet.text = bankDeatilsModel.message.get(adapterPosition).insert_date
            itemView.txt_firmNo_BankDet.text = bankDeatilsModel.message.get(adapterPosition).firm_name
            itemView.txt_vendorId_BankDet.text = bankDeatilsModel.message.get(adapterPosition).vendor_id

        }
    }

}