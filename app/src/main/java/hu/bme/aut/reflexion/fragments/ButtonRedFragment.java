package hu.bme.aut.reflexion.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.aut.reflexion.R;

public class ButtonRedFragment extends Fragment {

    public ButtonRedFragment() {
        // Required empty public constructor
    }

    public static ButtonRedFragment newInstance() {
        ButtonRedFragment fragment = new ButtonRedFragment();
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
        return inflater.inflate(R.layout.fragment_button_red, container, true);
    }
}
