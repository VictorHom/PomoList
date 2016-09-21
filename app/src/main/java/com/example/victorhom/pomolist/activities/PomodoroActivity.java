package com.example.victorhom.pomolist.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    private int taskTime = 60 * 25;
    private int breakTime = 60 * 5;
    private int seconds;
    private boolean running;
    private boolean wasRunning;
    private int currentPomoIndex = 0;
    private ArrayList<String> pomoTodos;
    private int pomoTodosSize;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        clearActionBar();

        // get the task items, the task time, and the break time
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> todos = bundle.getStringArrayList("todos");

        taskTime = Integer.valueOf(bundle.get("taskTime").toString()) * 60;
        breakTime = Integer.valueOf(bundle.get("breakTime").toString()) * 60;
        seconds = taskTime;

        pomoTodos = setupPomodoroList(todos);
        pomoTodosSize = pomoTodos.size();

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
    }

    // insert each todos followed by the break message
    private ArrayList<String> setupPomodoroList(ArrayList<String> todos) {
        ArrayList<String> t = new ArrayList<>();
        for (int i = 0; i < todos.size(); i++) {
            t.add(todos.get(i));
            t.add(getResources().getString(R.string.break_message));
        }
        return t;
    }

    private void clearActionBar() {
        // the icon will put you back on main acvitity
        // the back button at bottom of screen will give you a warning dialog too to teach users
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("");
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
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
        seconds = taskTime;
        currentPomoIndex = 0;
    }


    //Sets the number of seconds on the timer.
    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        handler = new Handler();
        runnable = new Runnable() {
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
                            seconds = breakTime;
                        } else {
                            seconds = taskTime;
                        }
                        // change text now
                        currentPomoIndex = (currentPomoIndex + 1) % pomoTodosSize;
                        setTaskText(currentPomoIndex);
                    }
                }
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.warning)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        handler.removeCallbacks(runnable);
                        PomodoroActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
