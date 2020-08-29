package com.example.suryaproserver.Mitra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suryaproserver.R
import com.squareup.picasso.Picasso

class adptdaftarp (val dataIjn : List<HashMap<String,String>>) :
    RecyclerView.Adapter<adptdaftarp.HolderDaftarm>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderDaftarm {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.row_daftarp,p0,false)
        return HolderDaftarm(v)
    }

    override fun getItemCount(): Int {
        return dataIjn.size
    }

    override fun onBindViewHolder(p0: HolderDaftarm, p1: Int) {
        val data = dataIjn.get(p1)

        p0.pemilik.setText(data.get("NAMA_PEMILIK"))
        p0.nama.setText(data.get("NAMA_PETER"))
        p0.alamat.setText(data.get("ALAMAT_PETER"))
        if (!data.get("FT_PETER").equals(""))
            Picasso.get().load(data.get("FT_PETER")).into(p0.photo1)
    }
    class HolderDaftarm (v : View) : RecyclerView.ViewHolder(v){
        val pemilik = v.findViewById<TextView>(R.id.tvadpt117)
        val nama = v.findViewById<TextView>(R.id.tvadpt137)
        val alamat = v.findViewById<TextView>(R.id.tvadpt127)
        val photo1 = v.findViewById<ImageView>(R.id.imageView7)

    }

}