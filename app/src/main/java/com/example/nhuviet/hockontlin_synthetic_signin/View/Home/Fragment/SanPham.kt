package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nhuviet.hockontlin_synthetic_signin.R
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_RX2androidNetWorking.RX2_AndroidNetWorking
import kotlinx.android.synthetic.main.layout_fragment_home.view.*

/**
 * Created by nhuvi on 11/08/2017.
 */
class SanPham   : Fragment() {

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_fragment_home, container, false)

        view.bt_Rx2.setOnClickListener{

            val intent = Intent(context, RX2_AndroidNetWorking::class.java)
            context.startActivity(intent)
        }

        return view

    }
}