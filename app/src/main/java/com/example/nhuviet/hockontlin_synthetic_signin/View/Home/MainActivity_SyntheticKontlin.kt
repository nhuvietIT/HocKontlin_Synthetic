package com.example.nhuviet.hockontlin_synthetic_signin.View.Home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.nhuviet.hockontlin_synthetic_signin.Adapter.Adapter_ViewPager
import com.example.nhuviet.hockontlin_synthetic_signin.R
import kotlinx.android.synthetic.main.activity_main__synthetic_kontlin.*


class MainActivity_SyntheticKontlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main__synthetic_kontlin)

        // toanbar
        home_toobar.setTitle("Synthetic")
        setSupportActionBar(home_toobar)

        // adapter viewpager
        val adapter_viewPager = Adapter_ViewPager(supportFragmentManager)
        home_viewpager.adapter = adapter_viewPager
        // tab layout
        home_tablayout.setupWithViewPager(home_viewpager)



//        val presenter_code_rauanla: Presenter_cacloairau = Presenter_cacloairau(this)
//        presenter_code_rauanla.LaydanhsachSanPham_cacloairau(this)

    }


}
