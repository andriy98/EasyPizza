package com.example.andrii.myapplication;

import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.mikepenz.actionitembadge.library.ActionItemBadge;


public class MainActivity extends AppCompatActivity {
    private Main_fragment main_fragment;
    private Splash_fragment splash_fragment;
    private Drawable icon;
    private int badgeCount=0 ;

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        ActionItemBadge.update(this, menu.findItem(R.id.item_samplebadge), icon,ActionItemBadge.BadgeStyles.RED, badgeCount);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toovoid);
        icon = getResources().getDrawable(R.drawable.ic_basket);
        main_fragment = new Main_fragment();
        splash_fragment = new Splash_fragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, splash_fragment).commit();

    }


}


