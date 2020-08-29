package com.example.suryaproserver.Peternak

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.act_bioview_peternak.*
import org.json.JSONArray

class biodataview_peternak : AppCompatActivity(), View.OnClickListener {

    var gets = SharedPref.getInstance(this).user
    var puser = gets.idusr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_bioview_peternak)
        button3.setOnClickListener(this)

    }

    var bio1 = ""
    var bio2 = ""
    var bio3 = ""
    var bio4 = ""
    var bio5 = ""

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_peternak::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View) {
        when (v?.id) {
            R.id.button3 ->{
               posttargetnilai()
            }
        }
    }

    fun showDataBio(idp : String){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_BIOPETERVIEW,
            Response.Listener{ response ->
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()
                    var dt1 = HashMap<String,String>()
                    dt.put("NAMA_PETER",jsonObject.getString("NAMA_PETER"))
                    dt.put("NAMA_PEMILIK",jsonObject.getString("NAMA_PEMILIK"))
                    dt.put("ALAMAT_PETER",jsonObject.getString("ALAMAT_PETER"))
                    dt.put("THN_BERDIRI",jsonObject.getString("THN_BERDIRI"))
                    dt.put("NOHP_PETER",jsonObject.getString("NOHP_PETER"))
                    dt.put("STATUS_KEMITRA",jsonObject.getString("STATUS_KEMITRA"))
                    dt.put("STATUS_LAHAN",jsonObject.getString("STATUS_LAHAN"))
                    dt.put("FT_PETER",jsonObject.getString("FT_PETER"))
                    if (!dt.get("FT_PETER").equals(""))
                        Picasso.get().load(dt.get("FT_PETER")).into(img_pimg)
                    val bo1 = dt.get("NAMA_PEMILIK").toString()
                    val bo2 = dt.get("NAMA_PETER").toString()
                    val bo3 = dt.get("ALAMAT_PETER").toString()
                    val bo4 = dt.get("THN_BERDIRI")
                    val bo5 = dt.get("NOHP_PETER")
                    val bo6 = dt.get("STATUS_KEMITRA").toString()
                    val bo7 = dt.get("STATUS_LAHAN").toString()
                    if(bo1 == "null"){ tvbiov_nmp.setText("-") }
                    else{tvbiov_nmp.setText(bo1)}
                    if(bo2 == "null"){  tvbiov_nmpp.setText("-") }
                    else {tvbiov_nmpp.setText(bo2)}
                    if(bo3 == "null"){  tvbiov_almtp.setText("-") }
                    else {tvbiov_almtp.setText(bo3)}
                    if(bo4 == "null"){  tvbiov_thnp.setText("-") }
                    else{tvbiov_thnp.setText(bo4)}
                    if(bo5 == "null"){  tvbiov_nohpp.setText("-")}
                    else {tvbiov_nohpp.setText(bo5)}
                    if(bo6 == "null"){  tvbiov_statk.setText("-")}
                    else {tvbiov_statk.setText(bo6)}
                    if(bo7 == "null"){  tvbiov_statl.setText("-")}
                    else {tvbiov_statl.setText(bo7)}

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

    fun posttargetnilai(){

         bio1 = tvbiov_nmp.text.toString()
         bio2 = tvbiov_nmpp.text.toString()
         bio3 = tvbiov_almtp.text.toString()
         bio4 = tvbiov_thnp.text.toString()
         bio5 = tvbiov_nohpp.text.toString()

        if(bio1 == "-"){ bio1 = "" }
        if(bio2 == "-"){ bio2 = "" }
        if(bio3 == "-"){ bio3 = "" }
        if(bio4 == "-"){ bio4 = "" }
        if(bio5 == "-"){ bio5 = "" }

        val hm = Bundle()
        hm.putString("NAMA_PETER",bio1)
        hm.putString("NAMA_PEMILIK",bio2)
        hm.putString("ALAMAT_PETER",bio3)
        hm.putString("THN_BERDIRI",bio4)
        hm.putString("NOHP_PETER",bio5)

        val intent = Intent(this, biodata_peternak::class.java)
        intent.putExtras(hm)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        showDataBio(puser.toString())
    }
}