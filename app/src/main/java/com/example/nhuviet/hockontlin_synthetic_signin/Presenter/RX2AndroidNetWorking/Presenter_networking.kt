package sega.fastnetwork.test.presenter

import android.os.Looper
import android.util.Log
import com.androidnetworking.error.ANError
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.Product
import com.example.nhuviet.hockontlin_synthetic_signin.Presenter.RX2AndroidNetWorking.iPresenter_networking
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_RX2androidNetWorking.View_RX2_Android_NetWorking
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject


/**
 * Created by sega4 on 27/07/2017.
 */

class Presenter_networking(internal var view_rx2: View_RX2_Android_NetWorking)
    : iPresenter_networking {

    var productdetail = "PRODUCTDETAIL"

    override fun take_dataRX2(id: String, masp: String, tensp: String, soluongsp: Int) {
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
        Rx2AndroidNetworking.post(BASE_URL + "postall")
                .addJSONObjectBody(jsonObject)
                .build()
                .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                    Log.d(productdetail, " timeTakenInMillis : " + timeTakenInMillis)
                    Log.d(productdetail, " bytesSent : " + bytesSent)
                    Log.d(productdetail, " bytesReceived : " + bytesReceived)
                    Log.d(productdetail, " isFromCache : " + isFromCache)
                }
                .getObjectObservable(Product::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Product> {

                    override fun onNext(product: Product?) {
                        Log.d(productdetail + "2222222", product?.listproduct!![0].tenLoaiSP!!.toString())
                        Log.d(productdetail, "onResponse isMainThread : " + (Looper.myLooper() == Looper.getMainLooper()).toString())
                        view_rx2.take_data_RX2(product?.listproduct!!)
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        if (e is ANError) {
                            Log.d(productdetail, "onError errorCode : " + e.errorCode)
                            Log.d(productdetail, "onError errorBody : " + e.errorBody)
                            Log.d(productdetail, e.errorDetail + " : " + e.message)
                            view_rx2.setErrorMessage(e.errorDetail)
                        } else {
                            Log.d(productdetail, "onError errorMessage : " + e.message)
                            view_rx2.setErrorMessage(e.message!!)
                        }
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }
                })
    }

    override fun create_dataRX2(masp: String, tensp: String, soluongsp: String, _users_producst: String) {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("maLoaiSP", masp)
            jsonObject.put("tenLoaiSP", tensp)
            jsonObject.put("soLuongSP", soluongsp)
            jsonObject.put("_users_producst", _users_producst)

            Log.d("sasa", " $masp + $tensp + $soluongsp + $_users_producst")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val BASE_URL = "http://192.168.1.27:8080/api/"
        Rx2AndroidNetworking.post(BASE_URL + "product")
                .addJSONObjectBody(jsonObject)
                .build()
                .setAnalyticsListener { timeTakenInMillis, bytesSent, bytesReceived, isFromCache ->
                    Log.d(productdetail, " timeTakenInMillis : " + timeTakenInMillis)
                    Log.d(productdetail, " bytesSent : " + bytesSent)
                    Log.d(productdetail, " bytesReceived : " + bytesReceived)
                    Log.d(productdetail, " isFromCache : " + isFromCache)
                }
                .getObjectObservable(Product::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Product> {

                    override fun onNext(product: Product?) {
//                        Log.d(productdetail + "2222222", product?.listproduct!![0].tenLoaiSP!!.toString())
                        Log.d(productdetail, "onResponse isMainThread : " + (Looper.myLooper() == Looper.getMainLooper()).toString())
                        view_rx2.take_data_RX2(product?.listproduct!!)

                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        if (e is ANError) {
                            Log.d(productdetail, "onError errorCode : " + e.errorCode)
                            Log.d(productdetail, "onError errorBody : " + e.errorBody)
                            Log.d(productdetail, e.errorDetail + " : " + e.message)
                            view_rx2.setErrorMessage(e.errorDetail)
                        } else {
                            Log.d(productdetail, "onError errorMessage : " + e.message)
                            view_rx2.setErrorMessage(e.message!!)
                        }
                    }

                    override fun onSubscribe(d: Disposable?) {

                    }


                })
    }




}
