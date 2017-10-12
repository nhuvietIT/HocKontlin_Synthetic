package com.example.nhuviet.hockontlin_synthetic_signin.Presenter.RX2AndroidNetWorking

import android.os.Looper
import android.util.Log
import com.androidnetworking.error.ANError
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.InfoProducts
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_RX2androidNetWorking.View_RX2_Pot
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

/**
 * Created by nhuvi on 29/09/2017.
 */
class Presenter_Networking_pot (internal var view_rx2_pot : View_RX2_Pot) : iPresenter_pot {

    var productdetail = "data"

    override fun PotTable_dataRX2() {
        val jsonObject = JSONObject()
//        try {
//            jsonObject.put("_id", id)
//            jsonObject.put("maLoaiSP", masp)
//            jsonObject.put("tenLoaiSP", tensp)
//            jsonObject.put("soLuongSP", soluongsp)
//
////            Log.d("sasa", "$id + $masp + $tensp + $soluongsp ")
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
        val BASE_URL = "http://192.168.1.27:8080/api/"
        Rx2AndroidNetworking.post(BASE_URL + "PotTable")
                .addJSONObjectBody(jsonObject)
                .build()
                .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                    Log.d(productdetail, " timeTakenInMillis : " + timeTakenInMillis)
                    Log.d(productdetail, " bytesSent : " + bytesSent)
                    Log.d(productdetail, " bytesReceived : " + bytesReceived)
                    Log.d(productdetail, " isFromCache : " + isFromCache)
                }
                .getObjectObservable(InfoProducts::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<InfoProducts> {

                    override fun onNext(infoProducts: InfoProducts?) {
//                         Log.d(productdetail+"1", infoProducts?.listthongtin_product!![0].productlist!!.tenLoaiSP.toString())
                        Log.d(productdetail, "onResponse isMainThread : " + (Looper.myLooper() == Looper.getMainLooper()).toString())
                        view_rx2_pot.take_data_RX2_potTable(infoProducts?.listthongtin_product!!)
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        if (e is ANError) {
                            Log.d(productdetail, "onError errorCode : " + e.errorCode)
                            Log.d(productdetail, "onError errorBody : " + e.errorBody)
                            Log.d(productdetail, e.errorDetail + " : " + e.message)
                            view_rx2_pot.setErrorMessage(e.errorDetail)
                        } else {
                            Log.d(productdetail, "onError errorMessage : " + e.message)
                            view_rx2_pot.setErrorMessage(e.message!!)
                        }
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }
                })
    }


}