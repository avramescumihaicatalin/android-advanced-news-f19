package ro.atelieruldigital.news.web_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.recycler_view.CustomHorizontalAdapter;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
        enableWebView();
    }

    private void initView() {
        mWebView = findViewById(R.id.web_view);
    }


    private void enableWebView() {
        Bundle bundle = getIntent().getExtras();
        String URL = bundle.getString(CustomHorizontalAdapter.WEB_VIEW_URL);
        if (URL != null) {
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.loadUrl(URL);
        }
    }

}
