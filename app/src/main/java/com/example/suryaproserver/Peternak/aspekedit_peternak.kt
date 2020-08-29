package com.example.suryaproserver.Peternak

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.R
import com.example.suryaproserver.SharedPref
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_aspekedit_peternak.*
import java.util.*

class aspekedit_peternak: AppCompatActivity(), View.OnClickListener {

    var tvp = SharedPref.getInstance(this).user
    var guserp = tvp.idusr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_aspekedit_peternak)

        btn_submitaspek.setOnClickListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, aspek_peternak::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_submitaspek -> {
                queryInsertUpdateDelete()

            }

        }
    }

    fun queryInsertUpdateDelete(){
        //validasi
        if (rb_kandang.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rb_kandang2.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rb_kandang3.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rb_fasilitas1.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rb_fasilitas2.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rb_peter1.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rb_peter2.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }

        val request = object : StringRequest(
            Method.POST, URLs.URL_ASPEKPETER,
            Response.Listener { response ->

                Toast.makeText(this,"Operasi Berhasil!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, aspek_peternak:: class.java)
                startActivity(intent)
                finish()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Tidak Dapat Terhubung Ke Server!", Toast.LENGTH_LONG)
                    .show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val hm = HashMap<String,String>()

                val type1 = (findViewById<View>(rb_kandang.checkedRadioButtonId) as RadioButton).text.toString()
                val type2 = (findViewById<View>(rb_kandang2.checkedRadioButtonId) as RadioButton).text.toString()
                val type3 = (findViewById<View>(rb_kandang3.checkedRadioButtonId) as RadioButton).text.toString()
                val type4 = (findViewById<View>(rb_fasilitas1.checkedRadioButtonId) as RadioButton).text.toString()
                val type5 = (findViewById<View>(rb_fasilitas2.checkedRadioButtonId) as RadioButton).text.toString()
                val type6 = (findViewById<View>(rb_peter1.checkedRadioButtonId) as RadioButton).text.toString()
                val type7 = (findViewById<View>(rb_peter2.checkedRadioButtonId) as RadioButton).text.toString()

                        hm.put("mode","update")
                        hm.put("ID_PETER",guserp.toString())
                        hm.put("KAPASITAS_KAPS",type1)
                        hm.put("JENIS_KAPS",type2)
                        hm.put("JML_KAPS",type3)
                        hm.put("FKP_SUHU",type4)
                        hm.put("FKP_TMPTMKN",type5)
                        hm.put("PENGALAMAN_PTRNK",type6)
                        hm.put("JMLTNG_PTRNK",type7)

                return hm
            }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)


    }
}