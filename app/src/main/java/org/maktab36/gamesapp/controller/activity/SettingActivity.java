package org.maktab36.gamesapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import org.maktab36.gamesapp.R;
import org.maktab36.gamesapp.controller.fragment.SettingFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_games);

        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_games, new SettingFragment())
                    .commit();
        }
    }
}