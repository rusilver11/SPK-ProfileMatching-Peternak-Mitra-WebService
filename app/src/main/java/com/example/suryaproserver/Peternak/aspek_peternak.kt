package com.example.suryaproserver.Peternak

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
import kotlinx.android.synthetic.main.act_aspek_peternak.*
import org.json.JSONArray

class aspek_peternak : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_aspek_peternak)

        btnEdit.setOnClickListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_peternak::class.java)
        startActivity(intent)
        finish()
    }

    var gets = SharedPref.getInstance(this).user
    var puser = gets.idusr
    var ask1 = ""
    var ask2 = ""
    var ask3 = ""
    var asf1 = ""
    var asf2 = ""
    var asp1 = ""
    var asp2 = ""


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnEdit->{
                val intent = Intent(this, aspekedit_peternak::class.java)
                startActivity(intent)
                finish()

            }
        }
    }

    fun showDataAspek(idp : String){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_ASPEKPETERVIEW,
            Response.Listener{ response ->
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()
                    dt.put("kapasitas_kaps",jsonObject.getString("kapasitas_kaps"))
                    dt.put("jenis_kaps",jsonObject.getString("jenis_kaps"))
                    dt.put("jml_kaps",jsonObject.getString("jml_kaps"))
                    dt.put("fkp_suhu",jsonObject.getString("fkp_suhu"))
                    dt.put("fkp_tmptmkn",jsonObject.getString("fkp_tmptmkn"))
                    dt.put("pengalaman_ptrnk",jsonObject.getString("pengalaman_ptrnk"))
                    dt.put("jmltng_ptrnk",jsonObject.getString("jmltng_ptrnk"))

                    ask1 = dt.get("kapasitas_kaps").toString()
                    ask2 = dt.get("jenis_kaps").toString()
                    ask3 = dt.get("jml_kaps").toString()
                    asf1 = dt.get("fkp_suhu").toString()
                    asf2 = dt.get("fkp_tmptmkn").toString()
                    asp1 = dt.get("pengalaman_ptrnk").toString()
                    asp2 = dt.get("jmltng_ptrnk").toString()

                    if (ask1 == "1") {
                            ask1 = "2000+ ekor"
                    }else if(ask1 == "2") {
                            ask1 = "3000+ ekor"
                    }else if(ask1 == "3" ){
                            ask1 = "5000+ ekor"
                    }else if(ask1 == "4" ){
                            ask1 = "7000+ ekor"
                    }else if(ask1 == "5"){
                            ask1 = "10000+ ekor"
                    }

                    if (ask2 == "1") {
                        ask2 = "Open"
                    }else if(ask2 == "2") {
                        ask2 = "Close"
                    }

                    if (ask3 == "1") {
                        ask3 = "1 Single"
                    }else if(ask3 == "2") {
                        ask3 = "1 Tingakat"
                    }else if(ask3 == "3" ){
                        ask3 = "2 Single"
                    }else if(ask3 == "4"){
                        ask3 = "2 Tingkat"
                    }

                    if (asf1 == "1") {
                        asf1 = "Pemanas Kompor Canopy, Kipas, Tirai"
                    }else if(asf1 == "2") {
                        asf1 = "Gas Brooder, Kipas, Tirai"
                    }else if(asf1 == "3" ){
                        asf1 = "Brooder Elektrik, Blower Fan, Cooling Pad"
                    }

                    if (asf2 == "1") {
                        asf2 = "Tempat Minum, Tempat Ransum Ayam"
                    }else if(asf2 == "2") {
                        asf2 = "Tempat Minum, Tempat Ransum+Baby Chick"
                    }else if(asf2 == "3" ){
                        asf2 = "Tempat Minum Otomatis, Tempat Ransum+Baby Chick"
                    }else if(asf2 == "4"){
                        asf2 = "Niple Drinken, Super Feeder"
                    }

                    if (asp1 == "1") {
                        asp1 = "Baru"
                    }else if(asp1 == "2") {
                        asp1 = "Baru ex"
                    }else if(asp1 == "3" ){
                       asp1 = "6 Bulan+"
                    }else if(asp1 == "4"){
                        asp1 = "1 Tahun+"
                    }

                    if(asp2 == "1"){
                        asp2 = "1-2 orang"
                    }else if (asp2 == "2") {
                        asp2 = "3-5 orang"
                    }else if(asp2 == "3"){
                        asp2 = "6+ orang"
                    }

                    tv_kapskandang.setText(ask1)
                    tv_jeniskandang.setText(ask2)
                    tv_jmlkandang.setText(ask3)
                    tv_suhukandang.setText(asf1)
                    tv_tmptkandang.setText(asf2)
                    tv_pengalaman.setText(asp1)
                    tv_jmlpekerja.setText(asp2)

                }

            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Terjadi kesalahan Koneksi Server", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                val hm = HashMap<String,String>()
                hm.put("idp",idp)
                return hm
            }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    override fun onStart() {
        super.onStart()
        showDataAspek(puser.toString())
    }
}
