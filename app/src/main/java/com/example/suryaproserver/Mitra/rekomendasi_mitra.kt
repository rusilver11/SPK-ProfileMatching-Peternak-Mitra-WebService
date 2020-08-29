package com.example.suryaproserver.Mitra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.suryaproserver.R
import kotlinx.android.synthetic.main.act_rekomendasi_mitra.*

class rekomendasi_mitra : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_rekomendasi_mitra)
        btn_rekomendasi2.setOnClickListener(this)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, main_mitra::class.java)
        startActivity(intent)
        finish()
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_rekomendasi2 ->{
                posttargetnilai()
            }
        }
    }
    var type1 = ""
    var type2 = ""
    var type3 = ""
    var type4 = ""
    var type5= ""
    var type6 = ""
    var type7 = ""

    fun posttargetnilai(){
        //validasi
        if (rbm_rekkapas.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbm_rekjenis.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbm_rekjmlk.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbm_reksuhu.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbm_rektmptmkn.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbm_rekpengalaman.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
        if (rbm_rekpekerja.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong lengkapi kriteria", Toast.LENGTH_LONG).show()
            return
        }
         type1 = (findViewById<View>(rbm_rekkapas.checkedRadioButtonId) as RadioButton).text.toString()
         type2 = (findViewById<View>(rbm_rekjenis.checkedRadioButtonId) as RadioButton).text.toString()
         type3 = (findViewById<View>(rbm_rekjmlk.checkedRadioButtonId) as RadioButton).text.toString()
         type4 = (findViewById<View>(rbm_reksuhu.checkedRadioButtonId) as RadioButton).text.toString()
         type5 = (findViewById<View>(rbm_rektmptmkn.checkedRadioButtonId) as RadioButton).text.toString()
         type6 = (findViewById<View>(rbm_rekpengalaman.checkedRadioButtonId) as RadioButton).text.toString()
         type7 = (findViewById<View>(rbm_rekpekerja.checkedRadioButtonId) as RadioButton).text.toString()
        val hm = Bundle()
        hm.putString("KAPASITAS_KAPS",type1)
        hm.putString("JENIS_KAPS",type2)
        hm.putString("JML_KAPS",type3)
        hm.putString("FKP_SUHU",type4)
        hm.putString("FKP_TMPTMKN",type5)
        hm.putString("PENGALAMAN_PTRNK",type6)
        hm.putString("JMLTNG_PTRNK",type7)
        val intent = Intent(this, hasilrekom_mitra::class.java)
        intent.putExtras(hm)
        startActivity(intent)

    }

}
