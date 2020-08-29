package com.example.suryaproserver.Peternak

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
import kotlinx.android.synthetic.main.act_main_peternak.*

class main_peternak : AppCompatActivity(), View.OnClickListener {



    internal lateinit var cvPeternakan: CardView
    lateinit var cvRekomendasi: CardView
    lateinit var cvAspek: CardView
    lateinit var cvDaftar: CardView

    internal lateinit var signout: ImageButton
    internal lateinit var signoutText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main_peternak)



        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        idnamauserp.setText(SharedPref.getInstance(this).user.name.toString())

        cvPeternakan = findViewById(R.id.cvPeternakan)
        cvRekomendasi = findViewById(R.id.cvRekomendasi)
        cvAspek = findViewById(R.id.cvAspek)
        cvDaftar = findViewById(R.id.cvDaftar)

        signout = findViewById(R.id.btnSignoutpeter)
        signout.setOnClickListener(this)

        signoutText = findViewById(R.id.tvSignout)
        signoutText.setOnClickListener(this)

        cvPeternakan.setOnClickListener(this)
        cvRekomendasi.setOnClickListener(this)
        cvAspek.setOnClickListener(this)
        cvDaftar.setOnClickListener(this)

        if (SharedPref.getInstance(this).isLoggedIn) {
            if(SharedPref.getInstance(this).user.type != "Peternak"){
                finish()
                startActivity(Intent(this, act_login::class.java))
            }
        }
    }
    override fun onBackPressed() {
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.cvPeternakan ->{
                val intent = Intent(this, biodataview_peternak::class.java)
                startActivity(intent)
                finish()
            }
            R.id.cvRekomendasi->{
                val intent = Intent(this, rekomendasi_peternak::class.java)
                startActivity(intent)
                finish()
            }
            R.id.cvAspek->{
                val intent = Intent(this, aspek_peternak::class.java)
                startActivity(intent)
                finish()
            }
            R.id.cvDaftar->{
                val intent = Intent(this, daftarm_peternak::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnSignoutpeter->{
                SharedPref.getInstance(applicationContext).logout()

            }
            R.id.tvSignout->{
                SharedPref.getInstance(applicationContext).logout()

            }
        }

    }


}
