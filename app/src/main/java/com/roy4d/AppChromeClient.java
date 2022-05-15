package com.roy4d;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class AppChromeClient extends WebChromeClient {
    Context mContext;
    Activity activity;

    public AppChromeClient(Context c){
        mContext = c;
        activity = (Activity) c;
    }
    public void onProgressChanged(WebView view, int progress){
        activity.setProgress(progress * 1000);
    }

    public void openFileChooser(ValueCallback<Uri> filePathCallback)
    {
        MainActivity.mFilePathCallback4 = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"), MainActivity.REQUEST_FILE_PICKER);
    }

    public void openFileChooser(ValueCallback filePathCallback, String acceptType)
    {
        MainActivity.mFilePathCallback4 = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"), MainActivity.REQUEST_FILE_PICKER);
    }

    public void openFileChooser(ValueCallback<Uri> filePathCallback, String acceptType, String capture)
    {
        MainActivity.mFilePathCallback4 = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"), MainActivity.REQUEST_FILE_PICKER);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
    {
        MainActivity.mFilePathCallback5 = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "File Chooser"), MainActivity.REQUEST_FILE_PICKER);
        return true;
    }
}
