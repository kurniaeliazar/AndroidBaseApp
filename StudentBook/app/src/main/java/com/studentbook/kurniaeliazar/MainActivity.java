package com.studentbook.kurniaeliazar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.studentbook.kurniaeliazar.fragments.ListFragment;


/**
 * Created by kurniaeliazar on 3/6/17.
 */

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        ListFragment listFragment = new ListFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, listFragment).commit();


    }
}
