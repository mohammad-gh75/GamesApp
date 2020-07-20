package org.maktab36.gamesapp.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.maktab36.gamesapp.R;
import org.maktab36.gamesapp.controller.activity.SettingActivity;
import org.maktab36.gamesapp.controller.activity.SingleFragmentActivity;

public class MainButtonsFragment extends Fragment {

    public static final int REQUEST_CODE_SETTING_ACTIVITY = 0;

    private Button mButtonSetting;
    private Button mButtonTicTac;
    private Button mButtonFourInRow;

    public MainButtonsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_buttons, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mButtonSetting=view.findViewById(R.id.button_setting);
        mButtonTicTac=view.findViewById(R.id.button_tic_tac_toe);
        mButtonFourInRow=view.findViewById(R.id.button_4_in_row);
    }

    private void setListeners(){
        mButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(
                        getActivity(),
                        SettingActivity.class
                );
                startActivity(intent);
            }
        });

        mButtonTicTac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_games,new TicTacToeFragment())
                        .commit();
            }
        });

        mButtonFourInRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_games,new FourInRowFragment())
                        .commit();
            }
        });
    }
}