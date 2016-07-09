package com.udacity.gradle.builditbigger;

/**
 * Created by Ratik on 10/07/16.
 */

import android.os.ConditionVariable;
import android.test.AndroidTestCase;
import android.text.TextUtils;

/**
 * @see <a href="https://gist.github.com/he9lin/2195897">How to test AsyncTask in Android</a>
 */
public class JokeTest extends AndroidTestCase implements GetJokeListener {

    private FetchJokeTestTask fetchJokeTask;
    private ConditionVariable waiter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fetchJokeTask = new FetchJokeTestTask();
        waiter = new ConditionVariable();
    }

    public void testJokeIsNotEmpty() {
        fetchJokeTask.execute(this);
        waiter.block();
    }

    @Override
    public void onGetJokeCompleted(String jokeText) {
        assertFalse(TextUtils.isEmpty(jokeText));
        waiter.open();
    }
}
