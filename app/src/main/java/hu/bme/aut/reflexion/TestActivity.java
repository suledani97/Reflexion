package hu.bme.aut.reflexion;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;
import java.util.Random;

import hu.bme.aut.reflexion.fragments.ButtonGreenFragment;
import hu.bme.aut.reflexion.fragments.ButtonRedFragment;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button replaceButton = findViewById(R.id.replaceTestButton);

        replaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.redButtonFragment, ButtonGreenFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

    }
}
