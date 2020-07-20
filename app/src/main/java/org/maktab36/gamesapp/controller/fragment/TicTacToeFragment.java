package org.maktab36.gamesapp.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.maktab36.gamesapp.R;
import org.maktab36.gamesapp.model.TicTacStates;

public class TicTacToeFragment extends Fragment {
    private String mCurrentPlayer = "X";
    private TableLayout mTableLayout;
    private Button[][] mButtonsGame =new Button[3][3];
    private Button mButtonTryAgain;

    public TicTacToeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        setListener();
        return view;
    }

    private void setListener() {
        mButtonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void findViews(View view) {
        mTableLayout = view.findViewById(R.id.table);
        mButtonTryAgain=view.findViewById(R.id.button_tic_tac_try_again);
    }


    public void setButtonsText(View view) {
        Button button = (Button) view;
        if (button.getText().equals("")) {
            switch (mCurrentPlayer) {
                case "X":
                    button.setText("X");
                    break;
                case "O":
                    button.setText("O");
                    break;
            }
        }
        switch (checkState()) {
            case CONTINUE:
                switchTurn();
                break;
            case X_WINS:
                endGame("X wins");
                break;
            case O_WINS:
                endGame("O wins");
                break;
            case DRAW:
                endGame("DRAW");
                break;
        }
    }

    private void switchTurn() {
        switch (mCurrentPlayer) {
            case "X":
                mCurrentPlayer = "O";
                break;
            case "O":
                mCurrentPlayer = "X";
                break;
        }
    }

    private TicTacStates checkState() {
        boolean flag = false;
        String[][] buttonsText = new String[3][3];
        for (int i = 0; i < mTableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) mTableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                mButtonsGame[i][j] = (Button) row.getChildAt(j);
                buttonsText[i][j] = mButtonsGame[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 8; i++) {
            String line = null;
            switch (i) {
                case 0:
                    line = buttonsText[0][0] + buttonsText[0][1] + buttonsText[0][2];
                    break;
                case 1:
                    line = buttonsText[1][0] + buttonsText[1][1] + buttonsText[1][2];
                    break;
                case 2:
                    line = buttonsText[2][0] + buttonsText[2][1] + buttonsText[2][2];
                    break;
                case 3:
                    line = buttonsText[0][0] + buttonsText[1][0] + buttonsText[2][0];
                    break;
                case 4:
                    line = buttonsText[0][1] + buttonsText[1][1] + buttonsText[2][1];
                    break;
                case 5:
                    line = buttonsText[0][2] + buttonsText[1][2] + buttonsText[2][2];
                    break;
                case 6:
                    line = buttonsText[0][0] + buttonsText[1][1] + buttonsText[2][2];
                    break;
                case 7:
                    line = buttonsText[0][2] + buttonsText[1][1] + buttonsText[2][0];
                    break;
            }
            if (line.equals("XXX")) {
                return TicTacStates.X_WINS;
            } else if (line.equals("OOO")) {
                return TicTacStates.O_WINS;
            } else if (line.length() < 3) {
                flag = true;
            }
        }
        if (flag) {
            return TicTacStates.CONTINUE;
        }
        return TicTacStates.DRAW;
    }

    private void endGame(String winner){
        setButtonsEnabled(false);
        mButtonTryAgain.setVisibility(View.VISIBLE);
        showSnackBar(winner);
    }

    private void setButtonsEnabled(boolean enabled) {
        for (int i = 0; i < mButtonsGame.length ; i++) {
            for (int j = 0; j < mButtonsGame[0].length ; j++) {
                mButtonsGame[i][j].setEnabled(enabled);
            }
        }
    }

    private void startGame(){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_games,new TicTacToeFragment())
                .commit();
    }

    private void showSnackBar(String winner){
        String str="result is "+winner;
        Snackbar snackbar=Snackbar
                .make(getView(),str,Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}