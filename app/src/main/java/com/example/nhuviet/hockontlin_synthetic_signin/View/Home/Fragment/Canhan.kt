package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.Fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nhuviet.hockontlin_synthetic_signin.R

/**
 * Created by nhuvi on 11/08/2017.
 */
class Canhan : Fragment() {

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_fragment_home, container, false)



        return view

    }
}