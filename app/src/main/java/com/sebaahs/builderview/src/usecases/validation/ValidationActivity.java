package com.sebaahs.builderview.src.usecases.validation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.sebaahs.builderview.R;
import com.sebaahs.builderview.src.usecases.validation.pages.LogInFragment;

import org.jetbrains.annotations.NotNull;

public class ValidationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Set del tema de la aplicacion ( remplaza el splashscreen )
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);



        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_validation,new LogInFragment())
                .commit();

    }
}