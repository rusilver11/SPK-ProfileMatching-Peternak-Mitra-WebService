package com.example.suryaproserver.Mitra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.R
import com.example.suryaproserver.SharedPref
import com.example.suryaproserver.URLs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_bioview_mitra.*
import org.json.JSONArray

class biodataview_mitra: AppCompatActivity(), View.OnClickListener {

    var gets = SharedPref.getInstance(this).user
    var muser = gets.idusr
    lateinit var btn_medit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_bioview_mitra)

        btn_medit = findViewById(R.id.btn_medit)
        btn_medit.setOnClickListener(this)
    }

    var bio1 = ""
    var bio2 = ""
    var bio3 = ""
    var bio4 = ""
    var bio5 = ""

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_mitra::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View) {
        when (v?.id) {
            R.id.btn_medit ->{
            posttargetnilai()
            }
        }
    }

    fun showDataBio(idm : String){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_BIOMITRAVIEW,
            Response.Listener{ response ->
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)

                    var dt = HashMap<String,String>()
                    dt.put("NAMA_MITRA",jsonObject.getString("NAMA_MITRA"))
                    dt.put("ALAMAT_MITRA",jsonObject.getString("ALAMAT_MITRA"))
                    dt.put("THN_BERDIRI",jsonObject.getString("THN_BERDIRI"))
                    dt.put("CONTACT_MITRA",jsonObject.getString("CONTACT_MITRA"))
                    dt.put("JMB_MITRA",jsonObject.getString("JMB_MITRA"))
                    dt.put("JMT_MITRA",jsonObject.getString("JMT_MITRA"))
                    dt.put("VENDOR_MITRA",jsonObject.getString("VENDOR_MITRA"))
                    dt.put("FT_MITRA",jsonObject.getString("FT_MITRA"))
                    val bo1 = dt.get("NAMA_MITRA").toString()
                    val bo2 = dt.get("ALAMAT_MITRA").toString()
                    val bo3 = dt.get("THN_BERDIRI").toString()
                    val bo4 = dt.get("CONTACT_MITRA")
                    val bo5 = dt.get("JMB_MITRA").toString()+"-"+dt.get("JMT_MITRA").toString()
                    val bo6 = dt.get("VENDOR_MITRA").toString()
                    if(bo1 == "null"){ tvbio_mnama.setText("TESTCOK") }
                    else{tvbio_mnama.setText(bo1)}
                    if(bo2 == "null"){  tvbio_malamat.setText("-") }
                    else {tvbio_malamat.setText(bo2)}
                    if(bo3 == "null"){  tvbio_mthn.setText("-") }
                    else {tvbio_mthn.setText(bo3)}
                    if(bo4 == "null"){  tvbio_mnotlp.setText("-") }
                    else{tvbio_mnotlp.setText(bo4)}
                    if(bo5 == "null-null"){  tvbio_mjmkerja.setText("-")}
                    else {tvbio_mjmkerja.setText(bo5)}
                    if(bo6 == "null"){  tvbio_mvendor.setText("-")}
                    else {tvbio_mvendor.setText(bo6)}
                    if (!dt.get("FT_MITRA").equals(""))
                    Picasso.get().load(dt.get("FT_MITRA")).into(img_mimg)
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

    fun posttargetnilai(){
        bio1 = tvbio_mnama.text.toString()
        bio2 = tvbio_malamat.text.toString()
        bio3 = tvbio_mthn.text.toString()
        bio4 = tvbio_mnotlp.text.toString()
        bio5 = tvbio_mvendor.text.toString()

        if(bio1 == "-"){ bio1 = "" }
        if(bio2 == "-"){ bio2 = "" }
        if(bio3 == "-"){ bio3 = "" }
        if(bio4 == "-"){ bio4 = "" }
        if(bio5 == "-"){ bio5 = "" }

        val hm = Bundle()
        hm.putString("NAMA_MITRA",bio1)
        hm.putString("ALAMAT_MITRA",bio2)
        hm.putString("THN_BERDIRI",bio3)
        hm.putString("CONTACT_MITRA",bio4)
        hm.putString("VENDOR_MITRA",bio5)

        val intent = Intent(this, biodata_mitra::class.java)
        intent.putExtras(hm)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        showDataBio(muser.toString())
    }
}