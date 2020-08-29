package com.example.suryaproserver.Mitra

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.TextUtils
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.suryaproserver.MediaHelper
import com.example.suryaproserver.R
import com.example.suryaproserver.SharedPref
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_bio_mitra.*
import kotlinx.android.synthetic.main.act_bio_mitra.tvbio_mthn
import kotlinx.android.synthetic.main.act_bioview_mitra.*
import java.text.SimpleDateFormat
import java.util.*

class biodata_mitra : AppCompatActivity(), View.OnClickListener {

    var tvm = SharedPref.getInstance(this).user
    var guserm = tvm.idusr
    var imStr = ""
    lateinit var mediaHelper: MediaHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_bio_mitra)

        btn_mimg1.setOnClickListener(this)
        btn_msubmit1.setOnClickListener(this)

        mediaHelper = MediaHelper(this)

        var  hm = intent.extras!!
        bio1 = hm.getString("NAMA_MITRA").toString()
        bio2 = hm.getString("ALAMAT_MITRA").toString()
        bio3 = hm.getString("THN_BERDIRI").toString()
        bio4 = hm.getString("CONTACT_MITRA").toString()
        bio5 = hm.getString("VENDOR_MITRA").toString()

        te_mnama.setText(bio1)
        te_malamat.setText(bio2)
        tvbio_mthn.setText(bio3)
        te_mnotlp.setText(bio4)
        te_mvendor.setText(bio5)

        //awal DatePicker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        btn_mcal.setOnClickListener{
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    tvbio_mthn.setText(""+mYear+"-"+mMonth+"-"+mDay).toString()
                }, year, month, day)
            dpd.show()
        }
        //Akhir Datepicker
        //Awal Timepicker
        val mPickTimeBtnbuka = findViewById<ImageButton>(R.id.btn_mbuka)
        val tvjmbuka     = findViewById<TextView>(R.id.tv_mbuka)

        mPickTimeBtnbuka.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                tvjmbuka.text = SimpleDateFormat("HH:mm").format(cal.time).toString()
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        val mPickTimeBtntutup = findViewById<ImageButton>(R.id.btn_mtutup)
        val tvjmtutup     = findViewById<TextView>(R.id.tv_mtutup)

        mPickTimeBtntutup.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                tvjmtutup.text = SimpleDateFormat("HH:mm").format(cal.time).toString()
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        //Akhir Timepicker
        te_mnotlp.addTextChangedListener( PhoneNumberFormattingTextWatcher("IDN"))
    }

    var bio1 = ""
    var bio2 = ""
    var bio3 = ""
    var bio4 = ""
    var bio5 = ""

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, biodataview_mitra::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_mimg1 ->{
                val intent = Intent()
                intent.setType("image/*")
                intent.setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(intent,mediaHelper.getRcGallery())
            }
            R.id.btn_msubmit1 -> {
                queryInsertUpdateDelete()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == mediaHelper.getRcGallery()){
                imStr = mediaHelper.getBitmapToString(data!!.data, btn_mimg1)
            }
        }
    }

    fun queryInsertUpdateDelete(){
    //validasi
        val namamitra = te_mnama.text.toString().trim { it <= ' ' }
        val alamat = te_malamat.text.toString().trim { it <= ' ' }
        val tahun = tvbio_mthn.text.toString().trim { it <= ' ' }
        val nohp = te_mnotlp.text.toString().trim { it <= ' ' }
        val jmbuka = tv_mbuka.text.toString().trim { it <= ' ' }
        val jmtutup = tv_mtutup.text.toString().trim { it <= ' ' }
        val vendor = te_mvendor.text.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(namamitra)) {
            te_mnama.error = "Tolong isi Nama Mitra"
            te_mnama.requestFocus()
            return
        }
        if (TextUtils.isEmpty(alamat)) {
            te_malamat.error = "Tolong isi Alamat Mitra"
            te_malamat.requestFocus()
            return
        }
        if (TextUtils.isEmpty(tahun)) {
            tvbio_mthn.error = "Tolong isi Tahun Berdiri"
            tvbio_mthn.requestFocus()
            return
        }
        if (TextUtils.isEmpty(nohp)) {
            te_mnotlp.error = "Tolong isi Nomer Telp Mitra"
            te_mnotlp.requestFocus()
            return
        }
        val cnohp = nohp.substring(0,3)
        if (cnohp != "+62") {
            te_mnotlp.error = "Format Nomor Telephone Salah"
            te_mnotlp.requestFocus()
            return
        }

        if (TextUtils.isEmpty(jmbuka)) {
            tv_mbuka.error = "Tolong isi Jam Buka"
            tv_mbuka.requestFocus()
            return
        }
        if (TextUtils.isEmpty(jmtutup)) {
            tv_mtutup.error = "Tolong isi Jam Tutup"
            tv_mtutup.requestFocus()
            return
        }
        if (TextUtils.isEmpty(vendor)) {
            te_mvendor.error = "Tolong isi Vendor"
            te_mvendor.requestFocus()
            return
        }


        val request = object : StringRequest(
            Method.POST, URLs.URL_BIOMITRA,
            Response.Listener { response ->
                Toast.makeText(this,"Operasi Berhasil!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, biodataview_mitra:: class.java)
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
                    Locale.getDefault()
                )
                    .format(Date())+",jpg"

                        hm.put("mode","update")
                        hm.put("ID_MITRA",guserm.toString())
                        hm.put("NAMA_MITRA",te_mnama.text.toString())
                        hm.put("ALAMAT_MITRA",te_malamat.text.toString())
                        hm.put("THN_BERDIRI",tvbio_mthn.text.toString())
                        hm.put("CONTACT_MITRA",te_mnotlp.text.toString())
                        hm.put("JMB_MITRA",tv_mbuka.text.toString())
                        hm.put("JMT_MITRA",tv_mtutup.text.toString())
                        hm.put("VENDOR_MITRA",te_mvendor.text.toString())
                        hm.put("image",imStr)
                        hm.put("file",nmFile)
                return hm
            }
        }
        val queue = Volley.newRequestQueue(this)
        queue.add(request)


    }
}