package org.maktab36.gamesapp.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.maktab36.gamesapp.R;
import org.maktab36.gamesapp.controller.fragment.FourInRowFragment;
import org.maktab36.gamesapp.controller.fragment.MainButtonsFragment;
import org.maktab36.gamesapp.controller.fragment.TicTacToeFragment;

public class GameActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mFragmentManager = getSupportFragmentManager();
        Fragment fragment_Games = mFragmentManager.findFragmentById(R.id.fragment_container_games);
        Fragment fragment_Buttons = mFragmentManager.findFragmentById(R.id.fragment_container_buttons);

        if (fragment_Buttons == null) {
            mFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_buttons, new MainButtonsFragment())
                    .commit();
        }

        if (fragment_Games == null) {
            mFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_games, new TicTacToeFragment())
                    .commit();
        }
    }

    public void setButtonsText(View view) {
        TicTacToeFragment ticTacToe = (TicTacToeFragment) mFragmentManager
                .findFragmentById(R.id.fragment_container_games);
        ticTacToe.setButtonsText(view);
    }

    public void setButtonsColor(View view) {
        FourInRowFragment fourInRow = (FourInRowFragment) mFragmentManager
                .findFragmentById(R.id.fragment_container_games);
        fourInRow.setButtonsColor(view);
    }
}