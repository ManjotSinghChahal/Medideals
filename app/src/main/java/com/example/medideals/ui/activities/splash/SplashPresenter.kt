package com.example.medideals.ui.activities.splash

class SplashPresenter(var splashview: SplashView)  {


    fun onTimeOut() {

        splashview.onHandleTimeout()
    }

}