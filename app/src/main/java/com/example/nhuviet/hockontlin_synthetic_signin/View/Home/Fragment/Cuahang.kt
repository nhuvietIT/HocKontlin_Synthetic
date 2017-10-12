package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.Fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Model_GoogleMap
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.SanPham
import com.example.nhuviet.hockontlin_synthetic_signin.Presenter.Laydiadiem_googleMap.Presenter_takeData_googleMap
import com.example.nhuviet.hockontlin_synthetic_signin.R
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_cacloairau
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.layout_frament_cuhang_googlemap.*
import kotlinx.android.synthetic.main.layout_frament_cuhang_googlemap.view.*


/**
 * Created by nhuvi on 11/08/2017.
 */

class Cuahang : Fragment(), View_cacloairau, OnMapReadyCallback
//        , GoogleApiClient.ConnectionCallbacks
//        , GoogleApiClient.OnConnectionFailedListener
//        , LocationListener
        , View.OnClickListener, GoogleMap.OnMarkerClickListener, View_Google_Map {


    var mMap: GoogleMap? = null
    var pre_takedata_Map: Presenter_takeData_googleMap? = null
    var mapModel = Model_GoogleMap()
    val markerOptions = MarkerOptions()


    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.layout_frament_cuhang_googlemap, container, false)
//        val presenter_code_rauanla: Presenter_cacloairau = Presenter_cacloairau(this)
//        presenter_code_rauanla.LaydanhsachSanPham_cacloairau(context)



        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
// Construct a GeoDataClient.
//  view.bt_marker.setOnClickListener(this)
//  view.bt_hinhvetinh.setOnClickListener(this)
//        view.bt_Polyline.setOnClickListener(this)
//        view.bt_Polygon.setOnClickListener(this)
//        view.bt_Cricle.setOnClickListener(this)
        view.bt_search.setOnClickListener(this)
        pre_takedata_Map = Presenter_takeData_googleMap(this)

        pre_takedata_Map!!.Take_dataJson(context, mapModel)

        return view
    }


    override fun onMapReady(googleMap: GoogleMap?) {

        mMap = googleMap

        googleMap!!.isMyLocationEnabled = true
        googleMap.uiSettings.isCompassEnabled = true
        googleMap!!.uiSettings.isZoomControlsEnabled = true
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
//        googleMap.setOnMarkerClickListener(this)

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun Hienthi_cacloairau(list_cacloairau: List<SanPham>) {
//        val obj_adapter = Adapter_RecyclerView(context, list_cacloairau)
//        home_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
//        home_recyclerView.adapter = obj_adapter
//        obj_adapter.notifyDataSetChanged()
    }

    override fun View_location(Gmap: Model_GoogleMap) {
        mapModel = Gmap

        val latlng = LatLng(mapModel.lat!!, mapModel.long!!)

        markerOptions.position(latlng)
        markerOptions.title(mapModel.addrees1)
        markerOptions.snippet(mapModel.name)

        mMap!!.addMarker(markerOptions)
//                 camera gooogle map
        val cameraPostion = CameraPosition.builder()
                .target(latlng)
                .bearing(8.6f)
                .tilt(90f)
                .zoom(15f)
                .build()
        val CameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPostion)
        mMap!!.animateCamera(CameraUpdate, 2000, null)


//        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latlng))
//        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(5F))
    }
    override fun onMarkerClick(p0: Marker?): Boolean {
      Toast.makeText(context, "id: " + p0!!.id + "\t title: " + p0.title, Toast.LENGTH_LONG).show()
        return true
    }
    override fun onClick(v: View?) {
        val id = v!!.id
        when (id) {
            R.id.bt_search -> {
                //     var ed_saerch =  v.findViewById(R.id.ed_search)
                var location: String = ed_search.text.toString().replace(" ", "%20")
                mapModel.addrees = location

                Log.d("ktt", location)
              //   mapModel.addrees1 = location
                pre_takedata_Map!!.Take_dataJson(context, mapModel)
///////////////////////////////////////////////////////////////////////////
//                var addressList: List<Address>? = null
//                Log.d("ktt", location)
////                Toast.makeText(context, ed_saerch.toString()  , Toast.LENGTH_SHORT).show()
//                if (!location.equals("")) {
//                    val geocoder = Geocoder(context)
//
//                    try {
//                        addressList = geocoder.getFromLocationName(location,5)
//                        if (addressList != null) {
//                            for (i in 0..addressList!!.size - 1) {
//                                val mapModel = Model_GoogleMap()
//
//                                val latlng = LatLng(mapModel.lat!!, mapModel.long!!)
//                                val markerOptions = MarkerOptions()
//                                markerOptions!!.position(latlng)
//                                markerOptions!!.title(location)
//                                mMap!!.addMarker(markerOptions)
//                                mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latlng))
//                                mMap!!.animateCamera(CameraUpdateFactory.zoomTo(9F))
//                            }
//                        }
//                    } catch(e: IOException) {
//                        e.printStackTrace()
//                    }
//                }

            }
//            R.id.bt_hinhvetinh -> {
//                mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                        LatLng(16.047079, 108.206230), 4f))
//                mMap!!.setMapType(Model_GoogleMap.MAP_TYPE_SATELLITE)
//
//                var latlng = LatLng(16.047079, 108.206230)
//                var location1 =  MarkerOptions()
//                location1.draggable(true)
//                location1.position(latlng)
//                location1.title("Đà Nẵng")
//                location1.snippet("Da Nang City")
//                var marker =  mMap!!.addMarker(location1)
//                marker.showInfoWindow()
//            }
//            R.id.bt_Polyline -> {
//                var viethan = LatLng(15.974968, 108.25226)
//                var home = LatLng(15.885931, 108.24245)
//                var hoian = LatLng(15.880441, 108.34991)
//                var langbichhoa = LatLng(15.603693, 108.54320)
//
//                mMap!!.addPolyline(PolylineOptions()
//                        .add(viethan, home)
//                        .color(Color.BLUE)
//                )
//
//            }
//            R.id.bt_Polygon -> {
//                var viethan = LatLng(15.974968, 108.25226)
//                var home = LatLng(15.885931, 108.24245)
//                var hoian = LatLng(15.880441, 108.34991)
//                var langbichhoa = LatLng(15.603693, 108.54320)
//
//                mMap!!.addPolygon(PolygonOptions()
//                        .add(viethan, home, hoian)
//                        .strokeColor(Color.BLACK)
//                        .fillColor(Color.CYAN)
//
//
//                )
//
//            }
//            R.id.bt_Cricle -> {
//                var viethan = LatLng(15.974968, 108.25226)
//                mMap!!.addCircle(CircleOptions()
//                        .center(viethan)
//                        .radius(500.0)
//                        .strokeColor(Color.GREEN)
//                )
//            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////
}