package com.example.medideals.utils

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.medideals.R
import com.example.medideals.ui.activities.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import android.R.attr.duration
import android.view.animation.RotateAnimation
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.drawerlayout.widget.DrawerLayout


object GlobalHelper
{

    val BASE_URL = "http://medideals.co.in/cdg/medideals/Api/"
    var mProgress: ProgressDialog? = null
    var cartCount : Int = 0
    var  animation : ObjectAnimator? = null

    var CURRENT_LAT  = 0.0
    var CURRENT_LNG  = 0.0
    var PREV_LAT  = 0.0
    var PREV_LNG  = 0.0

    var USER_TYPE = ""

    fun setToolbar(value : String, homeMenuIconVis : Boolean = false, homeBackIconVis : Boolean = false, drawerSwipeable : Boolean = false, cartIcon: Boolean = false, searchFilterHomeVis : Boolean = false,relFilterHomeVis : Boolean = false,relAddCardVis : Boolean = false,
                   relEditIconHomeVis : Boolean = false, relFilterTickHomeVis : Boolean = false, relFilterCloseHomeVis : Boolean = false)
    {
        HomeActivity.titleHome.text = value
        if (cartIcon) HomeActivity.viewCartHome.visibility = View.VISIBLE else HomeActivity.viewCartHome.visibility = View.GONE
        if (homeMenuIconVis) HomeActivity.homeMenuIcon.visibility = View.VISIBLE else HomeActivity.homeMenuIcon.visibility = View.GONE
        if (homeBackIconVis)  HomeActivity.homeBackIcon.visibility = View.VISIBLE else  HomeActivity.homeBackIcon.visibility = View.GONE
        if (searchFilterHomeVis)  HomeActivity.searchFilterHome.visibility = View.VISIBLE else  HomeActivity.searchFilterHome.visibility = View.GONE
        if (relAddCardVis)  HomeActivity.relAddCard.visibility = View.VISIBLE else  HomeActivity.relAddCard.visibility = View.GONE
        if (relFilterCloseHomeVis)  HomeActivity.relFilterCloseHome.visibility = View.VISIBLE else  HomeActivity.relFilterCloseHome.visibility = View.GONE
        if (relFilterTickHomeVis)  HomeActivity.relFilterTickHome.visibility = View.VISIBLE else  HomeActivity.relFilterTickHome.visibility = View.GONE
        if (relEditIconHomeVis)  HomeActivity.relEditIconHome.visibility = View.VISIBLE else  HomeActivity.relEditIconHome.visibility = View.GONE
    //    if (relFilterHomeVis)  HomeActivity.relFilterHome.visibility = View.VISIBLE else  HomeActivity.relFilterHome.visibility = View.GONE
        if (!drawerSwipeable)   HomeActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) else HomeActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

    }

    fun roundUpto2Dec(num : String) : String
    {
        try {
            return String.format("%.2f", num.toDouble())
        }catch (ex : Exception){}

        return num
    }

    fun replaceFragment(context: Context, containerHome: Int,frag : Fragment)
    {
        (context as AppCompatActivity).supportFragmentManager.beginTransaction().replace(containerHome, frag).addToBackStack(null).commit()
    }

    @SuppressLint("WrongConstant")
    fun snackBar(view : View, message: String, duration: Int = Toast.LENGTH_SHORT) {
        val snack = Snackbar.make(view, message, duration).show()

    }

    fun hideKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }

    fun showProgress(mContext: Context) = try {
        if (mProgress == null) {
            mProgress = ProgressDialog(mContext)
            mProgress!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        mProgress!!.show()
        mProgress!!.setContentView(R.layout.dialog_progress)
        mProgress!!.setCancelable(false)

        val img : ImageView = mProgress!!.findViewById(R.id.loader_img)
        animation = ObjectAnimator.ofFloat(img, "rotationY", 0.0f, 360f)
        animation!!.duration = 1000
        animation!!.repeatCount = ObjectAnimator.INFINITE
        animation!!.interpolator = AccelerateDecelerateInterpolator()
        animation!!.start()


    } catch (e: Exception) {
        e.printStackTrace()
        mProgress = null
    }

    // hide the common progress which is used in all application.
    fun hideProgress() {
        try {
            if (mProgress != null) {
                mProgress!!.hide()
                mProgress!!.dismiss()
                mProgress = null
            }


        } catch (e: Exception) {
            e.printStackTrace()
            mProgress = null
        }

    }

    @SuppressLint("WrongConstant")
     fun manageBlinkEffect(txtview : TextView) {
        val anim = ObjectAnimator.ofInt(
            txtview, "textColor", Color.RED
        )
        anim.duration = 500
        anim.setEvaluator(ArgbEvaluator())
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        anim.start()
    }

}