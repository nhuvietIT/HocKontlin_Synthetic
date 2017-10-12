package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_RX2androidNetWorking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.nhuviet.hockontlin_synthetic_signin.Adapter.Adapter_RX2_RecycerView
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.Product
import com.example.nhuviet.hockontlin_synthetic_signin.R
import kotlinx.android.synthetic.main.layout_rx2.*
import sega.fastnetwork.test.presenter.Presenter_networking





/**
 * Created by nhuvi on 26/09/2017.
 */

class RX2_AndroidNetWorking  : AppCompatActivity(),View_RX2_Android_NetWorking  {


    var data : ArrayList<Product>? = null
    var presenter_rx2 : Presenter_networking? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rx2)
        presenter_rx2 = Presenter_networking(this)
        //take
        presenter_rx2?.take_dataRX2("","","",0)

        bt_themsp.setOnClickListener{
            //ceate
            presenter_rx2?.create_dataRX2(ed_masp.text.toString(),ed_tensp.text.toString(),ed_soluongsp.text.toString(),
                    ed_ma_idpot.text.toString())
        }

    }

    override fun take_data_RX2(product: ArrayList<Product>) {
        val obj_adapter = Adapter_RX2_RecycerView(this,product)
        RX2_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
     //   RX2_recyclerView.adapter = obj_adapter

//        RX2_recyclerView.setLayoutManager(VegaLayoutManager())

       // obj_adapter.notifyDataSetChanged()

        RX2_recyclerView.adapter = obj_adapter
    }

    override fun setErrorMessage(errorMessage: String) {

    }


}