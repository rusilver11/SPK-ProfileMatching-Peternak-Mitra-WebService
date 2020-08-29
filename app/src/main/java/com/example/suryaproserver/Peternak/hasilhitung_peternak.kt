package com.example.suryaproserver.Peternak

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.suryaproserver.R
import com.example.suryaproserver.URLs
import kotlinx.android.synthetic.main.act_hasilhitung_peternak.*

class hasilhitung_peternak : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_hasilhitung_peternak)
        webview.webViewClient =
            MyWebViewClient(this)
        webview.loadUrl(URLs.URL_BOBOTASPEK)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }

    }

}
