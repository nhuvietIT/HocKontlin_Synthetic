package com.example.nhuviet.hockontlin_synthetic_signin.Presenter.signIn_Account

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Account_SignIn
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.DangNhap.View_Account
import com.example.nhuviet.hockontlin_synthetic_signin.connectInternet.AppCtrollerClass
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Created by nhuvi on 14/08/2017.
 */
class Presenter_Account(internal val view__Account: View_Account) : IPPresenter_Account {

    //=======================================================================================================================
    override fun Thuchien_Account(ctx: Context, account: Account_SignIn) {

        val tenham = "Dangkithanhvien"
        val url = "http://192.168.1.144/data_zalada/dangnhap.php"
        val pDialog = ProgressDialog(ctx)
        var kiemtra: Boolean = false
        pDialog.setMessage("Loading...")
        pDialog.show()
        var strReq = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                   // Log.d("eeee", "Login Response:" + response.toString())
                    pDialog.hide()
                    try {
                        val ketqua = JSONObject(response).getString("ketqua")
                        if (ketqua.equals("false")) {
                            kiemtra = false
                            view__Account.email_datontai()
                        } else {
                            if (ketqua.equals("true")) {
                                kiemtra = true
                                view__Account.dangki_thanhcong()
                            } else {
                                kiemtra = false
                                view__Account.dangki_thatbai()
                                }
                        }
                        Log.d("ketqua:", ketqua.toString())
                    } catch (e: JSONException) {
                        // JSON error
                        e.printStackTrace()
                        Toast.makeText(ctx, "Json error: " + e.message, Toast.LENGTH_LONG).show()
                    }
                }, Response.ErrorListener { error ->
            // Log.e("eeeeee", "Login Error: " + error.message)
            Toast.makeText(ctx, error.message, Toast.LENGTH_LONG).show()
            pDialog.hide()
        }) {
            override fun getParams(): Map<String, String> {
                // Posting parameters to login url
                val params = HashMap<String, String>()
                params.put("dangnhap", tenham)
                params.put("email", account.email.toString())
                params.put("pass",account.pass.toString())
                return params
            }
        }
        //   Adding request to request queue
        //Activity gọi
        AppCtrollerClass.instance!!.addToRequestQueue(strReq, "hello")

    }

}