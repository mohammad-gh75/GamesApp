package org.maktab36.gamesapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import org.maktab36.gamesapp.controller.fragment.TicTacToeFragment;

public class TicTacToeActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TicTacToeFragment();
    }
}