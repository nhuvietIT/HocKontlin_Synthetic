package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_RX2androidNetWorking

import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.Product

/**
 * Created by nhuvi on 26/09/2017.
 */
interface View_RX2_Android_NetWorking {
    fun take_data_RX2(product : ArrayList<Product>)
    fun setErrorMessage(errorMessage: String)
}