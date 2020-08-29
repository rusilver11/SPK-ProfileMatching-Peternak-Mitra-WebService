package com.example.suryaproserver.Mitra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.R
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_hasilrekom_mitra.*
import kotlinx.android.synthetic.main.act_hasilrekom_peternak.rv_adptrekom
import org.json.JSONArray

class hasilrekom_mitra : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_hasilrekom_mitra)

        RekomAdapter = adptrekom(daftarp, this)
        rv_adptrekom.layoutManager = LinearLayoutManager(this)
        rv_adptrekom.adapter = RekomAdapter

        var  hm = intent.extras!!
        ass1 = hm.getString("KAPASITAS_KAPS").toString()
        ass2 = hm.getString("JENIS_KAPS").toString()
        asb1 = hm.getString("JML_KAPS").toString()
        asb2 = hm.getString("FKP_SUHU").toString()
        asp1 = hm.getString("FKP_TMPTMKN").toString()
        asp2 = hm.getString("PENGALAMAN_PTRNK").toString()
        asp3 = hm.getString("JMLTNG_PTRNK").toString()

//        hm.putString("KAPASITAS_KAPS",type1)
//        hm.putString("JENIS_KAPS",type2)
//        hm.putString("JML_KAPS",type3)
//        hm.putString("FKP_SUHU",type4)
//        hm.putString("FKP_TMPTMKN",type5)
//        hm.putString("PENGALAMAN_PTRNK",type6)
//        hm.putString("JMLTNG_PTRNK",type7)

        if (ass1 == "2000+ ekor") {
            ass1 = "1"
        }else if(ass1 == "3000+ ekor") {
            ass1 = "2"
        }else if(ass1 == "5000+ ekor" ){
            ass1 = "3"
        }else if(ass1 == "7000+ ekor") {
            ass1 = "4"
        }else if(ass1 == "10000+ ekor" ){
            ass1 = "5"
        }

        if (ass2 == "Open") {
            ass2 = "1"
        }else if(ass2 == "Close") {
            ass2 = "2"
        }

        if (asb1 == "1 Single") {
            asb1 = "1"
        }else if(asb1 == "1 Tingkat") {
            asb1 = "2"
        }else if(asb1 == "2 Single" ){
            asb1 = "3"
        }else if(asb1 == "2 Tingkat" ){
            asb1 = "4"
        }

        if (asb2 == "Pemanas Kompor Canopy, Kipas, Tirai") {
            asb2 = "1"
        }else if(asb2 == "Gas Brooder, Kipas, Tirai") {
            asb2 = "2"
        }else if(asb2 == "Brooder Elektrik, Blower Fan, Cooling Pad" ){
            asb2 = "3"
        }

        if (asp1 == "Tempat Minum, Tempat Ransum Ayam") {
            asp1 = "1"
        }else if(asp1 == "Tempat Minum, Tempat Ransum+Baby Chick") {
            asp1 = "2"
        }else if(asp1 == "Tempat Minum Otomatis, Tempat Ransum+Baby Chick" ){
            asp1 = "3"
        }else if(asp1 == "Niple Drinken, Super Feeder" ){
            asp1 = "4"
        }


        if (asp2 == "Baru") {
            asp2 = "1"
        }else if(asp2 == "Baru Ex") {
            asp2 = "2"
        }else if(asp2 == "6 Bulan +" ){
            asp2 = "3"
        }else if(asp2 == "1 Tahun +" ){
            asp2 = "4"
        }

        if(asp3 == "1-2"){
            asp3 = "1"
        }else if (asp3 == "3-5") {
            asp3 = "2"
        }else if(asp3 == "6+"){
            asp3 = "3"
        }
        btn_tabel.setOnClickListener(View.OnClickListener {
            finish()
            val intent = Intent(this, hasilhitung_mitra::class.java)
            startActivity(intent)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        daftarp.clear()
        val intent = Intent(this, rekomendasi_mitra::class.java)
        startActivity(intent)
        finish()
    }

    lateinit var RekomAdapter : adptrekom
    var daftarp = mutableListOf<HashMap<String,String>>()

    var ass1 = ""
    var ass2 = ""
    var asb1 = ""
    var asb2 = ""
    var asp1 = ""
    var asp2 = ""
    var asp3 = ""


    fun showDataBobot(sdm : String,pp : String,kaps : String,jns : String,jmlk : String,suhu : String,tmptmkn : String){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_BOBOTASPEKM,
            Response.Listener{ response ->
                daftarp.clear()
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()

                    dt.put("ID_PETER",jsonObject.getString("ID_PETER"))
                    dt.put("NAMA_PETER",jsonObject.getString("NAMA_PETER"))
                    dt.put("NAMA_PEMILIK",jsonObject.getString("NAMA_PEMILIK"))
                    dt.put("ALAMAT_PETER",jsonObject.getString("ALAMAT_PETER"))
                    dt.put("THN_BERDIRI",jsonObject.getString("THN_BERDIRI"))
                    dt.put("NOHP_PETER",jsonObject.getString("NOHP_PETER"))
                    dt.put("STATUS_KEMITRA",jsonObject.getString("STATUS_KEMITRA"))
                    dt.put("STATUS_LAHAN",jsonObject.getString("STATUS_LAHAN"))
                    dt.put("FTPETER",jsonObject.getString("FTPETER"))
                    dt.put("nilai",jsonObject.getString("nilai"))
                    daftarp.add(dt)
                }
                RekomAdapter.notifyDataSetChanged()

            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Terjadi kesalahan Koneksi Server", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                val hm = HashMap<String,String>()
                hm.put("JMLTNG_PTRNK",sdm)
                hm.put("PENGALAMAN_PTRNK", pp)
                hm.put("KAPASITAS_KAPS", kaps)
                hm.put("JENIS_KAPS", jns)
                hm.put("JML_KAPS", jmlk)
                hm.put("FKP_SUHU", suhu)
                hm.put("FKP_TMPTMKN", tmptmkn)



                return hm
            }

        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    override fun onStart() {
        super.onStart()
        showDataBobot(asp3,asp2,ass1,ass2,asb1,asb2,asp1)

    }
}
