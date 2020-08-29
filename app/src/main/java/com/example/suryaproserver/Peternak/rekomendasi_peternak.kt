package com.example.suryaproserver.Peternak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.suryaproserver.R
import kotlinx.android.synthetic.main.act_rekomendasi_peternak.*

class rekomendasi_peternak : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_rekomendasi_peternak)

        btn_rekomendasi.setOnClickListener(this)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_peternak::class.java)
        startActivity(intent)
        finish()
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_rekomendasi ->{
                posttargetnilai()
            }
        }

    }

    fun posttargetnilai(){
        //validasi
        if (rbp_rekpakan.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbp_rekdoc.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbp_rekmodal.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbp_rekkomisi.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbp_rekpelayanan.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbp_rekkesehatan.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbp_rekwaktu.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
                val type1 = (findViewById<View>(rbp_rekpakan.checkedRadioButtonId) as RadioButton).text.toString()
                val type2 = (findViewById<View>(rbp_rekdoc.checkedRadioButtonId) as RadioButton).text.toString()
                val type3 = (findViewById<View>(rbp_rekmodal.checkedRadioButtonId) as RadioButton).text.toString()
                val type4 = (findViewById<View>(rbp_rekkomisi.checkedRadioButtonId) as RadioButton).text.toString()
                val type5 = (findViewById<View>(rbp_rekpelayanan.checkedRadioButtonId) as RadioButton).text.toString()
                val type6 = (findViewById<View>(rbp_rekkesehatan.checkedRadioButtonId) as RadioButton).text.toString()
                val type7 = (findViewById<View>(rbp_rekwaktu.checkedRadioButtonId) as RadioButton).text.toString()
        val hm = Bundle()
                hm.putString("PAKAN_SAP",type1)
                hm.putString("DOC_SAP",type2)
                hm.putString("JML_MODAL",type3)
                hm.putString("KOMISI_MODAL",type4)
                hm.putString("PPL_PN",type5)
                hm.putString("KESEHATAN_PN",type6)
                hm.putString("CHECK_PN",type7)
        val intent = Intent(this, hasilrekom_peternak::class.java)
        intent.putExtras(hm)
        startActivity(intent)

    }

}
