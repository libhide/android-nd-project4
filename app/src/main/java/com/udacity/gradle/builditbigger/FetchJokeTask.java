package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.ratik.builditbigger.jokebackend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Ratik on 09/07/16.
 */
public class FetchJokeTask extends AsyncTask<GetJokeListener, Void, String> {

    private static MyApi myApiService = null;
    private GetJokeListener listener;
    private Context context;

    @Override
    protected String doInBackground(GetJokeListener... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger---android-nd.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        listener = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        listener.onGetJokeCompleted(joke);
    }
}
