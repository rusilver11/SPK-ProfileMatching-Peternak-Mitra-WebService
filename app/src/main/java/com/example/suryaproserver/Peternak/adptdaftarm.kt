package com.example.suryaproserver.Peternak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suryaproserver.R
import com.squareup.picasso.Picasso

class adptdaftarm (val dataIjn : List<HashMap<String,String>>) :
    RecyclerView.Adapter<adptdaftarm.HolderDaftarm>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderDaftarm {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.row_daftarm,p0,false)
        return HolderDaftarm(v)
    }

    override fun getItemCount(): Int {
        return dataIjn.size
    }

    override fun onBindViewHolder(p0: HolderDaftarm, p1: Int) {
        val data = dataIjn.get(p1)

        p0.vendor.setText(data.get("VENDOR_MITRA"))
        p0.nama.setText(data.get("NAMA_MITRA"))
        p0.alamat.setText(data.get("ALAMAT_MITRA"))
        if (!data.get("FT_MITRA").equals(""))
            Picasso.get().load(data.get("FT_MITRA")).into(p0.photo1)
    }
    class HolderDaftarm (v : View) : RecyclerView.ViewHolder(v){
        val vendor = v.findViewById<TextView>(R.id.tvadpt113)
        val nama = v.findViewById<TextView>(R.id.tvadpt133)
        val alamat = v.findViewById<TextView>(R.id.tvadpt123)
        val photo1 = v.findViewById<ImageView>(R.id.imageView3)

    }

}