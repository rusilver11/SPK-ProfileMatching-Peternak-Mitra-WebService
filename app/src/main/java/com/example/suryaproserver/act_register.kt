package com.example.suryaproserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.suryaproserver.Peternak.main_peternak
import kotlinx.android.synthetic.main.activity_act_register.*
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class act_register : AppCompatActivity() {
    internal lateinit var editTextUsername: EditText
    internal lateinit var editTextEmail: EditText
    internal lateinit var editTextPassword: EditText
    internal lateinit var radioGroupType: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_register)

        //if the user is already logged in we will directly start the MainActivity_Mitra (profile) activity
        if (SharedPref.getInstance(this).isLoggedIn) {
            if(SharedPref.getInstance(this).user.type == "Peternak") {
                finish()
                startActivity(Intent(this, main_peternak::class.java))
            }else if(SharedPref.getInstance(this).user.type == "Mitra") {
                finish()
                startActivity(Intent(this, main_peternak::class.java))
            }else {
                finish()
                startActivity(Intent(this, act_login::class.java))
            }
        }

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        radioGroupType = findViewById(R.id.radioType)

        buttonRegister.setOnClickListener(View.OnClickListener {
            //if user pressed on button register
            //here we will register the user to server
            registerUser()
        })

        textViewLogin.setOnClickListener(View.OnClickListener {
            finish()
            startActivity(Intent(this@act_register, act_login::class.java))
        })
    }
    override fun onBackPressed() {
        finish()
    }
    private fun registerUser() {
        // validations checkbox
        if (radioType.getCheckedRadioButtonId() == -1){
            Toast.makeText(applicationContext, "Tolong pilih Tipe Pengguna", Toast.LENGTH_LONG).show()
            return
        }
        val username = editTextUsername.text.toString().trim { it <= ' ' }
        val email = editTextEmail.text.toString().trim { it <= ' ' }
        val password = editTextPassword.text.toString().trim { it <= ' ' }
        val type = (findViewById<View>(radioType.checkedRadioButtonId) as RadioButton).text.toString()

        //first we will do the validations
        if (TextUtils.isEmpty(username)) {
            editTextUsername.error = "Please enter username"
            editTextUsername.requestFocus()
            return
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.error = "Please enter your email"
            editTextEmail.requestFocus()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Enter a valid email"
            editTextEmail.requestFocus()
            return
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.error = "Enter a password"
            editTextPassword.requestFocus()
            return
        }

        val stringRequest = object : StringRequest(Request.Method.POST,
            URLs.URL_REGISTER,
            Response.Listener { response ->
                finish()
                startActivity(Intent(applicationContext, act_login::class.java))
                try {
                    //converting response to json object
                    val obj = JSONObject(response)
                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_SHORT).show()

                        //getting the user from the response
                        val userJson = obj.getJSONObject("user")

                        //creating a new user object
                        val user = User(
                            userJson.getInt("id"),
                            userJson.getString("idusr"),
                            userJson.getString("username"),
                            userJson.getString("email"),
                            userJson.getString("type")
                        )
                        //storing the user in shared preferences
                        SharedPref.getInstance(
                            applicationContext
                        ).userLogin(user)
                        //starting the Login activity
                        finish()
                        startActivity(Intent(applicationContext, act_login::class.java))
                    } else {
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["email"] = email
                params["password"] = password
                params["type"] = type
                return params
            }
        }

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)

    }
}
