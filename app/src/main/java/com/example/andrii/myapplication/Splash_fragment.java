package com.example.andrii.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;

public class Splash_fragment extends Fragment {
    private static final int LAYOUT = R.layout.splash_screen;
    private View view;
    private Main_fragment main_fragment;
    private DataBaseHelper myDB;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        main_fragment = new Main_fragment();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        myDB = new DataBaseHelper(getContext());
        myDB.removeAll();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), MainActivity_drawer.class);
                startActivity(intent);
            }
        }, 3*1000);







        return view;
    }




}
