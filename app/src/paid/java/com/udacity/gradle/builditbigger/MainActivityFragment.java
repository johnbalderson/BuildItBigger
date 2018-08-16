package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 * This activity is for the paid version, and does NOT display an ad.
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

        Button jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(this);

        return root;
    }



    @Override
    public void onClick(View view) {
        Random random = new Random();
        int randomJoke = random.nextInt(jokeCount);

        new EndpointsAsyncTask().execute(new Pair<Context, Integer>(mContext, randomJoke));
    }
}