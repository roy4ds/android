package com.roy4d;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    Context mContext;
    MainActivity activity;
    MyWebViewClient(Context c){
        mContext = c;
        activity = new MainActivity();
    }
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String[] ovrs = {"roy4d.com","t.me","secure.3g"};
        String host = request.getUrl().getHost();
        for(String i : ovrs){
            if (host.contains(i)) {
                return false;
            }
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
        mContext.startActivity(intent);
        return true;
    }
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

        activity.app.loadUrl("file:///android_asset/err.html");

//        view.clearHistory();
    }
}
