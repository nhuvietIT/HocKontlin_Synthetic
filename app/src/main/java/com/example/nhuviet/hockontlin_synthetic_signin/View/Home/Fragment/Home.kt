package com.example.nhuviet.hockontlin_synthetic_signin.View.Home.Fragment

import android.os.Bundle
import android.os.Looper
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.example.nhuviet.hockontlin_synthetic_signin.Adapter.Adapter_RecyclerView
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.SanPham
import com.example.nhuviet.hockontlin_synthetic_signin.R
import com.example.nhuviet.hockontlin_synthetic_signin.View.Home.View_cacloairau
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.layout_chats.*
import kotlinx.android.synthetic.main.layout_chats.view.*
import kotlinx.android.synthetic.main.layout_fragment_home.*
import org.json.JSONException
import org.json.JSONObject


/**
 * Created by nhuvi on 11/08/2017.
 */
class Home : Fragment(), View_cacloairau, View.OnClickListener {


    var msocket: Socket? = null
    var arraylistUser  : ArrayList<String>? = null
    var adapterUser : ArrayAdapter<String>? = null
    var arraylistChat : ArrayList<String>? = null
    var adapterChat: ArrayAdapter<String>? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_chats, container, false)

//        val presenter_code_rauanla: Presenter_cacloairau = Presenter_cacloairau(this)
//       presenter_code_rauanla.LaydanhsachSanPham_cacloairau(context)
        msocket = IO.socket("http://192.168.1.25:3000/")
        msocket!!.connect()
        msocket!!.on("server_send_datakq", onRetrieveData)
        msocket!!.on("server_send_user", onListUser)
        msocket!!.on("client_send", onList_send)
//            msocket!!.emit("app_send_data","Lap Trinh Kotlin & NodeJS")


        arraylistUser = ArrayList<String>()
        adapterUser = ArrayAdapter(context, android.R.layout.simple_list_item_1, arraylistUser)
        view.listView_user.adapter = adapterUser

        arraylistChat = ArrayList<String>()
        adapterChat = ArrayAdapter(context, android.R.layout.simple_list_item_1, arraylistChat)
        view.listView_chat.adapter = adapterChat


        view.im_add_user.setOnClickListener(this)
        view.im_send.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        val id = v!!.id
        when (id) {
            R.id.im_add_user -> {
                if (ed_nhapchat.text.toString().trim().length > 0) {
                    msocket!!.emit("client_user", ed_nhapchat.text.toString())
                }
            }
            R.id.im_send ->{
                if (ed_nhapchat.text.toString().trim().length > 0) {
                    msocket!!.emit("client_send", ed_nhapchat.text.toString())
                }else{
                    Toast.makeText(context, "moi nhập", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private val onList_send = Emitter.Listener { args ->
        activity.runOnUiThread {
            if (Looper.myLooper() == null) {
                Looper.prepare()
            }
            val jsonObject = args[0] as JSONObject
            try {
                val chat= jsonObject.getString("chat")
                Log.d("chat",chat)
//                Toast.makeText(context, chat, Toast.LENGTH_SHORT).show()
                arraylistChat!!.add(chat)
                adapterChat!!.notifyDataSetChanged()

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
    private val onRetrieveData = Emitter.Listener { args ->
        activity.runOnUiThread {
            if (Looper.myLooper() == null) {
                Looper.prepare()
            }
            val jsonObiect = args[0] as JSONObject
            try {
                val ketqua: Boolean = jsonObiect.getBoolean("ketqua")

                if (ketqua) {
                    Log.d("kty", "Tai Khoan da ton tai" + ketqua.toString())
                    Toast.makeText(context, "Tai Khoan da ton tai", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("kty", "Tai Khoan Dang Ki Thanh Cong" + ketqua.toString())
                    Toast.makeText(activity, "Tai Khoan Dang Ki Thanh Cong", Toast.LENGTH_SHORT).show()
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    private val onListUser = Emitter.Listener { args ->
        activity.runOnUiThread {
            if (Looper.myLooper() == null) {
                Looper.prepare()
            }
            val jsonObject = args[0] as JSONObject
            try {
                val jsonArray = jsonObject.getJSONArray("danhsach")

                arraylistUser!!.clear()
                for (i in 0..jsonArray.length() - 1) {
                    val userName = jsonArray.get(i).toString()
                    arraylistUser !!.add(userName)
                }
                    adapterUser!!.notifyDataSetChanged()

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }


    override fun Hienthi_cacloairau(list_cacloairau: List<SanPham>) {
        val obj_adapter = Adapter_RecyclerView(context, list_cacloairau)
        home_recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
        home_recyclerView.adapter = obj_adapter
        obj_adapter.notifyDataSetChanged()
    }

}
