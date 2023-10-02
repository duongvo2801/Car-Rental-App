package com.example.carrentelrealtimeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.carrentelrealtimeapp.R;

public class DetailWeb extends AppCompatActivity {
    WebView webView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_web);

        webView = findViewById(R.id.webView);
        intent = getIntent();
        String link = intent.getStringExtra("openLink");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.theme_tele));
    }
}