package com.roy4d;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;
    MainActivity activity;
    WebAppInterface(Context c) {
        mContext = c;
        activity = (MainActivity) c;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void showInter(){
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.adManager.showInter();
            }
        });
    }
    @JavascriptInterface
    public void shareApp(){
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.shareApp();
            }
        });
    }
    @JavascriptInterface
    public void rateApp(){
        this.activity.rateApp();
    }

    @JavascriptInterface
    public void sendMessage(String text){
        this.activity.sendMessage(text);
    }
    @JavascriptInterface
    public void showDialog(String title, String msg){
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.showDialog(title,msg);
            }
        });
    }
    @JavascriptInterface
    public void alert() {
//        AlertDialog.Builder myDialog
//                = new AlertDialog.Builder(BrowserScreen.this);
//        myDialog.setTitle("DANGER!");
//        myDialog.setMessage("You can do what you want!");
//        myDialog.setPositiveButton("ON", null);
//        myDialog.show();
    }
}
