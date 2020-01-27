package com.example.medideals.ui.activities.home.homeFrag



import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide

import com.example.medideals.R
import com.example.medideals.data.model.addCart.AddCart
import com.example.medideals.data.model.getCart.GetCart
import com.example.medideals.data.model.homeListings.HomeListings
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.home.HomeContract
import com.example.medideals.ui.activities.home.HomePresenterImp
import com.example.medideals.ui.activities.home.allopathic.Allopathic
import com.example.medideals.ui.activities.home.viewCart.ViewCart
import com.example.medideals.ui.activities.home.viewCart.ViewCart_Adapter
import com.example.medideals.utils.Constants
import com.example.medideals.utils.Constants.GO_TO_CART
import com.example.medideals.utils.Constants.HOME
import com.example.medideals.utils.GlobalHelper
import com.example.medideals.utils.SharedPrefUtil
import com.example.medideals.utils.SlidingImages_Adapter

import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home_frag.view.*
import io.supercharge.shimmerlayout.ShimmerLayout
import kotlinx.android.synthetic.main.fragment_view_cart.view.*
import java.util.*


class HomeFrag : Fragment(), HomeContract.HomeListingsView, HotDeals_Adapter.AddToCart, MostDis_Adapter.AddToCart,
    LatestProd_Adapter.AddToCart {


    var presenter: HomeContract.HomePresenter? = null
    var recyclerViewHotDeals: RecyclerView? = null
    var recyclerViewMostDis: RecyclerView? = null
    var recyclerviewLatestProd: RecyclerView? = null
    var NUM_PAGES = 3
    var currentPage = 0

    private var layouts: IntArray? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_frag, container, false)


        SharedPrefUtil.getInstance()!!.saveUserId("26")
        initialize(view)
        clickListener(view)



        return view
    }

    fun initialize(view: View) {
        GlobalHelper.setToolbar(getString(R.string.medideals), homeMenuIconVis = true, cartIcon = true)


        layouts = intArrayOf(
            R.drawable.silder_one,
            R.drawable.silder_two,
            R.drawable.slider_three
        )
        val viewpager = view.findViewById(R.id.viewPager_imageSlider) as ViewPager
        val myViewPagerAdapter = activity?.let { SlidingImages_Adapter(it, layouts) }
        viewpager.adapter = myViewPagerAdapter

        val tabLayout = view.findViewById(R.id.tabLayout_imagesliding) as TabLayout
        tabLayout.setupWithViewPager(viewpager, true)

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            viewpager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 2000, 2000)

        // Pager listener over indicator
        viewpager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })


        presenter = HomePresenterImp(this)
        GlobalHelper.showProgress(activity as AppCompatActivity)
        presenter!!.homeListing()
        presenter!!.getCart(HOME)

        recyclerViewHotDeals = view.findViewById(R.id.recyclerview_hotDeals) as RecyclerView
        recyclerViewMostDis = view.findViewById(R.id.recyclerview_mostDis) as RecyclerView
        recyclerviewLatestProd = view.findViewById(R.id.recyclerview_latestProd) as RecyclerView

        val display = activity!!.getWindowManager().getDefaultDisplay()
        val width = display.getWidth().toFloat()
        val animation = TranslateAnimation(-800f, width, 0f, 0f) // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.duration = 8000 // animation duration
        animation.repeatCount = 500 // animation repeat count
        animation.repeatMode = 200 // repeat animation (left to right, right to
        view.lin_delTruck.startAnimation(animation)


    }

    fun clickListener(view : View)
    {
        view.lin_allopathicHome.setOnClickListener {
            setfrag(getString(R.string.allopathic),"1")
        }
        view.lin_ayurvedicHome.setOnClickListener {
            setfrag(getString(R.string.ayurvedic),"2")
        }
        view.lin_fmcgHome.setOnClickListener {
            setfrag(getString(R.string.fmcg),"3")
        }
        view.lin_pcdHome.setOnClickListener {
            setfrag(getString(R.string.pcd_party),"4")
        }
        view.lin_surgicalFoodsHome.setOnClickListener {
            setfrag(getString(R.string.surgical_goods),"5")
        }
        view.lin_genericsHome.setOnClickListener {
            setfrag(getString(R.string.generics),"6")
        }

    }

    override fun onHomeListingsViewSuccess(homeListings: HomeListings) {

        try {
            GlobalHelper.hideProgress()

            recyclerViewHotDeals?.layoutManager = GridLayoutManager(context, 2) as RecyclerView.LayoutManager?
            activity?.let {
                recyclerViewHotDeals?.adapter = HotDeals_Adapter(it, homeListings.hotDealsProducts, this)
            }

            recyclerViewMostDis?.layoutManager = GridLayoutManager(context, 2)
            activity?.let {
                recyclerViewMostDis?.adapter = MostDis_Adapter(it, homeListings.getMostDiscounted, this)
            }

            recyclerviewLatestProd?.layoutManager = GridLayoutManager(context, 2)
            activity?.let {
                recyclerviewLatestProd?.adapter = LatestProd_Adapter(it, homeListings.getNewProducts, this)
            }

            /*Glide.with(activity as AppCompatActivity).load(homeListings.images.get(0)).into(view!!.adv11)
            Glide.with(activity as AppCompatActivity).load(homeListings.images.get(1)).into(view!!.adv12)
            Glide.with(activity as AppCompatActivity).load(homeListings.images.get(2)).into(view!!.adv13)*/

        } catch (ex: Exception) {
        }


    }

    override fun onFailure(error: String) {
        try {
            error.let {
                GlobalHelper.hideProgress()
                GlobalHelper.snackBar(view!!.rootHome, it)
            }

        } catch (ex: Exception) {
        }
    }

    override fun onAddToCartSuccess(product_id: String, product_status: String,qty : String) {
        if (product_status.equals("not_added")) {
            GlobalHelper.showProgress(activity as AppCompatActivity)
            presenter!!.addCart(product_id, qty, HOME)
        } else if (product_status.equals(GO_TO_CART))
            GlobalHelper.replaceFragment(activity as AppCompatActivity, R.id.container_home, ViewCart())

    }

    override fun onAddCartViewSuccess(addCart: AddCart) {
        try {
            GlobalHelper.hideProgress()
            addCart.message.let { GlobalHelper.snackBar(view!!.rootHome, it) }
            if (addCart.status.equals("1")) {
                HomeActivity.relCartBack.visibility = View.VISIBLE
                GlobalHelper.cartCount = GlobalHelper.cartCount + 1
                HomeActivity.cartCountHome.text = GlobalHelper.cartCount.toString()

            }
        } catch (ex: Exception) {
        }
    }

    override fun onGetCartViewSuccess(screen: String, getCart: GetCart) {
        try {
            activity?.let {
                if (getCart.status.equals("1")) {
                    HomeActivity.relCartBack.visibility = View.VISIBLE
                    HomeActivity.cartCountHome.text = getCart.total_items.toString()
                    GlobalHelper.cartCount = getCart.total_items

                } else {
                    HomeActivity.relCartBack.visibility = View.GONE
                    GlobalHelper.cartCount = 0
                }

            }

        } catch (ex: java.lang.Exception) {
        }
    }

    fun setfrag(title : String, cat_id : String)
    {
        val bundle = Bundle()
        bundle.putString(Constants.TITLE_CATEGORY,title)
        bundle.putString(Constants.CATEGORY_ID,cat_id)
        bundle.putString(Constants.SEARCH_QUERY,"")
        val frag = Allopathic()
        frag.arguments = bundle
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.container_home,
            frag
        ).addToBackStack(null).commit()
    }
}
