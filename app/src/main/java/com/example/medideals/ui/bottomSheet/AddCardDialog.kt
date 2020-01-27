package com.example.medideals.ui.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.medideals.R
import com.example.medideals.data.saveData.AddCardInfo
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.addcard_dialog.view.*

class AddCardDialog(val callback : CardInfo) : BottomSheetDialogFragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.addcard_dialog,container,false)



        view.submit_addCard.setOnClickListener {

            if (view.bankName_addCard.text.toString().trim().isEmpty())
               Toast.makeText(activity,getString(R.string.enter_bank_name),Toast.LENGTH_SHORT).show()
            else if (view.bankAcc_addCard.text.toString().trim().isEmpty() || view.bankAcc_addCard.text.toString().trim().length<12)
                Toast.makeText(activity,getString(R.string.enter_bank_acc),Toast.LENGTH_SHORT).show()
            else if (view.ifscCode_addCard.text.toString().trim().isEmpty())
                Toast.makeText(activity,getString(R.string.enter_ifsc_code),Toast.LENGTH_SHORT).show()
            else if (view.bankAddress_addCard.text.toString().trim().isEmpty())
                Toast.makeText(activity,getString(R.string.enter_bank_address),Toast.LENGTH_SHORT).show()
            else if (view.phNo_addCard.text.toString().trim().isEmpty() || view.phNo_addCard.text.toString().trim().length<10)
                Toast.makeText(activity,getString(R.string.enter_ph_no),Toast.LENGTH_SHORT).show()
            else if (view.upiNo_addCard.text.toString().trim().isEmpty())
                Toast.makeText(activity,getString(R.string.enter_upi_no),Toast.LENGTH_SHORT).show()
            else if (view.enterDate_addCard.text.toString().trim().isEmpty())
                Toast.makeText(activity,getString(R.string.enter_date),Toast.LENGTH_SHORT).show()
            else
            {
                val addCardInfo = AddCardInfo()

                addCardInfo.vendor_id = SharedPrefUtil.getInstance()!!.getUserId().toString()
                addCardInfo.bank_name = view.bankName_addCard.text.toString().trim()
                addCardInfo.bank_account = view.bankAcc_addCard.text.toString().trim()
                addCardInfo.ifsc_code = view.ifscCode_addCard.text.toString().trim()
                addCardInfo.bank_address = view.bankAddress_addCard.text.toString().trim()
                addCardInfo.phone_number = view.phNo_addCard.text.toString().trim()
                addCardInfo.upi_number = view.upiNo_addCard.text.toString().trim()
                addCardInfo.insert_date = view.enterDate_addCard.text.toString().trim()

                callback.OnCardInfoSuccess(addCardInfo)
                dismiss()
            }

        }

        return view
    }

    interface CardInfo
    {
        fun OnCardInfoSuccess(addCardInfo: AddCardInfo)
    }
}