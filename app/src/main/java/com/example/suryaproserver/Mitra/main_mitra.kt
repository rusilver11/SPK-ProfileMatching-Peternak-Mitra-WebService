package com.example.suryaproserver.Mitra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.suryaproserver.R
import com.example.suryaproserver.SharedPref
import com.example.suryaproserver.act_login
import kotlinx.android.synthetic.main.act_main_mitra.*

class main_mitra : AppCompatActivity(), View.OnClickListener {

    lateinit var btn_mbiodata: CardView
    lateinit var btn_maspek: CardView
    lateinit var btn_mrekom: CardView
    lateinit var btn_mdaftar: CardView

    lateinit var  signout: ImageButton
    lateinit var  signoutText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main_mitra)

        idnamauserm.setText(SharedPref.getInstance(this).user.name.toString())

    if (SharedPref.getInstance(this).isLoggedIn) {
        if(SharedPref.getInstance(this).user.type != "Mitra"){
            finish()
            startActivity(Intent(this, act_login::class.java))
        }
    }
        btn_mbiodata = findViewById(R.id.btn_mbiodata)
        btn_maspek = findViewById(R.id.btn_maspek)
        btn_mrekom = findViewById(R.id.btn_mrekommitra)
        btn_mdaftar = findViewById(R.id.btn_mdaftar)
        btn_mbiodata.setOnClickListener(this)
        btn_maspek.setOnClickListener(this)
        btn_mrekom.setOnClickListener(this)
        btn_mdaftar.setOnClickListener(this)

        signout = findViewById(R.id.btnSignout2)
        signout.setOnClickListener(this)
        signoutText= findViewById(R.id.tvSignout2)
        signoutText.setOnClickListener(this)
    }
    override fun onBackPressed() {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_mbiodata ->{
                val intent = Intent(this, biodataview_mitra::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btn_maspek ->{
                val intent = Intent(this, aspekview_mitra::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btn_mrekommitra ->{
                val intent = Intent(this, rekomendasi_mitra::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btn_mdaftar->{
                val intent = Intent(this, daftarp_mitra::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnSignout2->{
                SharedPref.getInstance(applicationContext).logout()

            }
            R.id.tvSignout2->{
                SharedPref.getInstance(applicationContext).logout()

            }
        }
    }
}
