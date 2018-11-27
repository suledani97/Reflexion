package hu.bme.aut.reflexion;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import hu.bme.aut.reflexion.data.ResultData;
import hu.bme.aut.reflexion.fragments.DialogFragment;
import hu.bme.aut.reflexion.timer.StopwatchTask;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class TestActivity extends AppCompatActivity {

    private static int RANDOM_TIME_LOWER_MS = 2000;
    private static int RANDOM_TIME_UPPER_MS = 10000;

    Button bigButton;
    TextView timerText;
    Timer updateTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        bigButton = findViewById(R.id.btnMain);
        timerText = findViewById(R.id.tvTimer);

        Random randomTime = new Random();
        changeButtonAfter(RANDOM_TIME_LOWER_MS
                + randomTime.nextInt(RANDOM_TIME_UPPER_MS));

        bigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigButton.setText(R.string.press_done);
                bigButton.setBackgroundResource(R.drawable.yellowbutton);
                updateTimer.cancel();
                showDialogFragment();
            }
        });
    }

    private void showDialogFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(fragmentManager, "fragment_dialog");
    }

    private void changeButtonStatus(){
        bigButton.setBackgroundResource(R.drawable.greenbutton);
        bigButton.setText(R.string.press_right);
        bigButton.setEnabled(true);
    }

    private void changeButtonAfter(int delay_ms){
        final Handler delayer = new Handler();
        delayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeButtonStatus();
                startTimer(System.currentTimeMillis());
            }
        }, delay_ms);
    }

    private void startTimer(long startTime){

        final long fstartTime = startTime;
        updateTimer = new Timer();
        StopwatchTask stopwatch = new StopwatchTask(this, timerText, startTime);
        updateTimer.scheduleAtFixedRate(stopwatch,0, 1);
    }

    public void fragmentButtonPressed(String nickname){
        Realm realm = Realm.getDefaultInstance();
        Log.i("Realm", realm.getPath());
        RealmQuery<ResultData> query = realm.where(ResultData.class);
        query.equalTo("name", nickname);

        ResultData nickData = query.findFirst();
        realm.beginTransaction();
        if (nickData == null){
            ResultData newData = realm.createObject(ResultData.class);
            newData.setName(nickname);
        }
        realm.commitTransaction();
        realm.close();

        Toast.makeText(getApplicationContext(), "Result saved, name: "+nickname, Toast.LENGTH_LONG).show();
        Intent gameEndIntent = new Intent(TestActivity.this, MenuActivity.class);
        startActivity(gameEndIntent);
    }
}
