package com.example.suryaproserver.Mitra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.R
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_daftarp_mitra.*
import org.json.JSONArray

class daftarp_mitra : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_daftarp_mitra)
        DaftarAdapter = adptdaftarp(daftarp)

        rv_adptdaftarp.layoutManager = LinearLayoutManager(this)
        rv_adptdaftarp.adapter = DaftarAdapter

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_mitra::class.java)
        startActivity(intent)
        finish()
    }

    lateinit var DaftarAdapter : adptdaftarp
    var daftarp = mutableListOf<HashMap<String,String>>()

    fun showDataMitra(){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_DAFTARPETERNAK,
            Response.Listener{ response ->
                daftarp.clear()
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()

                    dt.put("NAMA_PETER",jsonObject.getString("NAMA_PETER"))
                    dt.put("NAMA_PEMILIK",jsonObject.getString("NAMA_PEMILIK"))
                    dt.put("ALAMAT_PETER",jsonObject.getString("ALAMAT_PETER"))
                    dt.put("FT_PETER",jsonObject.getString("FT_PETER"))

                    daftarp.add(dt)
                }
                DaftarAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Terjadi kesalahan Koneksi Server", Toast.LENGTH_LONG).show()
            }){
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }

    override fun onStart() {
        super.onStart()
        showDataMitra()
    }
}
