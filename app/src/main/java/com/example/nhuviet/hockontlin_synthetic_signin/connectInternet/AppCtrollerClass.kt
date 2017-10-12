package com.example.nhuviet.hockontlin_synthetic_signin.connectInternet

/**
 * Created by nhuvi on 09/08/2017.
 */
import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by nhuvi on 07/08/2017.
 */

class AppCtrollerClass : Application() {

    private var mRequestQueue: RequestQueue? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return mRequestQueue!!
        }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        requestQueue.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    companion object {

        val TAG = AppCtrollerClass::class.java.simpleName

        @get:Synchronized var instance: AppCtrollerClass? = null
            private set
    }
}