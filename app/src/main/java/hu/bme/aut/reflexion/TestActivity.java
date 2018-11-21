package hu.bme.aut.reflexion;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class TestActivity extends AppCompatActivity {

    Button bigButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        bigButton = findViewById(R.id.btnMain);

        //Random idő után a gomb zölddé változik
        final Handler delayer = new Handler();
        delayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeButtonStatus();
            }
        }, 3000);
    }

    private void changeButtonStatus(){
        bigButton.setBackgroundResource(R.drawable.greenbutton);
        bigButton.setText(R.string.press_right);
    }
}
