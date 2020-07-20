package org.maktab36.gamesapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;

import org.maktab36.gamesapp.R;
import org.maktab36.gamesapp.controller.fragment.MainButtonsFragment;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment_games = fragmentManager.findFragmentById(R.id.fragment_container_games);
        Fragment fragment_buttons=fragmentManager.findFragmentById(R.id.fragment_container_buttons);

        if(fragment_buttons==null){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_buttons,new MainButtonsFragment())
                    .commit();
        }

        if (fragment_games == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_games, createFragment())
                    .commit();
        }
    }

}