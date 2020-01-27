package com.example.medideals.ui.activities.home.bankDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.medideals.R
import com.example.medideals.data.model.addCard.AddCard
import com.example.medideals.data.model.bankDeatilsModel.BankDeatilsModel
import com.example.medideals.data.saveData.AddCardInfo
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.bottomSheet.AddCardDialog
import com.example.medideals.utils.GlobalHelper
import kotlinx.android.synthetic.main.fragment_bank_details.view.*

class BankDetails : Fragment(), HomeContract.BankDeatilsView, AddCardDialog.CardInfo {


    var recyclerviewBankDeatils: RecyclerView? = null
    var presenter: HomeContract.HomePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bank_details, container, false)

        initialize(view)
        clickListener(view)

        return view
    }


    fun initialize(view: View) {
        GlobalHelper.setToolbar(getString(R.string.bank_details), homeBackIconVis = true, relAddCardVis = true)

        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter = HomePresenterImp(this)
        presenter?.bankDetails()

        recyclerviewBankDeatils = view.findViewById(R.id.recyclerviewBankDeatils) as RecyclerView
        recyclerviewBankDeatils?.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?



        HomeActivity.homeBackIcon.setOnClickListener {
            activity?.let { it.onBackPressed() }
        }
    }

    fun clickListener(view: View) {
        HomeActivity.relAddCard.setOnClickListener {
            val addCard = AddCardDialog(this)
            addCard.show(activity!!.supportFragmentManager, "")
            //  addCard.isCancelable = false
        }

    }

    override fun onBankDeatilsViewSuccess(bankDeatilsModel: BankDeatilsModel) {
        try {
            GlobalHelper.hideProgress()
            if (bankDeatilsModel.status.equals("1")) {
                activity?.let { recyclerviewBankDeatils?.adapter =
                    BankDetailsAdapter(it, bankDeatilsModel)
                }
            }

        } catch (ex: Exception) {
        }
    }

    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
           // error.let { GlobalHelper.snackBar(view!!.rootBankDetails, it) }
        } catch (ex: Exception) {
        }
    }


    override fun OnCardInfoSuccess(addCardInfo: AddCardInfo) {
        presenter!!.addCardDetails(addCardInfo)
    }

    override fun onAddCardDetailsViewSuccess(addCard: AddCard) {
        try {
            GlobalHelper.hideProgress()
            GlobalHelper.snackBar(view!!.rootBankDetails, addCard.message)
            presenter!!.bankDetails()
        } catch (ex: Exception) {
        }

    }
}
