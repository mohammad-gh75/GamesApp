package org.maktab36.gamesapp.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab36.gamesapp.R;

public class TicTacToeFragment extends Fragment {

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
        View view=inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {
    }
}