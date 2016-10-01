package com.example.mavin.certamen2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main3Activity extends AppCompatActivity {

    private WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        webView1 = (WebView) findViewById(R.id.main_webview);

        Bundle bundle = getIntent().getExtras();
        webView1.loadUrl(bundle.getString("direccion"));

    }
}
