package com.example.medideals.ui.activities.login.introduction


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.medideals.R
import com.example.medideals.ui.activities.home.HomeActivity
import com.example.medideals.ui.activities.login.loginFrag.Login
import com.example.medideals.utils.ImageSlider
import kotlinx.android.synthetic.main.fragment_introduction.view.*
import java.util.*


class Introduction : Fragment() {

    private var layouts: IntArray? = null
    var NUM_PAGES = 3
    var currentPage = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_introduction, container, false)


        layouts = intArrayOf(
            R.layout.item_slider_one,
            R.layout.item_slider_two,
            R.layout.item_slider_three,
            R.layout.item_slider_four
        )

        val viewpager = view.findViewById(R.id.viewpager_intro) as ViewPager
        val myViewPagerAdapter = activity?.let { ImageSlider(it,layouts) }
        viewpager.adapter = myViewPagerAdapter

        viewpager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                if (position==3)
                    view.let_started_intro.visibility = View.VISIBLE
                else
                    view.let_started_intro.visibility = View.GONE
            }
            override fun onPageSelected(position: Int) {

            }

        })

        view.let_started_intro.setOnClickListener {
         //   activity?.let { it.supportFragmentManager.beginTransaction().replace(R.id.container_login, Login()).commit() }
            activity?.let { it.startActivity(Intent(it,HomeActivity::class.java)) }
        }


        view.txt_skip.setOnClickListener {
            activity?.let { it.startActivity(Intent(it,HomeActivity::class.java)) }
        }

        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
              //  currentPage = 0
            }
            viewpager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 2000, 2000)

        return view
    }


}
