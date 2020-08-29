package com.example.suryaproserver

import android.content.Context
import android.content.Intent

class SharedPref private constructor(context: Context){

    //this method will checker whether user is already logged in or not
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString(KEY_USERNAME, null) != null
        }

    //this method will give the logged in user
    val user: User
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(
                SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences!!.getInt(
                    KEY_ID,
                    -1
                ),
                sharedPreferences.getString(
                    KEY_IDUSER,
                    null
                ),
                sharedPreferences.getString(
                    KEY_USERNAME,
                    null
                ),
                sharedPreferences.getString(
                    KEY_EMAIL,
                    null
                ),
                sharedPreferences.getString(
                    KEY_TYPE,
                    null
                )
            )
        }

    init {
        ctx = context
    }

    //this method will store the user data in shared preferences
    fun userLogin(user: User) {
        val sharedPreferences = ctx?.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt(KEY_ID, user.id)
        editor?.putString(KEY_IDUSER, user.idusr)
        editor?.putString(KEY_USERNAME, user.name)
        editor?.putString(KEY_EMAIL, user.email)
        editor?.putString(KEY_TYPE, user.type)
        editor?.apply()
    }
    //this method will logout the user
    fun logout() {
        val sharedPreferences = ctx?.getSharedPreferences(
            SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
        ctx?.startActivity(Intent(
            ctx,
            act_login::class.java))
    }

    companion object {

        private val SHARED_PREF_NAME = "volleyregisterlogin"
        private val KEY_IDUSER = "keyiduser"
        private val KEY_USERNAME = "keyusername"
        private val KEY_EMAIL = "keyemail"
        private val KEY_TYPE = "keytype"
        private val KEY_ID = "keyid"
        private var mInstance: SharedPref? = null
        private var ctx: Context? = null
        @Synchronized
        fun getInstance(context: Context): SharedPref {
            if (mInstance == null) {
                mInstance =
                    SharedPref(context)
            }
            return mInstance as SharedPref
        }
    }
}