package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ratik.jokedroid.JokeActivity;
import com.udacity.gradle.builditbigger.FetchJokeTask;
import com.udacity.gradle.builditbigger.GetJokeListener;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity implements GetJokeListener {

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
    }

    public void tellJoke(View view) {
        new FetchJokeTask().execute(this);
    }

    @Override
    public void onGetJokeCompleted(String jokeText) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("joke", jokeText);
        startActivity(intent);
    }
}
