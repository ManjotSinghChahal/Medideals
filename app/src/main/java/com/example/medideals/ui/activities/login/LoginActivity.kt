package com.example.medideals.ui.activities.login


import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.medideals.R
import com.example.medideals.ui.activities.login.introduction.Introduction
import com.example.medideals.ui.activities.login.loginFrag.Login
import com.example.medideals.ui.activities.login.registerFrag.Register
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.LOGOUT
import com.example.medideals.utils.Constants.REGISTER_TYPE

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)

          val intent = intent.extras
        if (intent!=null && intent.containsKey(LOGOUT))
            supportFragmentManager.beginTransaction().replace(R.id.container_login, Login()).commit()
        else if (intent!=null && intent.containsKey(REGISTER_TYPE))
            supportFragmentManager.beginTransaction().replace(R.id.container_login, Register()).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.container_login,Introduction()).commit()
       //  supportFragmentManager.beginTransaction().replace(R.id.container_login,Login()).commit()

    }
}
