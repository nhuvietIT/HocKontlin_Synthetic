package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_RX2androidNetWorking

import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.InfoProducts

/**
 * Created by nhuvi on 29/09/2017.
 */
interface View_RX2_Pot {
    fun take_data_RX2_potTable(infoproductList : ArrayList<InfoProducts>)
    fun setErrorMessage(errorMessage: String)
}