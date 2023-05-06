package com.example.myapplication.ui.chart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class ChartFragment : Fragment() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
        webView = view.findViewById(R.id.webview)
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.loadUrl("file:///android_asset/test.html")
        return view
    }
}
