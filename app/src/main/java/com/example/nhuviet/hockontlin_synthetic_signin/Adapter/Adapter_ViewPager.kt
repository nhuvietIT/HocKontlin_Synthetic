package com.example.nhuviet.hockontlin_synthetic_signin.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.Fragment.*

/**
 * Created by nhuvi on 11/08/2017.
 */
class Adapter_ViewPager(fm : FragmentManager)  : FragmentPagerAdapter(fm) {
    var titlefragmentList: Array<String> = arrayOf("Home", "Sản phẩm", "Khuyến Mãi","Cửa Hàng","Trang Cá Nhân")
    val NUM_ITEMS = 5

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return Home()
            1 -> return SanPham()
            2 -> return Khuyenmai()
            3 -> return Cuahang()
            4 -> return Canhan()
            else -> return null
        }
 //      return fragmentList.get(position)
    }
    override fun getCount(): Int {
        return  NUM_ITEMS
    }
    override fun getPageTitle(position: Int): CharSequence {
     //   return  titles[position]
         return titlefragmentList.get(position)
    }
 }

