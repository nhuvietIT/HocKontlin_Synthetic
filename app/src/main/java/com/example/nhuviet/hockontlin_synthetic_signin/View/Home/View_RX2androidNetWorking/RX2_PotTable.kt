package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_RX2androidNetWorking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.nhuviet.hockontlin_synthetic_signin.Adapter.Adater_RX2_RecyerView_PotTable
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.InfoProducts
import com.example.nhuviet.hockontlin_synthetic_signin.Presenter.RX2AndroidNetWorking.Presenter_Networking_pot
import com.example.nhuviet.hockontlin_synthetic_signin.R
import kotlinx.android.synthetic.main.layout_rx2_pot.*

/**
 * Created by nhuvi on 28/09/2017.
 */
class RX2_PotTable  : AppCompatActivity(),View_RX2_Pot  {
    override fun setErrorMessage(errorMessage: String) {

    }


    var presenter_rx2 : Presenter_Networking_pot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rx2_pot)
        presenter_rx2 =  Presenter_Networking_pot(this)
        //take
        presenter_rx2?.PotTable_dataRX2()

    }
    override fun take_data_RX2_potTable(infoproductList: ArrayList<InfoProducts>) {
    Log.d("ttttttttt",infoproductList.get(0).tenLoaiSP.toString())
     Log.d("ttttttttt",infoproductList.get(0)._users_products!!.get(0).ten.toString())
        Log.d("ttttttttt",infoproductList.get(0)._users_products!!.get(0).facebook!!.email.toString())


        val obj_adapter = Adater_RX2_RecyerView_PotTable(this,infoproductList)
        RX2_recyclerView_pot.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
        RX2_recyclerView_pot.adapter = obj_adapter
         obj_adapter.notifyDataSetChanged()
    }





}