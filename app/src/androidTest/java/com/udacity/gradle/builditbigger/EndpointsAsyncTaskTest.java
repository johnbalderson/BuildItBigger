package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class EndpointsAsyncTaskTest extends ApplicationTestCase<Application> {

   String mJokeString = null;
    Exception mError = null;
    CountDownLatch signal = null;

    public EndpointsAsyncTaskTest() {
        super(Application.class);
    }

   @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testJokeRetrieved() throws InterruptedException {

        EndpointsAsyncTask task = new EndpointsAsyncTask();
        Random random = new Random();
        int randomInt = random.nextInt(10);

        task.setListener(new EndpointsAsyncTask.getJokeListener() {
            @Override
            public void onComplete(String jokeString, Exception e) {
                mJokeString = jokeString;
                mError = e;
                signal.countDown();
            }
        }).execute(new Pair<Context, Integer>(InstrumentationRegistry.getTargetContext(), randomInt));
        signal.await();


        //test the response
        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mJokeString));
    }
}