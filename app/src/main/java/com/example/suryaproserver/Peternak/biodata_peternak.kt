package com.example.suryaproserver.Peternak

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.MediaHelper
import com.example.suryaproserver.R
import com.example.suryaproserver.SharedPref
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_bio_peternak.*
import java.util.*

class biodata_peternak : AppCompatActivity(), View.OnClickListener {
    var tvp = SharedPref.getInstance(this).user
    var guserp = tvp.idusr
    var imStr = ""
    lateinit var mediaHelper: MediaHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_bio_peternak)

        btn_pimg.setOnClickListener(this)
        btn_psubmit1.setOnClickListener(this)
        mediaHelper = MediaHelper(this)

        var  hm = intent.extras!!
        bio1 = hm.getString("NAMA_PEMILIK").toString()
        bio2 = hm.getString("NAMA_PETER").toString()
        bio3 = hm.getString("ALAMAT_PETER").toString()
        bio4 = hm.getString("THN_BERDIRI").toString()
        bio5 = hm.getString("NOHP_PETER").toString()

        te_pnama.setText(bio1)
        te_pmilik.setText(bio2)
        te_palamat.setText(bio3)
        te_thn.setText(bio4)
        te_pnotlp.setText(bio5)
//        val nohp = "+62 857-4452-1"
//        val cnohp = nohp.substring(0,3)
//        te_mnotlp.setText(cnohp)

        te_pnotlp.addTextChangedListener( PhoneNumberFormattingTextWatcher("IDN"))
    }

    var bio1 = ""
    var bio2 = ""
    var bio3 = ""
    var bio4 = ""
    var bio5 = ""

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, biodataview_peternak::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_pimg ->{
                val intent = Intent()
                intent.setType("image/*")
                intent.setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(intent,mediaHelper.getRcGallery())
            }
            R.id.btn_psubmit1 -> {
                queryInsertUpdateDelete()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == mediaHelper.getRcGallery()){
                imStr = mediaHelper.getBitmapToString(data!!.data, btn_pimg)
            }
        }
    }

    fun queryInsertUpdateDelete(){
        //validasi
        if (radio_plahan.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong pilih Status Lahan", Toast.LENGTH_LONG).show()
            return
        }
        if (radio_pmitra.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong pilih Status Mitra", Toast.LENGTH_LONG).show()
            return
        }
        val namapeter = te_pnama.text.toString().trim { it <= ' ' }
        val namapemilik = te_pmilik.text.toString().trim { it <= ' ' }
        val alamat = te_palamat.text.toString().trim { it <= ' ' }
        val tahun = te_thn.text.toString().trim { it <= ' ' }
        val nohp = te_pnotlp.text.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(namapeter)) {
            te_pnama.error = "Tolong isi Nama Peternakan"
            te_pnama.requestFocus()
            return
        }
        if (TextUtils.isEmpty(namapemilik)) {
            te_pmilik.error = "Tolong isi Nama Pemilik"
            te_pmilik.requestFocus()
            return
        }
        if (TextUtils.isEmpty(alamat)) {
            te_palamat.error = "Tolong isi Alamat Peternakan"
            te_palamat.requestFocus()
            return
        }
        if (TextUtils.isEmpty(tahun)) {
            te_thn.error = "Tolong isi Tahun Berdiri"
            te_thn.requestFocus()
            return
        }
        if (TextUtils.isEmpty(nohp)) {
            te_pnotlp.error = "Tolong isi Nomer Telp Peternakan"
            te_pnotlp.requestFocus()
            return
        }
        val cnohp = nohp.substring(0,3)
        if (cnohp != "+62") {
            te_pnotlp.error = "Format Nomor Telephone Salah"
            te_pnotlp.requestFocus()
            return
        }

        val request = object : StringRequest(
            Method.POST, URLs.URL_BIOPETER,
            Response.Listener { response ->

                Toast.makeText(this,"Operasi Berhasil!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, biodataview_peternak:: class.java)
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
                val nmFile = "DC"+ android.icu.text.SimpleDateFormat("yyyymmddHHmmss",
                    Locale.getDefault()).format(Date())+",jpg"
                val type1 = (findViewById<View>(radio_plahan.checkedRadioButtonId) as RadioButton).text.toString()
                val type = (findViewById<View>(radio_pmitra.checkedRadioButtonId) as RadioButton).text.toString()
//                when(mode){
//                    "update" ->{
                        hm.put("ID_PETER",guserp.toString())
                        hm.put("NAMA_PETER",te_pnama.text.toString())
                        hm.put("NAMA_PEMILIK",te_pmilik.text.toString())
                        hm.put("ALAMAT_PETER",te_palamat.text.toString())
                        hm.put("THN_BERDIRI",te_thn.text.toString())
                        hm.put("NOHP_PETER",te_pnotlp.text.toString())
                        hm.put("STATUS_KEMITRA",type)
                        hm.put("STATUS_LAHAN",type1)
                        hm.put("image",imStr)
                        hm.put("file",nmFile)
//                    }
//                }
                return hm
            }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)


    }

}