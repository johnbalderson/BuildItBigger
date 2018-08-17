package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;


/**
 * A placeholder fragment containing a simple view.
 * This activity is for the free version, and displays an ad.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private Context mContext;
    public int jokeCount = 10;

    public MainActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeButton = root.findViewById(R.id.tell_joke_button);
        jokeButton.setOnClickListener(this);

       AdView mAdView =  root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        return root;
        }



    @Override
    public void onClick(View view) {
        Random random = new Random();
        int randomInt = random.nextInt(jokeCount);

        new EndpointsAsyncTask().execute(new Pair<Context, Integer>(mContext, randomInt));
    }
}


