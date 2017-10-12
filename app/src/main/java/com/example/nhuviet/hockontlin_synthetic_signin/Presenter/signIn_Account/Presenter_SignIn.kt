package com.example.nhuviet.hockontlin_synthetic_signin.Presenter.signIn_Account

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Account_SignIn
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.DangNhap.View_SignIn
import com.example.nhuviet.hockontlin_synthetic_signin.connectInternet.AppCtrollerClass
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Created by nhuvi on 15/08/2017.
 */
class Presenter_SignIn(internal var view_SignIn: View_SignIn) : iPresenter_SignIn{


    override fun Laythongtin_Sinin(ctx: Context, signIn: Account_SignIn) {
        val tenham = "KTDangnhap"
        val url = "http://192.168.1.144/data_zalada/dangnhap.php"
        val pDialog = ProgressDialog(ctx)
        var kiemtra : Boolean  = false
        pDialog.setMessage("Loading...")
        pDialog.show()
        var strReq = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Log.d("eeee", "Login Response:" + response.toString())
                    pDialog.hide()
                    try {
                        val ketqua = JSONObject(response).getString("tenDangNhap")

                        Log.d("ketqua:",ketqua.toString())

                        if (ketqua.equals(signIn.email.toString())) {
                            kiemtra = true
                            view_SignIn.dangki_thanhcong()
                        } else {
                            kiemtra = false
                            view_SignIn.dangki_thatbai()
                        }
                    } catch (e: JSONException) {
                        // JSON error
                        e.printStackTrace()
                        Toast.makeText(ctx, "loi sever!", Toast.LENGTH_LONG).show()
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
                params.put("email", signIn.email.toString())
                params.put("pass", signIn.pass.toString())

                return params
            }
        }
        //   Adding request to request queue
        //Activity gọi
        AppCtrollerClass.instance!!.addToRequestQueue(strReq, "hello")
    }
}