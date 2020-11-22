package com.unip.pimzeh.acitivity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.unip.pimzeh.R;

public class WebActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        inicializarComponentes();

        webView.loadUrl("https://www.google.com.br/");

    }

    private void inicializarComponentes() {
        webView = findViewById(R.id.webView);
    }
}