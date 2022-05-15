package com.roy4d;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    WebView app;
    AdManager adManager;
    WebSettings appSettings;
    static final int REQUEST_FILE_PICKER = 1;
    static ValueCallback<Uri> mFilePathCallback4;
    static ValueCallback<Uri[]> mFilePathCallback5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = new WebView(this);
        setContentView(app);
        app.loadUrl("file:///android_asset/index.html");
        initAppSettings();
        app.addJavascriptInterface(new WebAppInterface(this), "Android");
        app.setWebViewClient(new MyWebViewClient(this));
        app.setWebChromeClient(new AppChromeClient(this));
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)){
            WebSettingsCompat.setForceDark(app.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
        }

        adManager = new AdManager(this);
    }

    public void initAppSettings(){
        appSettings = app.getSettings();
        appSettings.setJavaScriptEnabled(true);
        appSettings.setAllowFileAccess(true);
        appSettings.setDatabaseEnabled(true);
        appSettings.setDomStorageEnabled(true);
        appSettings.setMediaPlaybackRequiresUserGesture(false);
        appSettings.setUserAgentString(getPackageName());
        appSettings.setGeolocationEnabled(true);
        appSettings.setAppCacheEnabled(true);
        appSettings.setAllowFileAccessFromFileURLs(true);
        appSettings.setSupportMultipleWindows(true);
    }

    public void shareApp() {
        Intent myapp = new Intent(Intent.ACTION_SEND);
        myapp.setType("text/plain");
        myapp.putExtra(Intent.EXTRA_TEXT, "Get your first low interest loan here today.\n https://play.google.com/store/apps/details?id=" + getPackageName() + " \n");
        startActivity(myapp);
    }

    public void rateApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && app.canGoBack()) {
            app.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void sendMessage(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse("whatsapp://"));
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        if (intent == null){
            intent.setPackage("com.whatsapp.w4b");
        }
        if (intent != null){
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Whatsapp not Found!",Toast.LENGTH_LONG).show();
        }
    }

    public void showDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(msg)
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                }).show();
        AlertDialog dialog = builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_FILE_PICKER) {
            if (mFilePathCallback4 != null) {
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                if (result != null) {
                    mFilePathCallback4.onReceiveValue(result);
                } else {
                    mFilePathCallback4.onReceiveValue(null);
                }
            }
            if (mFilePathCallback5 != null) {
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                if (result != null) {
                    mFilePathCallback5.onReceiveValue(new Uri[]{result});
                } else {
                    mFilePathCallback5.onReceiveValue(null);
                }
            }

            mFilePathCallback4 = null;
            mFilePathCallback5 = null;
        }
    }
}
