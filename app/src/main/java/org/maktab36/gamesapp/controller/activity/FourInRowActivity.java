package org.maktab36.gamesapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import org.maktab36.gamesapp.controller.fragment.FourInRowFragment;

public class FourInRowActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new FourInRowFragment();
    }
}