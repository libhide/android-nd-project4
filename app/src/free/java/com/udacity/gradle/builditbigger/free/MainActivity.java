package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.ratik.jokedroid.JokeActivity;
import com.udacity.gradle.builditbigger.FetchJokeTask;
import com.udacity.gradle.builditbigger.GetJokeListener;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity implements GetJokeListener {
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Banner Ad
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("15D3EEA36934234EAA79BA643D53B37E")
                .build();
        mAdView.loadAd(adRequest);

        // Interstitial
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                new FetchJokeTask(MainActivity.this).execute(MainActivity.this);
            }
        });
        requestNewInterstitial();
    }

    public void tellJoke(View view) {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            // Nothing here
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("15D3EEA36934234EAA79BA643D53B37E")
                .build();

        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onGetJokeCompleted(String jokeText) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("joke", jokeText);
        startActivity(intent);
    }
}
