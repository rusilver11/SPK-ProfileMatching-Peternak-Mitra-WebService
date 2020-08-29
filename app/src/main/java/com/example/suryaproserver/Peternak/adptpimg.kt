package com.example.suryaproserver.Peternak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.suryaproserver.R
import com.squareup.picasso.Picasso

class adptpimg(val dataBio : List<HashMap<String,String>>) :
    RecyclerView.Adapter<adptpimg.HolderDataRecipe>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderDataRecipe {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.row_adptpimg,p0,false)
        return HolderDataRecipe(v)
    }

    override fun getItemCount(): Int {
        return dataBio.size
    }

    override fun onBindViewHolder(p0: HolderDataRecipe, p1: Int) {
        val data = dataBio.get(p1)

        if (!data.get("FT_PETER").equals(""))
            Picasso.get().load(data.get("FT_PETER")).into(p0.photo1)

    }

    class HolderDataRecipe(v : View) : RecyclerView.ViewHolder(v){
        val photo1 = v.findViewById<ImageView>(R.id.imageView2)

    }
}