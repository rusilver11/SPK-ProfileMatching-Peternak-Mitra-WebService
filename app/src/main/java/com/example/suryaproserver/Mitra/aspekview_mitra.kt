package com.example.suryaproserver.Mitra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.R
import com.example.suryaproserver.SharedPref
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_aspekview_mitra.*
import org.json.JSONArray

class aspekview_mitra : AppCompatActivity(), View.OnClickListener {
    var tvp = SharedPref.getInstance(this).user
    var guserm = tvp.idusr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_aspekview_mitra)

        btn_meditaspek.setOnClickListener(this)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_mitra::class.java)
        startActivity(intent)
        finish()
    }
    var ass1 = ""
    var ass2 = ""
    var asb1 = ""
    var asb2 = ""
    var asp1 = ""
    var asp2 = ""
    var asp3 = ""

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_meditaspek ->{
                val intent = Intent(this, aspek_mitra::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    fun showDataAspek(idm : String){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_ASPEKMITRAVIEW,
            Response.Listener{ response ->
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()
                    dt.put("ppl_pn",jsonObject.getString("ppl_pn"))
                    dt.put("kesehatan_pn",jsonObject.getString("kesehatan_pn"))
                    dt.put("check_pn",jsonObject.getString("check_pn"))
                    dt.put("jml_modal",jsonObject.getString("jml_modal"))
                    dt.put("komisi_modal",jsonObject.getString("komisi_modal"))
                    dt.put("doc_sap",jsonObject.getString("doc_sap"))
                    dt.put("pakan_sap",jsonObject.getString("pakan_sap"))

                    ass1 = dt.get("pakan_sap").toString()
                    ass2 = dt.get("doc_sap").toString()
                    asb1 = dt.get("jml_modal").toString()
                    asb2 = dt.get("komisi_modal").toString()
                    asp1 = dt.get("ppl_pn").toString()
                    asp2 = dt.get("kesehatan_pn").toString()
                    asp3 = dt.get("check_pn").toString()

                    if (ass1 == "1") {
                        ass1 = "6.000+"
                    }else if(ass1 == "2") {
                        ass1 = "7.000+"
                    }else if(ass1 == "3" ){
                        ass1 = "8.000+"
                    }

                    if (ass2 == "1") {
                        ass2 = "4.000/ekor"
                    }else if(ass2 == "2") {
                        ass2 = "5.000/ekor"
                    }else if(ass2 == "3") {
                        ass2 = "6.000/ekor"
                    }

                    if (asb1 == "1") {
                        asb1 = "3 Juta - 4 Juta"
                    }else if(asb1 == "2") {
                        asb1 = "5 Juta - 6 Juta"
                    }else if(asb1 == "3" ){
                        asb1 = "7 Juta - 8 Juta"
                    }

                    if (asb2 == "1") {
                        asb2 = "Kontrak"
                    }else if(asb2 == "2") {
                        asb2 = "Bobot panen"
                    }else if(asb2 == "3" ){
                        asb2 = "Harga pasar"
                    }

                    if (asp1 == "1") {
                        asp1 = "Konsultasi"
                    }else if(asp1 == "2") {
                        asp1 = "Konsultasi,Manajemen"
                    }else if(asp1 == "3" ){
                        asp1 = "Konsultasi,Manajemen,Kesehatan"
                    }


                    if (asp2 == "1") {
                        asp2 = "Tidak"
                    }else if(asp2 == "2") {
                        asp2 = "Eksternal"
                    }else if(asp2 == "3" ){
                        asp2 = "Internal"
                    }

                    if(asp3 == "1"){
                        asp3 = "Fleksibel"
                    }else if (asp3 == "2") {
                        asp3 = "1 Minggu 2x"
                    }else if(asp3 == "3"){
                        asp3 = "5 Hari 1x"
                    }else if(asp3 == "4"){
                        asp3 = "3 Hari 1x"
                    }

                    tv_hargapakan.setText(ass1)
                    tv_hargadoc.setText(ass2)
                    tv_modal.setText(asb1)
                    tv_komisi.setText(asb2)
                    tv_pelayanan.setText(asp1)
                    tv_kesehatan.setText(asp2)
                    tv_check.setText(asp3)
                }

            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Terjadi kesalahan Koneksi Server", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                val hm = HashMap<String,String>()
                hm.put("idm",idm)
                return hm
            }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    override fun onStart() {
        super.onStart()
        showDataAspek(guserm.toString())
    }
}
