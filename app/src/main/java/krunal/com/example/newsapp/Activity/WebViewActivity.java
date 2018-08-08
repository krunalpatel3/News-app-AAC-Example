package krunal.com.example.newsapp.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import krunal.com.example.newsapp.NetworkApi.Utility;
import krunal.com.example.newsapp.R;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    private String getUrlFromFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = findViewById(R.id.webView);

        getSupportActionBar().setTitle("Web View");

        getUrlFromFragment = getIntent().getStringExtra(Utility.NewsUrl);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        if (!getUrlFromFragment.matches("")){
            mWebView.loadUrl(getUrlFromFragment);
        }

    }
}
