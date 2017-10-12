package com.example.nhuviet.hockontlin_synthetic_signin.Presenter.Laydiadiem_googleMap

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Model_GoogleMap
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.Fragment.View_Google_Map
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by nhuvi on 30/08/2017.
 */
class Presenter_takeData_googleMap(internal val View_googleMap: View_Google_Map) : IPresenter_takeData_googleMap {

    override fun Take_dataJson(ctx: Context, modelGoogle_map: Model_GoogleMap) {

        var address = modelGoogle_map.addrees
        val url = "https://maps.googleapis.com/maps/api/geocode/json?address=$address"
        val pDialog = ProgressDialog(ctx)
        pDialog.setMessage("Loading...")
        pDialog.show()
        val jsondataMap: Model_GoogleMap = Model_GoogleMap()

        // json Object
        var jsonObjecRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener<JSONObject> { response ->

                    Log.d("eeee", "Login Response:" + response.toString())
                    pDialog.hide()
                    try {
                        val js_array = response.getJSONArray("results")
                        var dem = js_array.length()
                        //address_components
                        for (i in 0..dem - 1) {
                            val jobject1 = js_array.getJSONObject(i)
                            val js_array1 = jobject1.getJSONArray("address_components")
                            // geometry
                            val jobject = js_array.getJSONObject(i).getJSONObject("geometry")
                            //formatted_address
                            val jobject2 = js_array.getJSONObject(i).getString("formatted_address")
                            jsondataMap.addrees1 = js_array.getJSONObject(i).getString("formatted_address")
                            Log.d("json_name:", jobject2.toString())

                            jsondataMap.lat = jobject.getJSONObject("location").getDouble("lat")
                            jsondataMap.long = jobject.getJSONObject("location").getDouble("lng")

                            //log
                            val ketqua2 = jobject.getJSONObject("location").getString("lng")
                            val ketqua1 = jobject.getJSONObject("location").getString("lat")
                        //    Log.d("json_name:", "${ketqua1},${ketqua2}")
                            //////////////////////
                            var dem1 = js_array1.length()
                            for (i in 0..dem1 - 1) {

                                val jobject2 = js_array1.getJSONObject(i)
                                jsondataMap.name = jobject2.getString("long_name")
                                // log
                                val ketqua = jobject2.getString("long_name")
                               // Log.d("json_name:", ketqua.toString())
                            }
                            View_googleMap.View_location(jsondataMap)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    //    Toast.makeText(ctx, response.toString(), Toast.LENGTH_SHORT).show()
                }, Response.ErrorListener {
            error ->
            Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show()
        })
        var requestqueue = Volley.newRequestQueue(ctx)
        requestqueue.add(jsonObjecRequest)

    }
}