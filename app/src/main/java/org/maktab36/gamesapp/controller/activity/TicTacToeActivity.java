package org.maktab36.gamesapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import org.maktab36.gamesapp.R;
import org.maktab36.gamesapp.controller.fragment.TicTacToeFragment;

public class TicTacToeActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TicTacToeFragment();
    }

    public void setButtonsText(View v){
        TicTacToeFragment ticTacToe=(TicTacToeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_games);
        ticTacToe.setButtonsText(v);
    }
}