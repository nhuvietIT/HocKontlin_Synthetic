package com.example.nhuviet.hockontlin_synthetic_signin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.InfoProducts
import com.example.nhuviet.hockontlin_synthetic_signin.R

/**
 * Created by nhuvi on 28/09/2017.
 */

class Adater_RX2_RecyerView_PotTable(internal var context: Context, internal var infoproducts: ArrayList<InfoProducts>?)
    : RecyclerView.Adapter<Adater_RX2_RecyerView_PotTable.ViewHolder_RX2_POT>() {


    override fun getItemCount(): Int {
        return infoproducts!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder_RX2_POT {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.custom_recyclerview_pottable, parent, false)
        val viewholder_pot = ViewHolder_RX2_POT(view)
        return viewholder_pot
    }

    override fun onBindViewHolder(holder: ViewHolder_RX2_POT, position: Int) {
        val products = infoproducts!![position]
//    Log.d("ttttttttttttt",product._id+"/"+product.maLoaiSP+"/"+product.tenLoaiSP+"/"+product.soLuong)
        holder.id.setText(products!!._id)
        holder.maSP.setText(products!!.maLoaiSP)
        holder.tenSP.setText(products!!.tenLoaiSP)
        holder.soluongSP.setText(products!!.soLuongSP.toString())

        var dem = products._users_products!!.size
        for (i in 0..dem - 1) {
            holder.id_user.setText(products._users_products!![i]._id)
            holder.ho.setText(products._users_products!![i].ho)
            holder.ten.setText(products._users_products!![i].ten)
            holder.tuoi.setText(products._users_products!![i].tuoi.toString())

            holder.ma_fb.setText(products._users_products!![i].facebook!!.mafb)
            holder.email.setText(products._users_products!![i].facebook!!.email)
            holder.name.setText(products._users_products!![i].facebook!!.name)
        }
    }

    class ViewHolder_RX2_POT(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var id: TextView
        var maSP: TextView
        var tenSP: TextView
        var soluongSP: TextView
        var id_user: TextView
        var ho: TextView
        var ten: TextView
        var tuoi: TextView
        var ma_fb: TextView
        var email: TextView
        var name: TextView

        init {
            id = itemView.findViewById(R.id.tv_id) as TextView
            maSP = itemView.findViewById(R.id.tv_masp) as TextView
            tenSP = itemView.findViewById(R.id.tv_tensp) as TextView
            soluongSP = itemView.findViewById(R.id.tv_soluongsp) as TextView

            id_user = itemView.findViewById(R.id.tv__id) as TextView
            ho = itemView.findViewById(R.id.tv_ho) as TextView
            ten = itemView.findViewById(R.id.tv_ten) as TextView
            tuoi = itemView.findViewById(R.id.tv_tuoi) as TextView

            ma_fb = itemView.findViewById(R.id.tv_mafb) as TextView
            email = itemView.findViewById(R.id.tv_mail) as TextView
            name = itemView.findViewById(R.id.tv_name) as TextView
        }
    }
}