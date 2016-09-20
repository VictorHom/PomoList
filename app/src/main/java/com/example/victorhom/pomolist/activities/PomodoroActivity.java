package com.example.victorhom.pomolist.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.victorhom.pomolist.R;

import java.util.ArrayList;

public class PomodoroActivity extends AppCompatActivity {
    private static int tasktime = 60 * 25;
    private static int breaktime = 60 * 5;
    private int seconds;
    private boolean running;
    private boolean wasRunning;
    private int currentPomoIndex = 0;
    private ArrayList<String> pomoTodos;
    private int pomoTodosSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);

        Bundle bundle = getIntent().getExtras();
        ArrayList<Integer> todosIndex = bundle.getIntegerArrayList("todosIndex");
        ArrayList<String> todos = bundle.getStringArrayList("todos");

        tasktime = Integer.valueOf(bundle.get("taskTime").toString()) * 60;
        breaktime = Integer.valueOf(bundle.get("breakTime").toString()) * 60;
        seconds = tasktime;

        pomoTodos = new ArrayList<>();

        //sanity check that the todos are getting to here
        for (int i = 0; i < todos.size(); i++) {
            pomoTodos.add(todos.get(i));
            pomoTodos.add("take a break. browse reddit. you deserve it");
        }
        pomoTodosSize = pomoTodos.size();

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    public void setTaskText(int index) {
        TextView tv = (TextView) findViewById(R.id.current_pomo_item);
        if (pomoTodos.size() > 0) {
            tv.setText(pomoTodos.get(index));
        }
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true;
        setTaskText(currentPomoIndex);
    }

    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false;
    }

    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false;
        seconds = tasktime;
        currentPomoIndex = 0;
    }


    //Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
            int minutes = (seconds%3600)/60;
            int secs = seconds%60;
            String time = String.format("%02d:%02d", minutes, secs);
            timeView.setText(time);
            if (running) {
                seconds--;
                if (seconds == 0) {
                    // alternate now
                    if (currentPomoIndex % 2 == 1) {
                        seconds = breaktime;
                    } else {
                        seconds = tasktime;
                    }
                    // change text now
                    currentPomoIndex = (currentPomoIndex + 1) % pomoTodosSize;
                    setTaskText(currentPomoIndex);

                }
            }
            handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.trans_right_in, R.animator.trans_right_out);
    }
}