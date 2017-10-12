package com.example.nhuviet.hockontlin_synthetic_signin.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nhuviet.hockontlin_synthetic_signin.Model.Object_Model.SanPham
import com.example.nhuviet.hockontlin_synthetic_signin.R
import com.squareup.picasso.Picasso

/**
 * Created by nhuvi on 09/08/2017.
 */
class Adapter_RecyclerView(internal var context: Context, internal var cacloairau: List<SanPham>)
    : RecyclerView.Adapter<Adapter_RecyclerView.ViewHolder_cacloairau>() {

    override fun getItemCount(): Int {
        return cacloairau.size
    }

    override fun onBindViewHolder(holder: ViewHolder_cacloairau, position: Int) {

        val cacloairau = cacloairau[position]

        holder.tenSP.setText(cacloairau.tenSP)
//        var numberFormat = DecimalFormat("###,###")
//       // var gia = numberFormat.format(cacloairau.gia.toString())
//        holder.giaSP.setText("$gia VNĐ")

//        val numberFormat1 = DecimalFormat("###,###")
//        val gia = numberFormat1.format(cacloairau.gia)
        holder.giaSP.setText(cacloairau.gia + " VNĐ")
        Picasso.with(context).load(cacloairau.hinhSP).resize(220,220).into(holder.hinhSPp)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder_cacloairau {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(R.layout.custom_recyclerview_cacloairau,parent,false)
            val viewholder_rau = ViewHolder_cacloairau(view)
            return viewholder_rau
    }

    class ViewHolder_cacloairau(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tenSP: TextView
        var giaSP: TextView
        var hinhSPp: ImageView

        init {
            tenSP = itemView.findViewById(R.id.tx_ct_tenSP) as TextView
            giaSP = itemView.findViewById(R.id.tx_ct_giaSp) as TextView
            hinhSPp = itemView.findViewById(R.id.im_ct_hinhSP) as ImageView
        }

    }


}