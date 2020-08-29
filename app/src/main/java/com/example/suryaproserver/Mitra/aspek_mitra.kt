package com.example.suryaproserver.Mitra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.R
import com.example.suryaproserver.SharedPref
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_aspek_mitra.*
import java.util.HashMap

class aspek_mitra : AppCompatActivity(),View.OnClickListener {

    var tvm = SharedPref.getInstance(this).user
    var guserm = tvm.idusr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_aspek_mitra)

        btn_maspeksubmit.setOnClickListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, aspekview_mitra::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_maspeksubmit -> {
                queryInsertUpdateDelete()
            }

        }
    }

    fun queryInsertUpdateDelete(){
        //validasi
        if (rbsapronak.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbsapronak1.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbpembayaran.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbpembayaran1.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbpelayanan.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbpelayanan1.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbpelayanan2.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }

        val request = object : StringRequest(
            Method.POST, URLs.URL_ASPEKMITRA,
            Response.Listener { response ->

                //val jsonObject = JSONObject(response)
//                val error = jsonObject.getString("kode")
//                if (error.equals("000")){
                Toast.makeText(this,"Operasi Berhasil!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, aspekview_mitra:: class.java)
                startActivity(intent)
                finish()
//                }else{
//                    Toast.makeText(this,"Operasi GAGAL!", Toast.LENGTH_LONG).show()
//
//            }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Tidak Dapat Terhubung Ke Server!", Toast.LENGTH_LONG)
                    .show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val hm = HashMap<String,String>()
                val type1 = (findViewById<View>(rbsapronak.checkedRadioButtonId) as RadioButton).text.toString()
                val type2 = (findViewById<View>(rbsapronak1.checkedRadioButtonId) as RadioButton).text.toString()
                val type3 = (findViewById<View>(rbpembayaran.checkedRadioButtonId) as RadioButton).text.toString()
                val type4 = (findViewById<View>(rbpembayaran1.checkedRadioButtonId) as RadioButton).text.toString()
                val type5 = (findViewById<View>(rbpelayanan.checkedRadioButtonId) as RadioButton).text.toString()
                val type6 = (findViewById<View>(rbpelayanan1.checkedRadioButtonId) as RadioButton).text.toString()
                val type7 = (findViewById<View>(rbpelayanan2.checkedRadioButtonId) as RadioButton).text.toString()

                        hm.put("mode","update")
                        hm.put("ID_MITRA",guserm.toString())
                        hm.put("PAKAN_SAP",type1)
                        hm.put("DOC_SAP",type2)
                        hm.put("JML_MODAL",type3)
                        hm.put("KOMISI_MODAL",type4)
                        hm.put("PPL_PN",type5)
                        hm.put("KESEHATAN_PN",type6)
                        hm.put("CHECK_PN",type7)
                return hm
            }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)


    }
}
 