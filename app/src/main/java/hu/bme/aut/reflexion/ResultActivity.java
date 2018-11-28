package hu.bme.aut.reflexion;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hu.bme.aut.reflexion.data.ResultData;
import hu.bme.aut.reflexion.fragments.DialogFragment;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ResultActivity extends AppCompatActivity implements hu.bme.aut.reflexion.interfaces.DialogInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TableLayout tableLayout = findViewById(R.id.main_table);
        createTable(tableLayout);

        Button graphButton = findViewById(R.id.btnGraph);

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFragment();
            }
        });
    }

    private void createTable(TableLayout tableLayout){
        TableRow tr_head = new TableRow(this);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView label_table = new TextView(this);
        label_table.setText("NAME");
        label_table.setTextColor(Color.WHITE);
        label_table.setPadding(5,5,5,5);
        tr_head.addView(label_table);

        TextView label_test = new TextView(this);
        label_test.setText("TIME");
        label_test.setTextColor(Color.WHITE);
        label_test.setPadding(5,5,5,5);
        tr_head.addView(label_test);

        tableLayout.addView(tr_head);

        fillTable(tableLayout);
    }

    private void fillTable(TableLayout tableLayout) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ResultData> results = realm.where(ResultData.class).findAll();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> times = new ArrayList<Integer>();

        for (ResultData resultData : results){
            for (Integer time : resultData.getCollection()){
                names.add(resultData.getName());
                times.add(time);
            }
        }

        TableRow[] rows = new TableRow[names.size()];

        for (int i = 0; i < names.size(); i++){
            rows[i] = new TableRow(this);
            rows[i].setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView label_name = new TextView(this);
            label_name.setText(names.get(i));
            label_name.setTextColor(Color.BLACK);
            label_name.setPadding(5,5,5,5);
            rows[i].addView(label_name);

            TextView label_time = new TextView(this);
            label_time.setText(String.valueOf(times.get(i)));
            label_time.setTextColor(Color.BLACK);
            label_time.setPadding(5,5,5,5);
            rows[i].addView(label_time);

            tableLayout.addView(rows[i]);
        }
    }

    private void showDialogFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(fragmentManager, "fragment_dialog");
    }

    @Override
    public void fragmentButtonPressed(String nickname) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ResultData> results = realm.where(ResultData.class)
                .equalTo("name", nickname).findAll();
        if (results.size() == 0){
            Toast.makeText(getApplicationContext(), "No such name", Toast.LENGTH_SHORT).show();
        }
    }
}
