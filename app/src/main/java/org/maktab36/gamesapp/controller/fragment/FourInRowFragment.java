package org.maktab36.gamesapp.controller.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.material.snackbar.Snackbar;

import org.maktab36.gamesapp.R;
import org.maktab36.gamesapp.model.GameStates;

public class FourInRowFragment extends Fragment {
    private TableLayout mTableLayout;
    private String mCurrentPlayer = "red";
    Button[][] mButtonsGame = new Button[5][5];
    private Button mButtonTryAgain;

    public FourInRowFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four_in_row, container, false);
        findViews(view);
        setListener();
        return view;
    }

    private void findViews(View view) {
        mTableLayout = view.findViewById(R.id.table_4_in_row);
        mButtonTryAgain = view.findViewById(R.id.button_4_in_row_try_again);
    }

    private void setListener() {
        mButtonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void setButtonsArray() {
        for (int i = 0; i < mTableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) mTableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                mButtonsGame[i][j] = (Button) row.getChildAt(j);
            }
        }
    }

    private int[] getPoint(Button button) {
        int[] point = new int[2];
        a:
        for (int i = 0; i < mButtonsGame.length; i++) {
            for (int j = 0; j < mButtonsGame[0].length; j++) {
                if (mButtonsGame[i][j].getId() == button.getId()) {
                    point[0] = i;
                    point[1] = j;
                    break a;
                }
            }
        }
        return point;
    }

    public void setButtonsColor(View view) {
        Button button = (Button) view;
        setButtonsArray();
        int[] point = getPoint(button);
        int row = point[0];
        int column = point[1];
        int lastColor = 0;
        for (int i = mButtonsGame.length - 1; i >= 0; i--) {
            Button button1 = mButtonsGame[i][column];
            int color = ((ColorDrawable) button1.getBackground()).getColor();
            if (color == Color.WHITE) {
                switch (mCurrentPlayer) {
                    case "red":
                        button1.setBackgroundColor(Color.RED);
                        row = i;
                        break;
                    case "blue":
                        button1.setBackgroundColor(Color.BLUE);
                        row = i;
                        break;
                }
                break;
            }
        }
        switch (checkState(row, column)) {
            case CONTINUE:
                switchTurn();
                break;
            case PLAYER_1_WINS:
                endGame("red wins");
                break;
            case PLAYER_2_WINS:
                endGame("blue wins");
                break;
            case DRAW:
                endGame("DRAW");
                break;
        }
    }

    private void switchTurn() {
        switch (mCurrentPlayer) {
            case "red":
                mCurrentPlayer = "blue";
                break;
            case "blue":
                mCurrentPlayer = "red";
                break;
        }
    }

    private GameStates checkState(int row, int column) {
        int count = 0;
        int lastColor = Color.parseColor(mCurrentPlayer.toUpperCase());
        for (int i = 0; i < mButtonsGame[0].length; i++) {
            int buttonColor = ((ColorDrawable) mButtonsGame[row][i].getBackground()).getColor();
            if (buttonColor == lastColor) {
                count++;
            } else {
                count = 0;
            }

            if (count >= 4) {
                if (mCurrentPlayer.equals("red")) {
                    return GameStates.PLAYER_1_WINS;
                } else {
                    return GameStates.PLAYER_2_WINS;
                }
            }
        }

        for (int i = 0; i < mButtonsGame.length; i++) {
            int buttonColor = ((ColorDrawable) mButtonsGame[i][column].getBackground()).getColor();
            if (buttonColor == lastColor) {
                count++;
            } else {
                count = 0;
            }

            if (count >= 4) {
                if (mCurrentPlayer.equals("red")) {
                    return GameStates.PLAYER_1_WINS;
                } else {
                    return GameStates.PLAYER_2_WINS;
                }
            }
        }

        for (int i = 0; i <5 ; i++) {
            count=0;
            for (int j = 0; j <=i ; j++) {
                int buttonColor = ((ColorDrawable) mButtonsGame[j][i-j].getBackground()).getColor();
                if(buttonColor==lastColor){
                    count++;
                }else{
                    count=0;
                }
                if(count>=4){
                    if (mCurrentPlayer.equals("red")) {
                        return GameStates.PLAYER_1_WINS;
                    } else {
                        return GameStates.PLAYER_2_WINS;
                    }
                }
            }
        }

        for (int i = 0; i <5 ; i++) {
            count=0;
            for (int j = 0; j <=i ; j++) {
                int buttonColor = ((ColorDrawable) mButtonsGame[j][4-i+j].getBackground()).getColor();
                if(buttonColor==lastColor){
                    count++;
                }else{
                    count=0;
                }
                if(count>=4){
                    if (mCurrentPlayer.equals("red")) {
                        return GameStates.PLAYER_1_WINS;
                    } else {
                        return GameStates.PLAYER_2_WINS;
                    }
                }
            }
        }
        if(isBoardFull()){
            return GameStates.DRAW;
        }else{
            return GameStates.CONTINUE;
        }
    }

    private boolean isBoardFull(){
        for (int i = 0; i <mButtonsGame.length ; i++) {
            for (int j = 0; j <mButtonsGame[0].length ; j++) {
                int color = ((ColorDrawable) mButtonsGame[i][j].getBackground()).getColor();
                if (color == Color.WHITE) {
                    return false;
                }
            }
        }
        return true;
    }

    private void endGame(String winner) {
        setButtonsEnabled(false);
        mButtonTryAgain.setVisibility(View.VISIBLE);
        showSnackBar(winner);
    }

    private void setButtonsEnabled(boolean enabled) {
        for (int i = 0; i < mButtonsGame.length; i++) {
            for (int j = 0; j < mButtonsGame[0].length; j++) {
                mButtonsGame[i][j].setEnabled(enabled);
            }
        }
    }

    private void startGame() {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_games, new FourInRowFragment())
                .commit();
    }

    private void showSnackBar(String winner) {
        String str = "result of game: " + winner;
        Snackbar snackbar = Snackbar
                .make(getView(), str, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}