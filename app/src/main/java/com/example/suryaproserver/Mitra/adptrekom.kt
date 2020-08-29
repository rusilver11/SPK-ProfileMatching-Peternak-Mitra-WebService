package com.example.suryaproserver.Mitra

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suryaproserver.R
import com.squareup.picasso.Picasso


class adptrekom (val dataIjn : List<HashMap<String,String>>,val rekom : hasilrekom_mitra) :
    RecyclerView.Adapter<adptrekom.HolderDataMhs>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderDataMhs {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.row_nilai_mitra,p0,false)
        return HolderDataMhs(v)
    }

    override fun getItemCount(): Int {
        return dataIjn.size
    }

    override fun onBindViewHolder(p0: HolderDataMhs, p1: Int) {
        val dt = dataIjn.get(p1)

//        p0.namaPemilik.setText(dt.get("NAMA_PEMILIK").toString())
        p0.namaPeter.setText(dt.get("NAMA_PETER").toString())
        p0.alamat.setText(dt.get("ALAMAT_PETER").toString())
//        p0.tahun.setText(dt.get("THN_BERDIRI"))
//        p0.cp.setText(dt.get("NOHP_PETER"))
        p0.statusMitra.setText(dt.get("STATUS_KEMITRA").toString())
        p0.statusLahan.setText(dt.get("STATUS_LAHAN").toString())
        p0.nilai.setText(dt.get("nilai"))
        if (!dt.get("FTPETER").equals(""))
            Picasso.get().load(dt.get("FTPETER")).into(p0.photo1)

        p0.itemView.setOnClickListener(View.OnClickListener{
            val idmt = dt.get("ID_PETER")
            val context = rekom
            val intent = Intent(context, detail_hasil_mitra::class.java)
            intent.putExtra("id",idmt)
            context.startActivity(intent)
        })

    }
    class HolderDataMhs (v : View) : RecyclerView.ViewHolder(v){
//        val namaPemilik = v.findViewById<TextView>(R.id.textViewpemilik)
        val namaPeter = v.findViewById<TextView>(R.id.textView26mitra)
        val alamat = v.findViewById<TextView>(R.id.textViewalamat)
//        val tahun = v.findViewById<TextView>(R.id.textViewcp)
//        val cp = v.findViewById<TextView>(R.id.textViewcp)
        val statusMitra = v.findViewById<TextView>(R.id.textViewstatusmitra)
        val statusLahan = v.findViewById<TextView>(R.id.textViewstatuslahan)
        val nilai = v.findViewById<TextView>(R.id.textView28)
        val photo1 = v.findViewById<ImageView>(R.id.imageViewmitramitra)


    }

}