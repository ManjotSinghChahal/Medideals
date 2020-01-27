package com.example.medideals.ui.activities.home.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.medideals.R
import com.example.medideals.data.model.getProfile.GetProfile
import com.example.medideals.data.model.updateProfile.UpdateProfile
import com.example.medideals.data.saveData.SaveProfileData
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.utils.Constants.PROFILE_TYPE
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.rootProfile

class Profile : Fragment(), HomeContract.ProfileView{



    var presenter: HomeContract.HomePresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        GlobalHelper.setToolbar("", homeMenuIconVis = true, relEditIconHomeVis = true)
        presenter = HomePresenterImp(this)
        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter!!.getProfile(PROFILE_TYPE)

        setEnabledFalse(view)


        clickListener(view)

        return view
    }


    fun clickListener(view : View)
    {
        view.updateProfile.setOnClickListener {

            if (view.business_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.enter_business_name))
            else if (view.email_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.enter_email_address))
            else if (view.contact_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.enter_phone))
            else if (view.address_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.enter_address))
            else if (view.gst_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.gstnum_empty))
            else if (view.lic_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.licnum_empty))

            /*else if (view.fssai_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.licnum_empty))
            else if (view.userType_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.licnum_empty))
            else if (view.city_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.licnum_empty))
            else if (view.shopNo_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.licnum_empty))
            else if (view.webUrl_profile.text.toString().trim().isEmpty())
                GlobalHelper.snackBar(view.rootProfile,getString(R.string.licnum_empty))*/

            else
            {

                val profileData = SaveProfileData()
                profileData.vendor_id = SharedPrefUtil!!.getInstance()?.getUserId().toString()
                profileData.email = view.email_profile.text.toString().trim()
                profileData.firm_name = view.business_profile.text.toString().trim()
                profileData.contact_no = view.contact_profile.text.toString().trim()
                profileData.firm_address = view.address_profile.text.toString().trim()
                profileData.gst_number = view.gst_profile.text.toString().trim()
                profileData.drug_licence = view.lic_profile.text.toString().trim()

                GlobalHelper.showProgress(activity as AppCompatActivity)
                presenter!!.updateProfile(profileData)
                GlobalHelper.hideKeyBoard(activity as AppCompatActivity, rootProfile)

            }

        }


        HomeActivity.relEditIconHome.setOnClickListener {

            HomeActivity.relEditIconHome.visibility = View.GONE
            setEnabledTrue(view)
        }

    }

    override fun onUpdateProfileSuccess(updateProfile: UpdateProfile) {
        try {
            GlobalHelper.hideProgress()
            if (updateProfile.status.equals("1"))
                activity?.let {  it.onBackPressed() }
        }catch (ex : Exception){}
    }


    override fun onFailure(error: String) {
        try {
            GlobalHelper.hideProgress()
        }catch (ex : Exception){}
    }

    override fun onGetProfileViewSuccess(getProfile: GetProfile) {
        try {
            GlobalHelper.hideProgress()

            if (getProfile.status.equals("1"))
                setValues(getProfile)

        }catch (ex : Exception){}    }


    fun setValues(getProfile: GetProfile)
    {
        view!!.email_profile.setText(getProfile.record.email)
        view!!.business_profile.setText(getProfile.record.firm_name)
        view!!.contact_profile.setText(getProfile.record.contact_no)
        view!!.address_profile.setText(getProfile.record.firm_address)
        view!!.gst_profile.setText(getProfile.record.gst_number)
        view!!.lic_profile.setText(getProfile.record.drug_licence)

        view!!.fssai_profile.setText(getProfile.record.fssai_no)
        view!!.userType_profile.setText(getProfile.record.user_type)
        view!!.city_profile.setText(getProfile.record.city)
        view!!.shopNo_profile.setText(getProfile.record.shop_no)
        view!!.webUrl_profile.setText(getProfile.record.website_url)
    }

    fun setEnabledFalse(view : View)
    {
        view.business_profile.isEnabled = false
        view.email_profile.isEnabled = false
        view.contact_profile.isEnabled = false
        view.address_profile.isEnabled = false
        view.gst_profile.isEnabled = false
        view.lic_profile.isEnabled = false
        view.fssai_profile.isEnabled = false
        view.userType_profile.isEnabled = false
        view.city_profile.isEnabled = false
        view.shopNo_profile.isEnabled = false
        view.webUrl_profile.isEnabled = false
    }

    fun setEnabledTrue(view : View)
    {
        view.business_profile.isEnabled = true
        view.email_profile.isEnabled = true
        view.contact_profile.isEnabled = true
        view.address_profile.isEnabled = true
        view.gst_profile.isEnabled = true
        view.lic_profile.isEnabled = true
        view.fssai_profile.isEnabled = true
        view.userType_profile.isEnabled = true
        view.city_profile.isEnabled = true
        view.shopNo_profile.isEnabled = true
        view.webUrl_profile.isEnabled = true
    }
}
