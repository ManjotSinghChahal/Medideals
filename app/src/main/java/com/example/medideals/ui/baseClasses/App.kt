package com.example.passerby.ui.baseClasses

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.medideals.utils.AppLifecycleHandler
import com.example.medideals.utils.SharedPrefUtil

class App : Application(), AppLifecycleHandler.AppLifecycleDelegates
{

    private var lifecycleHandler: AppLifecycleHandler? = null


    companion object {

        var instance: App? = null
        //   private set

        fun hasNetwork(): Boolean {
            return instance!!.checkIfHasNetwork()
        }

        fun tokenExpired() {
            instance!!.onTokenExpired()
        }
    }

    override fun onCreate() {
        super.onCreate()


        instance = this
        initSharedHelper()
        lifecycleHandler = AppLifecycleHandler(this)
        registerLifecycleHandler(lifecycleHandler!!)




    }

    private fun registerLifecycleHandler(lifeCycleHandler: AppLifecycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)
    }


    override fun onAppBackgrounded() {
    }

    override fun onAppForegrounded() {
//        if (SharedPrefUtil.getInstance().isLogin()) {
    }


    private fun initSharedHelper() {
           SharedPrefUtil.init(this)
    }

    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun onTokenExpired() {
        //        ToastUtils.shortToast(getString(R.string.session_expire));
        /*  val intent = Intent(this, LoginActivity::class.java)
          intent.putExtra(TOKEN_EXPIRED, true)
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
          startActivity(intent)*/
    }


}