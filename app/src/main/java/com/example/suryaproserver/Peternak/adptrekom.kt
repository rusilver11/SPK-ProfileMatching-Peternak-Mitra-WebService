package com.example.suryaproserver.Peternak

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suryaproserver.R
import com.squareup.picasso.Picasso


class adptrekom (val dataIjn : List<HashMap<String,String>>, val rekom : hasilrekom_peternak) :
    RecyclerView.Adapter<adptrekom.HolderDataMhs>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderDataMhs {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.row_nilai,p0,false)
        return HolderDataMhs(v)
    }

    override fun getItemCount(): Int {
        return dataIjn.size
    }


    override fun onBindViewHolder(p0: HolderDataMhs, p1: Int) {
        val dt = dataIjn.get(p1)

        p0.nama.setText(dt.get("NAMA_MITRA"))
        p0.alamat.setText(dt.get("ALAMAT_MITRA").toString())
//        p0.tahun.setText(dt.get("THN_BERDIRI"))
//        p0.nomer.setText(dt.get("CONTACT_MITRA"))
//        p0.jmb.setText(dt.get("JMB_MITRA").toString())
        p0.nilai.setText(dt.get("nilai"))
//        p0.jmt.setText(dt.get("JMT_MITRA").toString())
        p0.vendor.setText(dt.get("VENDOR_MITRA").toString())
        if (!dt.get("FTMITRA").equals(""))
            Picasso.get().load(dt.get("FTMITRA")).into(p0.poto)
//        if (p1.rem(2) == 0) p0.layouts.setBackgroundColor(Color.rgb(230,245,240))
//        else p0.layouts.setBackgroundColor(Color.rgb(255,255,245))
        p0.itemView.setOnClickListener(View.OnClickListener{
            val idpt = dt.get("ID_MITRA")
            val context = rekom
            val intent = Intent(context, detail_hasil_peternak::class.java)
            intent.putExtra("id",idpt)
            context.startActivity(intent)
        })

//        (p0 as PartViewHolder).bind(dataIjn[p1], clickListener)
//    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(part:HashMap<String,String>, clickListener: (PartData) -> Unit) {
//            itemView.tvadpt13.text = part.id.toString()
//            itemView.setOnClickListener { clickListener(part)}
//        }
//    }
    }
    class HolderDataMhs (v : View) : RecyclerView.ViewHolder(v){

        val nama = v.findViewById<TextView>(R.id.tvadpt13)
        val nilai = v.findViewById<TextView>(R.id.textView51)
        val alamat = v.findViewById<TextView>(R.id.textView28peter)
//        val tahun = v.findViewById<TextView>(R.id.textView42)
//        val nomer = v.findViewById<TextView>(R.id.textView50)
//        val jmb = v.findViewById<TextView>(R.id.textView53)
//        val jmt = v.findViewById<TextView>(R.id.textView54)
        val vendor = v.findViewById<TextView>(R.id.textView42)
        val poto = v.findViewById<ImageView>(R.id.imageViewrownilai)

    }
}