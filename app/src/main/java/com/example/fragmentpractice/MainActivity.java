package com.example.fragmentpractice;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements BlankFragment.RadioButtonChoiceListener {
    private static final String SAVE_BOOLEAN_FRAGMENT = "save";


    Button buttonFragment;
    boolean isFragmentOnDisplay =false;
    int radioButtonSelect=-1;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(SAVE_BOOLEAN_FRAGMENT, isFragmentOnDisplay);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void radioButtonChoice(int radioButtonIndex) {
        radioButtonSelect=radioButtonIndex;
        //Toast.makeText(this, "Choice is " + Integer.toString(radioButtonIndex),LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonFragment = findViewById(R.id.buttonFragment);

        if (savedInstanceState != null) {
            isFragmentOnDisplay = savedInstanceState.getBoolean(SAVE_BOOLEAN_FRAGMENT);
            if (isFragmentOnDisplay) {
                buttonFragment.setText("Спрятать");
            } else {
                buttonFragment.setText("Показать");
            }
        }

        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (!isFragmentOnDisplay) {
                        showFragment();
                    } else {
                        closeFragment();
                    }
            }
        });

    }

    private void showFragment() {
        BlankFragment blankFragment = BlankFragment.getInstance(radioButtonSelect);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,blankFragment);
        fragmentTransaction.addToBackStack(null).commit();
        isFragmentOnDisplay =true;
        buttonFragment.setText("Спрятать");
    }

    private void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        BlankFragment blankFragment = (BlankFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (blankFragment!=null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(blankFragment).commit();
            isFragmentOnDisplay = false;
            buttonFragment.setText("Показать");
        }
    }



}
