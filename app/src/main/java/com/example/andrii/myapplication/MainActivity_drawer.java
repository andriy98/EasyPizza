package com.example.andrii.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.actionitembadge.library.ActionItemBadge;

import java.util.ArrayList;


public class MainActivity_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Main_fragment main_fragment;
    private Details_fragment details_fragment;
    private Basket_fragment basket_fragment;
    private Drinks_fragment drinks_fragment;
    private Offers_fragment offers_fragment;
    private Delivery_fragment delivery_fragment;
    private Drawable icon;
    public static MenuItem item;
    public int badgeCount=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        icon = getResources().getDrawable(R.drawable.ic_basket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        main_fragment = new Main_fragment();
        details_fragment = new Details_fragment();
        basket_fragment = new Basket_fragment();
        drinks_fragment = new Drinks_fragment();
        offers_fragment = new Offers_fragment();
        delivery_fragment = new Delivery_fragment();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main2, main_fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        item = menu.getItem(0);
        ActionItemBadge.update(this, menu.findItem(R.id.item_samplebadge), icon,ActionItemBadge.BadgeStyles.RED, badgeCount);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main2, basket_fragment).commit();
        }catch (IllegalStateException e){
            Toast.makeText(getApplicationContext(),"Da",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.pizza) {
            fragmentTransaction.replace(R.id.content_main2, main_fragment);
        } else if (id == R.id.drinks) {
            fragmentTransaction.replace(R.id.content_main2, drinks_fragment);
        } else if (id == R.id.offers) {
            fragmentTransaction.replace(R.id.content_main2, offers_fragment);
        } else if (id == R.id.shipping) {
            fragmentTransaction.replace(R.id.content_main2, delivery_fragment);
        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
