package com.example.kike.Notitas;

import android.content.Intent;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

import static android.support.v4.content.res.TypedArrayUtils.getString;

public class ActividadPrincipal extends AppCompatActivity {

    private static final String TAG = "ActividadPrincipal";

    private SectionsAdapter mSectionsAdapter;

    private ViewPager mViewPager;
    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_principal);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("NOTITAS O.K.A");



        Log.d(TAG, "onCreate: Starting.");

        startService(new Intent(getBaseContext(),Servicio.class));

        mSectionsAdapter = new SectionsAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsAdapter adapter = new SectionsAdapter(getSupportFragmentManager());
        try {
            adapter.addFragment(new TabNota(), getString(R.string.notas));
            adapter.addFragment(new TabTarea(), getString(R.string.tareas));
            viewPager.setAdapter(adapter);
        }catch (Exception err){

    }
    }





}
