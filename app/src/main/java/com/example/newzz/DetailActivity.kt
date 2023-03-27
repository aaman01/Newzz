package com.example.newzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class DetailActivity : AppCompatActivity() {
    private lateinit var webView:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
         webView=findViewById(R.id.webView)
         var progressbar=findViewById<ProgressBar>(R.id.progressBar)
      val url=intent.getStringExtra("URL")  // key shoudl be same
        if (url!=null){
            webView.settings.javaScriptEnabled=true    //enable java scirpt in webpage if possible
            webView.webViewClient=object:WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressbar.visibility=View.GONE
                    webView.visibility=View.VISIBLE
                }
            }
            webView.loadUrl(url)
        }
    }
}