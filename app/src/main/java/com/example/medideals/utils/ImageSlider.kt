package com.example.medideals.utils

import androidx.viewpager.widget.ViewPager
import android.view.ViewGroup
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager.widget.PagerAdapter


class ImageSlider(
    private val context: Context,
    private val list_lay: IntArray?
        ) : PagerAdapter() {

    private val inflater: LayoutInflater


    init {
        inflater = LayoutInflater.from(context)

    }

    private var layoutInflater: LayoutInflater? = null

        override fun getCount(): Int {
            return list_lay!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var views: View =  layoutInflater!!.inflate(list_lay!!.get(position), container, false)

        container.addView(views, 0)
        return views
    }



        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val viewPager = container as ViewPager
            val view = `object` as View
            viewPager.removeView(view)
        }

}