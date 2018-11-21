package hu.bme.aut.reflexion.timer;

import android.app.Activity;
import android.widget.TextView;

import java.util.TimerTask;

public class StopwatchTask extends TimerTask {

    private static long STARTING_MILIS;

    private Activity currentActivity;
    private TextView stopwatchText;

    private long currentmillis;

    public StopwatchTask(Activity currentActivity, TextView stopwatchtext, long starttime){
        this.currentActivity = currentActivity;
        stopwatchText = stopwatchtext;
        STARTING_MILIS = starttime;
    }

    @Override
    public void run() {
        currentmillis = System.currentTimeMillis() - STARTING_MILIS;
        final int currentseconds = (int) currentmillis/1000;
        final int milisecond = (int) currentmillis % 1000;
       currentActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                stopwatchText.setText(String.valueOf(currentseconds) + " : " + String.valueOf(milisecond));
            }
        });
    }
}
