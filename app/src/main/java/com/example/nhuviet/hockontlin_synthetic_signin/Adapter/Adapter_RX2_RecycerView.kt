package com.example.nhuviet.hockontlin_synthetic_signin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.Test_RX2AndroidNetWorking.Product
import com.example.nhuviet.hockontlin_synthetic_signin.R

/**
 * Created by nhuvi on 27/09/2017.
 */
class Adapter_RX2_RecycerView(internal var context: Context, internal var products: ArrayList<Product>?)
    : RecyclerView.Adapter<Adapter_RX2_RecycerView.ViewHolder_RX2>() {


    override fun getItemCount(): Int {
        return products!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder_RX2 {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cutom_recycler_rx2, parent, false)
        val viewholder_rx2 = ViewHolder_RX2(view)
        return viewholder_rx2
    }

    override fun onBindViewHolder(holder: ViewHolder_RX2, position: Int) {
        val product = products!![position]
//    Log.d("ttttttttttttt",product._id+"/"+product.maLoaiSP+"/"+product.tenLoaiSP+"/"+product.soLuong)
        holder.id.setText(product._id)
        holder.maSP.setText(product.maLoaiSP)
        holder.tenSP.setText(product.tenLoaiSP)
        holder.soluongSP.setText(product.soLuongSP.toString())
    }

    class ViewHolder_RX2(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var id: TextView
        var maSP: TextView
        var tenSP: TextView
        var soluongSP: TextView

        init {
            id = itemView.findViewById(R.id.tv_id) as TextView
            maSP = itemView.findViewById(R.id.tv_masp) as TextView
            tenSP = itemView.findViewById(R.id.tv_tensp) as TextView
            soluongSP = itemView.findViewById(R.id.tv_soluongsp) as TextView

        }
    }

    class ViewHolder_RX2_POT(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }
}