package com.example.nhuviet.hockontlin_synthetic_signin.Presenter

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.SanPham
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_cacloairau
import com.example.nhuviet.hockontlin_synthetic_signin.connectInternet.AppCtrollerClass
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap
import kotlin.collections.ArrayList


/**
 * Created by nhuvi on 09/08/2017.
 */
class Presenter_cacloairau (internal var view_cacloairau : View_cacloairau) : iPresenter_cacloairau  {

    override fun LaydanhsachSanPham_cacloairau(ctx: Context) {

        val tenham = "Laydanhsachcacloairau"
        val url = "http://192.168.1.144/data_zalada/loaisanpham.php"
        val sanphams = ArrayList<SanPham>()
        val pDialog = ProgressDialog(ctx)
        pDialog.setMessage("Loading...")
        pDialog.show()
        var  strReq = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                  Log.d("eeee", "Login Response:" + response.toString())
                    pDialog.hide()
                    try {
                        val jObj = JSONObject(response)
                        val jArray = jObj.getJSONArray("ThucPhamXanh")
                        var dem = jArray.length()
                        for (i in 0..dem - 1) {

                            val jobjec = jArray.getJSONObject(i)
                            val sanpham: SanPham = SanPham()

                            sanpham.tenSP = jobjec.getString("tenSanPham")
                            sanpham.hinhSP = jobjec.getString("hinhLon")
                            sanpham.gia = jobjec.getString("gia")

                       Log.d("ok",sanpham.gia.toString())
                            sanphams.add(sanpham)
                        }
                        view_cacloairau.Hienthi_cacloairau(sanphams)
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

                params.put("ham",tenham)
                return params
            }
        }
        //   Adding request to request queue
        //Activity gọi
        AppCtrollerClass.instance!!.addToRequestQueue(strReq, "hello")


    }

}