package com.github.marhary.saveurlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.marhary.saveurlife.auth.VkAuthorizer;

public class AuthActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getSupportActionBar().hide();

        webView = ((WebView) findViewById(R.id.web_auth));
        navigate();
    }

    private void navigate() {
        final VkAuthorizer vkAuthorizer = new VkAuthorizer();
        webView.loadUrl(vkAuthorizer.getUrl(5710455, "offline"));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                String url1 = url;
                if (url.contains("access_token")){
                    int id=vkAuthorizer.parseUrl(url);
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
