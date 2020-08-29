package com.example.suryaproserver.Mitra

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.R
import com.example.suryaproserver.URLs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.act_detail_hasil_mitra.*
import org.json.JSONArray

class detail_hasil_mitra : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_detail_hasil_mitra)
        var hm = intent.extras!!
        idhm = hm.getString("id").toString()

        btn_call2.setOnClickListener(this)
        btn_call21.setOnClickListener(this)
        btn_call22.setOnClickListener(this)
    }

    var idhm = ""
    var kontak= ""

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_call2 -> {
                try {
                    val urel = "https://wa.me/$kontak"
                    val sendIntent = Intent().apply {
                        //                        action = Intent.ACTION_SEND
//                        putExtra(Intent.EXTRA_TEXT, "Hello Swapz")
//                        type = "text/plain"
//                        setPackage("com.whatsapp")
                        action = Intent.ACTION_VIEW
                        setData(Uri.parse(urel))
                    }
                    startActivity(sendIntent)
                } catch (e: Exception) {
                    e.printStackTrace()
                    val appPackageName = "com.whatsapp"
                    try {
                        startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName") )
                        )
                    } catch (e: android.content.ActivityNotFoundException) {
                        startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName"))
                        )
                    }
                }
            }
            R.id.btn_call21 ->{
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(kontak)))
                startActivity(intent)
            }
            R.id.btn_call22 ->{
                val uri = Uri.parse("smsto:$kontak")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.putExtra("Greetings~", "Hello i'm peternak...")
                startActivity(intent)
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
                    dt.put("NAMA_PETER",jsonObject.getString("NAMA_PETER"))
                    dt.put("NAMA_PEMILIK",jsonObject.getString("NAMA_PEMILIK"))
                    dt.put("ALAMAT_PETER",jsonObject.getString("ALAMAT_PETER"))
                    dt.put("THN_BERDIRI",jsonObject.getString("THN_BERDIRI"))
                    dt.put("NOHP_PETER",jsonObject.getString("NOHP_PETER"))
                    dt.put("STATUS_KEMITRA",jsonObject.getString("STATUS_KEMITRA"))
                    dt.put("STATUS_LAHAN",jsonObject.getString("STATUS_LAHAN"))
                    dt.put("FT_PETER",jsonObject.getString("FT_PETER"))
                    textView41.setText(dt.get("NAMA_PETER").toString())
                    textView449.setText(dt.get("NAMA_PEMILIK").toString())
                    textView44.setText(dt.get("ALAMAT_PETER").toString())
                    textView448.setText(dt.get("THN_BERDIRI"))
                    textView4488.setText(dt.get("NOHP_PETER"))
                    kontak = textView4488.text.toString()
                    textView44888.setText(dt.get("STATUS_KEMITRA").toString())
                    textView448888.setText(dt.get("STATUS_LAHAN").toString())
                    if (!dt.get("FT_PETER").equals(""))
                    Picasso.get().load(dt.get("FT_PETER")).into(imageView4)
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

    var ask1 = ""
    var ask2 = ""
    var ask3 = ""
    var asf1 = ""
    var asf2 = ""
    var asp1 = ""
    var asp2 = ""

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

                    textView449p.setText(ask1)
                    textView44p.setText(ask2)
                    textView448p.setText(ask3)
                    textView4488p.setText(asf1)
                    textView44888p.setText(asf2)
                    textView448888p.setText(asp1)
                    textView4488888p.setText(asp2)
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
        showDataBio(idhm)
        showDataAspek(idhm)
    }
}
