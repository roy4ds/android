package com.roy4d;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdManager extends Application {

    private AdView adView1, adView2;
    Context mContext;
    Activity activity;
    AdRequest adRequest;
    FrameLayout adView1Co, adView2Co;
    private InterstitialAd mInterstitialAd;
    boolean destroyNative = false;
    private static AppOpenManager appOpenManager;

    public  AdManager(){
        //
    }

    public AdManager(Context c){
        mContext = c;
        activity = (Activity) c;
        adView1Co = activity.findViewById(R.id.adView1Co);
        adView2Co = activity.findViewById(R.id.adView2Co);
        init(c);
        adRequest = new AdRequest.Builder().build();
        appOpenManager = new AppOpenManager(activity);
        loadInter();
    }

    public void init(Context c){
        MobileAds.initialize(c, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    public void loadInter(){
        InterstitialAd.load(mContext, mContext.getString(R.string.inter1), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        mInterstitialAd = null;
                        loadInter();
                        Log.d("TAG", "The ad was dismissed.");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        mInterstitialAd = null;
                        loadInter();
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        Log.d("TAG", "The ad was shown.");
                    }
                });
                Log.i("TAG", "Inter Loaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                mInterstitialAd = null;
                Log.i("TAG", loadAdError.getMessage());
                String error = String.format("domain: %s, code: %d, message: %s", loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                Toast.makeText(mContext, "Ad: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showInter(){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            loadInter();
        }
    }
}
