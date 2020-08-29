package com.example.suryaproserver.Peternak

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
import kotlinx.android.synthetic.main.act_hasilrekom_peternak.*
import org.json.JSONArray

class hasilrekom_peternak : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_hasilrekom_peternak)

        RekomAdapter = adptrekom(daftarp, this)
        rv_adptrekom.layoutManager = LinearLayoutManager(this)
        rv_adptrekom.adapter = RekomAdapter

        var  hm = intent.extras!!
        ass1 = hm.getString("PAKAN_SAP").toString()
        ass2 = hm.getString("DOC_SAP").toString()
        asb1 = hm.getString("JML_MODAL").toString()
        asb2 = hm.getString("KOMISI_MODAL").toString()
        asp1 = hm.getString("PPL_PN").toString()
        asp2 = hm.getString("KESEHATAN_PN").toString()
        asp3 = hm.getString("CHECK_PN").toString()

        if (ass1 == "6.000+") {
            ass1 = "1"
        }else if(ass1 == "7.000+") {
            ass1 = "2"
        }else if(ass1 == "8.000+" ){
            ass1 = "3"
        }
        if (ass2 == "4.000/ekor") {
            ass2 = "1"
        }else if(ass2 == "5.000/ekor") {
            ass2 = "2"
        }else if(ass2 == "6.000/ekor") {
            ass2 = "3"
        }

        if (asb1 == "3 Juta - 4 Juta") {
            asb1 = "1"
        }else if(asb1 == "5 Juta - 6 Juta") {
            asb1 = "2"
        }else if(asb1 == "7 Juta - 8 Juta" ){
            asb1 = "3"
        }

        if (asb2 == "Kontrak") {
            asb2 = "1"
        }else if(asb2 == "Bobot Panen") {
            asb2 = "2"
        }else if(asb2 == "Harga Pasar" ){
            asb2 = "3"
        }

        if (asp1 == "Konsultasi") {
            asp1 = "1"
        }else if(asp1 == "Konsultasi, Manajement") {
            asp1 = "2"
        }else if(asp1 == "Konsultasi, Manajement, Kesehatan" ){
            asp1 = "3"
        }


        if (asp2 == "Tidak") {
            asp2 = "1"
        }else if(asp2 == "Eksternal") {
            asp2 = "2"
        }else if(asp2 == "Internal" ){
            asp2 = "3"
        }

        if(asp3 == "Fleksibel"){
            asp3 = "1"
        }else if (asp3 == "1 Minggu 2x") {
            asp3 = "2"
        }else if(asp3 == "5 Hari"){
            asp3 = "3"
        }else if(asp3 == "3 Hari"){
            asp3 = "4"
        }
        btn_tabel2.setOnClickListener(View.OnClickListener {
            finish()
            val intent = Intent(this, hasilhitung_peternak::class.java)
            startActivity(intent)
        })
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



    fun showDataBobot(pakan : String,doc : String,modal : String,komisi : String,ppl : String,kesehatan : String,check : String){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_BOBOTASPEK,
            Response.Listener{ response ->
                daftarp.clear()
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()
                    dt.put("ID_MITRA",jsonObject.getString("ID_MITRA"))
                    dt.put("NAMA_MITRA",jsonObject.getString("NAMA_MITRA"))
                    dt.put("ALAMAT_MITRA",jsonObject.getString("ALAMAT_MITRA"))
                    dt.put("THN_BERDIRI",jsonObject.getString("THN_BERDIRI"))
                    dt.put("CONTACT_MITRA",jsonObject.getString("CONTACT_MITRA"))
                    dt.put("JMB_MITRA",jsonObject.getString("JMB_MITRA"))
                    dt.put("JMT_MITRA",jsonObject.getString("JMT_MITRA"))
                    dt.put("VENDOR_MITRA",jsonObject.getString("VENDOR_MITRA"))
                    dt.put("FTMITRA",jsonObject.getString("FTMITRA"))
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
                hm.put("pakan",pakan)
                hm.put("doc", doc)
                hm.put("modal", modal)
                hm.put("komisi", komisi)
                hm.put("ppl", ppl)
                hm.put("kesehatan", kesehatan)
                hm.put("check", check)

                return hm
            }

        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    override fun onStart() {
        super.onStart()
        showDataBobot(ass1,ass2,asb1,asb2,asp1,asp2,asp3)
//        tvtest1.setText(asp2)
//        tvtest2.setText(asp3)
    }
}
