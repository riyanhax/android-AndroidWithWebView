package com.pedroapps.androidwithwebview.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pedroapps.androidwithwebview.R;

public class MainActivity extends AppCompatActivity {

    ViewHolder viewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewHolder.webView = (WebView) findViewById(R.id.webView);
        this.viewHolder.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.viewHolder.layoutProgress = (LinearLayout) findViewById(R.id.layoutProgress);

        // Configurações da webView
        this.viewHolder.webView.setVisibility(View.GONE);
        WebSettings settings = this.viewHolder.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        this.viewHolder.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                viewHolder.webView.setVisibility(View.VISIBLE);
                viewHolder.layoutProgress.setVisibility(View.GONE);
                viewHolder.progressBar.setIndeterminate(false);
                super.onPageFinished(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                viewHolder.layoutProgress.setVisibility(View.VISIBLE);
                viewHolder.progressBar.setIndeterminate(true);
                super.onPageStarted(view, url, favicon);
            }
        });
        if (isOnline()) {
            viewHolder.webView.loadUrl("https://github.com/p3dr007");
        } else {
            String summary = "<html><body><font color='red'>Sem conexão com a Internet.</font></body></html>";
            viewHolder.webView.loadData(summary, "text/html", null);
            toast("Sem conexão com a Internet.");
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    private static class ViewHolder {
        private WebView webView;
        private ProgressBar progressBar;
        private LinearLayout layoutProgress;
    }

}