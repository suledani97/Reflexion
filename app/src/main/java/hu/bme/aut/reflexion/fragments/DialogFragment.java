package hu.bme.aut.reflexion.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import hu.bme.aut.reflexion.R;
import hu.bme.aut.reflexion.TestActivity;


public class DialogFragment extends android.support.v4.app.DialogFragment {

    private EditText nameInput;

    public DialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Please enter your nickname")
                .setView(getContentView())
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((TestActivity)getActivity()).fragmentButtonPressed(nameInput);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dialog, null);
        nameInput = contentView.findViewById(R.id.etName);
        return contentView;
    }
}
