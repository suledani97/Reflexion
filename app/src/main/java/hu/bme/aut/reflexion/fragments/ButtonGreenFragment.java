package hu.bme.aut.reflexion.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.aut.reflexion.R;

public class ButtonGreenFragment extends android.support.v4.app.Fragment {

    public ButtonGreenFragment() {
        // Required empty public constructor
    }

    public static ButtonGreenFragment newInstance() {
        ButtonGreenFragment fragment = new ButtonGreenFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_button_green, container, false);
    }
}
