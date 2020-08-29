package com.example.suryaproserver.Peternak

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
import kotlinx.android.synthetic.main.act_daftarm_peternak.*
import org.json.JSONArray

class daftarm_peternak : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_daftarm_peternak)

        DaftarAdapter = adptdaftarm(daftarp)
        rv_adptdaftarm.layoutManager = LinearLayoutManager(this)
        rv_adptdaftarm.adapter = DaftarAdapter

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_peternak::class.java)
        startActivity(intent)
        finish()
    }

    lateinit var DaftarAdapter : adptdaftarm
    var daftarp = mutableListOf<HashMap<String,String>>()

    fun showDataMitra(){
        val request = object : StringRequest(
            Request.Method.POST, URLs.URL_DAFTARMITRA,
            Response.Listener{ response ->
                daftarp.clear()
                val jsonArray = JSONArray(response)

                for(x in 0 .. (jsonArray.length()-1)){
                    val jsonObject = jsonArray.getJSONObject(x)
                    var dt = HashMap<String,String>()

                    dt.put("NAMA_MITRA",jsonObject.getString("NAMA_MITRA"))
                    dt.put("VENDOR_MITRA",jsonObject.getString("VENDOR_MITRA"))
                    dt.put("ALAMAT_MITRA",jsonObject.getString("ALAMAT_MITRA"))
                    dt.put("FT_MITRA",jsonObject.getString("FT_MITRA"))

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
