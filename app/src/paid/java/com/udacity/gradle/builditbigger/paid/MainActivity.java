package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ratik.jokedroid.JokeActivity;
import com.udacity.gradle.builditbigger.FetchJokeTask;
import com.udacity.gradle.builditbigger.GetJokeListener;
import com.udacity.gradle.builditbigger.R;

public class MainActivity extends AppCompatActivity implements GetJokeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tellJoke(View view) {
        new FetchJokeTask(this).execute(this);
    }

    @Override
    public void onGetJokeCompleted(String jokeText) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("joke", jokeText);
        startActivity(intent);
    }
}
