package com.example.zef.iqoptiondemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{
    FragmentTabHost tabHost;
    public static int MIN_AMOUNT=1;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    Spinner spinner;
    ArrayAdapter<CharSequence>option_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.syncState();

        //setting tab host
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.fragmenttabholder);

        //Investment tab host
        tabHost.addTab(tabHost.newTabSpec("Investment").setIndicator("INVESTMENT"), TabFragmentInvestment.class, null);
        TextView tI = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tI.setTextSize(9);
        tI.setTextColor(Color.RED);

        //Refund tab host
        tabHost.addTab(tabHost.newTabSpec("Refund").setIndicator("REFUND"), TabFragmentDeposit.class, null);
        TextView tR = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tR.setTextSize(9);
        tR.setTextColor(Color.WHITE);

        tabHost.setOnTabChangedListener(this);



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch(item.getItemId()){
                    case R.id.personal_data:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentPersonalData());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.deposit:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentDeposit());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.withdrawls:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentWithdrawls());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.operation_history:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentOperationHistory());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.trading_History:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentTradingHistory());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.support:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentSupport());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.preferences:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentPreferences());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.faq:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentFAQ());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.education:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new fragmentEducation());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.tutorial:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentTutorial());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.abt_company:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container,new FragmentAbtCompany());
                        fragmentTransaction.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.logout:
                        finish();


                }
                return false;
            }
        });
        spinner = (Spinner) findViewById(R.id.option_type);

        option_type = ArrayAdapter.createFromResource(this, R.array.options_type, R.layout.spinner_item);
        spinner.setAdapter(option_type);


        option_type.createFromResource(this,R.array.options_type,R.layout.spinner_item);
        option_type.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(option_type);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //increase and decrease amount value on increase and decrease buttons clicks
    public void decreaseInteger(View view) {
        if (MIN_AMOUNT == 1) {
            MIN_AMOUNT = 1;
        } else {
            MIN_AMOUNT = MIN_AMOUNT - 1;
            display(MIN_AMOUNT);
        }
    }

    public void increaseInteger(View view) {
        MIN_AMOUNT = MIN_AMOUNT + 1;
        display(MIN_AMOUNT);
    }

    private void display(int number) {
        TextView displayAmount = (TextView) findViewById(R.id.textAmount);
        displayAmount.setText("$" + number);
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

    public void hideNavDrawer(View view) {
        drawer.closeDrawers();

    }

    public void exitApp(MenuItem item) {

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onTabChanged(String tabId) {
        TextView tvInvestment = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        TextView tvRefund = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        boolean selected = "Investment".equals(tabId);

        tvInvestment.setTextColor(selected ? Color.RED : Color.WHITE);
        tvRefund.setTextColor(!selected ? Color.RED : Color.WHITE);
    }

    public void openNavDrawer(View view) {
        drawer.openDrawer(Gravity.LEFT);
    }
}
