package com.example.medideals.ui.activities.splash

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.medideals.R
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.login.LoginActivity
import com.example.medideals.utils.GrantPermissions
import com.example.medideals.utils.SharedPrefUtil

class Splash : AppCompatActivity(), SplashView {


    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //2 seconds
    private val PermissionsRequestCode = 101
    lateinit var managePermissions: GrantPermissions
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.splash)



        presenter = SplashPresenter(this)
        //---------------------------------------------------------
        val list = listOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        // Initialize a new instance of ManagePermissions class
        managePermissions = GrantPermissions(this, list, PermissionsRequestCode,this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            managePermissions.checkPermissions()


    }

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            if (SharedPrefUtil.getInstance()?.isLogin()!!)
            {
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
            else
            {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }


        }
    }

    override fun onPause() {
        if (mDelayHandler != null)
            mDelayHandler!!.removeCallbacks(mRunnable)
        super.onPause()
    }

    override fun onRestart() {
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
        super.onRestart()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {



        when (requestCode) {
            PermissionsRequestCode -> {
                val isPermissionsGranted = managePermissions
                    .processPermissionsResult(requestCode, permissions, grantResults)


                presenter.onTimeOut()

                if (isPermissionsGranted) {
                    //  toast("Permissions granted.")
                } else {
                    //  toast("Permissions denied.")
                }
                return
            }
        }

    }

    override fun onHandleTimeout() {
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

}