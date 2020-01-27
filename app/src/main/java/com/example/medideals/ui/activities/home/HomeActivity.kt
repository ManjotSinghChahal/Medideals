package com.example.medideals.ui.activities.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.medideals.R
import com.example.medideals.ui.activities.home.addProduct.AddProduct
import com.example.medideals.ui.activities.home.allopathic.Allopathic
import com.example.medideals.ui.activities.home.ayurvedic.Ayurvedic
import com.example.medideals.ui.activities.home.contactUs.ContactUs
import com.example.medideals.ui.activities.home.fmcg.Fmcg
import com.example.medideals.ui.activities.home.generics.Generics
import com.example.medideals.ui.activities.home.homeFrag.HomeFrag
import com.example.medideals.ui.activities.home.myAccount.MyAccount
import com.example.medideals.ui.activities.home.pcd.Pcd
import com.example.medideals.ui.activities.home.settings.Settings
import com.example.medideals.ui.activities.home.surgicalGoods.SurgicalGoods
import com.example.medideals.ui.activities.home.viewCart.ViewCart
import com.example.medideals.utils.GlobalHelper
import com.google.android.material.navigation.NavigationView
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import com.example.medideals.data.model.updateDeviceId.UpdateDeviceId
import com.example.medideals.data.model.updateLatLng.UpdateLatLng
import com.example.medideals.utils.LocationService
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_contact_us.view.*
import android.content.DialogInterface
import android.view.View
import android.widget.*

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.medideals.data.model.getProfile.GetProfile
import com.example.medideals.data.model.logout.Logout
import com.example.medideals.ui.activities.home.profile.Profile
import com.example.medideals.ui.activities.login.LoginActivity
import com.example.medideals.ui.activities.login.loginFrag.Login
import com.example.medideals.utils.Constants.CATEGORY_ID
import com.example.medideals.utils.Constants.HOME_ACTIVITY
import com.example.medideals.utils.Constants.LOGOUT
import com.example.medideals.utils.Constants.SEARCH_QUERY
import com.example.medideals.utils.Constants.TITLE_CATEGORY
import com.example.medideals.utils.GlobalHelper.USER_TYPE
import com.example.medideals.utils.SharedPrefUtil


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    HomeContract.HomeActivityView {



    var presenter: HomeContract.HomePresenter? = null

    companion object {
        lateinit var drawer: androidx.drawerlayout.widget.DrawerLayout
        lateinit var homeBackIcon: ImageView
        lateinit var homeMenuIcon: ImageView
        lateinit var titleHome: TextView
        lateinit var cartCountHome: TextView
        lateinit var relCartBack: RelativeLayout
       // lateinit var relHomeLeft: RelativeLayout
        lateinit var viewCartHome: RelativeLayout
        lateinit var searchFilterHome: LinearLayout
        lateinit var filterHome: RelativeLayout
        lateinit var searchHome: RelativeLayout
        lateinit var relFilterHome: RelativeLayout
        lateinit var relAddCard: RelativeLayout
        lateinit var relHomeSearchBack: RelativeLayout
        lateinit var relLaySearchHome: RelativeLayout
        lateinit var relHomeSearchClose: RelativeLayout
        lateinit var relFilterTickHome: RelativeLayout
        lateinit var relFilterCloseHome: RelativeLayout
        lateinit var relHomeSearchEdt: EditText
        lateinit var relEditHome: RelativeLayout
        lateinit var relEditIconHome: RelativeLayout
        lateinit var loginSignupNavHeader: LinearLayout
        lateinit var emailNavHeader: LinearLayout
        lateinit var emailHeaderTextView: TextView
        lateinit var numberHeaderTextView: TextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        initialize()
        clickListener()

        var l1 = ArrayList<String>()
        var l2 = mutableListOf<String>()
        var l3 = arrayListOf<String>()




       // Log.e("user_id",SharedPrefUtil!!.getInstance()?.getUserId().toString())

        /*val android_id = android.provider.Settings.Secure.getString(this.getContentResolver(),
            android.provider.Settings.Secure.ANDROID_ID)*/



    }

    fun initialize() {
        drawer = findViewById(R.id.drawer_layout)
        homeBackIcon = findViewById(R.id.img_back_drawer)
        homeMenuIcon = findViewById(R.id.img_drawer)
        titleHome = findViewById(R.id.title_home)
      //  relHomeLeft = findViewById(R.id.rel_menu_home)
        viewCartHome = findViewById(R.id.viewcart_home)
        searchFilterHome = findViewById(R.id.rel_search_filter_home)
        filterHome = findViewById(R.id.rel_filter_home)
        searchHome = findViewById(R.id.rel_search_home)
        relFilterHome = findViewById(R.id.rel_filterHome)
        relAddCard = findViewById(R.id.rel_addCard)
        relCartBack = findViewById(R.id.rel_cartBack)
        cartCountHome = findViewById(R.id.cartCount_home)
        relHomeSearchBack = findViewById(R.id.rel_homeSearch_back)
        relLaySearchHome = findViewById(R.id.relLay_search_home)
        relHomeSearchClose = findViewById(R.id.rel_homeSearch_close)
        relFilterCloseHome = findViewById(R.id.rel_filterClose_home)
        relHomeSearchEdt = findViewById(R.id.rel_homeSearch_edt)
        relEditIconHome = findViewById(R.id.rel_edit_home)
        relFilterTickHome = findViewById(R.id.rel_filterTick_home)

        homeMenuIcon.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        viewCartHome.setOnClickListener {
            GlobalHelper.replaceFragment(this, R.id.container_home, ViewCart())
        }






        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val headerView : View =  navigationView.getHeaderView(0)
        relEditHome = headerView.findViewById(R.id.relEdit_home)
        loginSignupNavHeader = headerView.findViewById(R.id.loginSignup_navHeader)
        emailNavHeader = headerView.findViewById(R.id.email_navHeader)
        emailHeaderTextView = headerView.findViewById(R.id.email_header_textView)
        numberHeaderTextView = headerView.findViewById(R.id.number_header_textView)
        navigationView.setNavigationItemSelectedListener(this)

        loginSignupNavHeader.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra(LOGOUT,LOGOUT)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        relEditHome.setOnClickListener {
            GlobalHelper.replaceFragment(this, R.id.container_home, Profile())
            drawer.closeDrawer(GravityCompat.START)
        }


        supportFragmentManager.beginTransaction().replace(R.id.container_home, HomeFrag()).commit()
        //  supportFragmentManager.beginTransaction().replace(R.id.container_filter, AddProduct()).commit()

        presenter = HomePresenterImp(this)
        if (SharedPrefUtil!!.getInstance()?.isLogin()!!)
           presenter!!.updateDeviceID()


        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.registerReceiver(receiver, IntentFilter("ACTION_LOCATION_BROADCAST"))

        if (SharedPrefUtil!!.getInstance()?.isLogin()!!)
        {
            loginSignupNavHeader.visibility = View.GONE
            relEditHome.visibility = View.VISIBLE
            emailNavHeader.visibility = View.VISIBLE

            presenter!!.getProfile(HOME_ACTIVITY)
        }
        else
        {
            loginSignupNavHeader.visibility = View.VISIBLE
            relEditHome.visibility = View.GONE
            emailNavHeader.visibility = View.GONE
        }
    }

    fun clickListener() {

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.nav_item_home -> supportFragmentManager.beginTransaction().replace(
                R.id.container_home,
                HomeFrag()
            ).addToBackStack(null).commit()
            R.id.nav_item_myAcc -> supportFragmentManager.beginTransaction().replace(
                R.id.container_home,
                MyAccount()
            ).addToBackStack(null).commit()
            R.id.nav_item_settings -> supportFragmentManager.beginTransaction().replace(
                R.id.container_home,
                Settings()
            ).addToBackStack(null).commit()
            R.id.nav_item_contact_us -> supportFragmentManager.beginTransaction().replace(
                R.id.container_home,
                ContactUs()
            ).addToBackStack(null).commit()
            R.id.nav_item_ayurvedic -> setfrag(getString(R.string.ayurvedic),"2")

            R.id.nav_item_allopathic -> setfrag(getString(R.string.allopathic),"1")

            R.id.nav_item_pcd -> setfrag(getString(R.string.pcd_party),"4")

            R.id.nav_item_fmcg -> setfrag(getString(R.string.fmcg),"3")

            R.id.nav_item_surgicalGoods -> setfrag(getString(R.string.surgical_goods),"5")

            R.id.nav_item_generics -> setfrag(getString(R.string.generics),"6")

            R.id.nav_item_logout -> logout()

        }
        drawer.closeDrawer(GravityCompat.START)

        return true

    }
    fun logout()
    {
        AlertDialog.Builder(this)
            .setMessage("Are you sure want to Logout?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                   GlobalHelper.showProgress(this)
                    presenter!!.logout()
                    })
            .setNegativeButton("No", null)
            .show()
    }

    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                val lat = intent.getStringExtra("lat")
                val lng = intent.getStringExtra("lng")
                presenter!!.updateLatLng(lat,lng)

            }
        }
    }

    override fun onUpdateDeviceIdViewSuccess(updateDeviceId: UpdateDeviceId) {
    }

    override fun onUpdateLatLngViewSuccess(updateLatLng: UpdateLatLng) {
    }

    override fun onLogoutViewSuccess(logout: Logout) {

        try {
            GlobalHelper.hideProgress()
            SharedPrefUtil.getInstance()!!.onLogout()
            val intent = Intent(this,LoginActivity::class.java)
            intent.putExtra(LOGOUT,LOGOUT)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }catch (ex : Exception){}

    }

    override fun onFailure(error: String) {
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(this, LocationService::class.java))

    }
    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, LocationService::class.java))
    }

    fun setfrag(title : String, cat_id : String)
    {
        val bundle = Bundle()
        bundle.putString(TITLE_CATEGORY,title)
        bundle.putString(CATEGORY_ID,cat_id)
        bundle.putString(SEARCH_QUERY,"")
        val frag = Allopathic()
        frag.arguments = bundle
        supportFragmentManager.beginTransaction().replace(
            R.id.container_home,
            frag
        ).addToBackStack(null).commit()
    }

    override fun onGetProfileViewSuccess(getProfile: GetProfile) {
        try {
            GlobalHelper.hideProgress()
            if (getProfile.status.equals("1"))
            {
                emailHeaderTextView.text = getProfile.record.email
                numberHeaderTextView.text = getProfile.record.contact_no
                USER_TYPE = getProfile.record.user_type

            }
        }catch (ex : Exception){}
    }


}
