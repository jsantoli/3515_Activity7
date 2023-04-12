package com.example.browser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

private lateinit var webView: WebView
private lateinit var enterButton: ImageButton
private lateinit var bkButton: ImageButton
private lateinit var fwdButton: ImageButton
private lateinit var urlEditText: EditText

class fragment_page_viewer : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_viewer, container, false).apply(){
            webView = findViewById(R.id.page_viewer)
            enterButton = findViewById(R.id.enterButton)
            bkButton = findViewById(R.id.back)
            fwdButton = findViewById(R.id.forward)
            urlEditText = findViewById(R.id.enterUrl)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                url?.run{
                    urlEditText.setText(this)
                }
            }
        }
        savedInstanceState?.run{
            webView.restoreState(this)
        }

        enterButton.setOnClickListener{
            urlEditText.setText(cleanUrl(urlEditText.text.toString()))
            webView.loadUrl(urlEditText.text.toString())
        }
        bkButton.setOnClickListener{
            webView.goBack()
        }
        fwdButton.setOnClickListener{
            webView.goForward()
        }
    }
    private fun cleanUrl(url: String): String{
        return if (url.startsWith("http"))
            url
        else
            "https://$url"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        webView.saveState(outState)
    }
}