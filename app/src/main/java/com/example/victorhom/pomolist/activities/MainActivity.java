package com.example.victorhom.pomolist.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.victorhom.pomolist.R;
import com.example.victorhom.pomolist.fragments.TimerSettingsFragment;
import com.example.victorhom.pomolist.fragments.TodoListFragment;
import com.example.victorhom.pomolist.models.TimerSettings;
import com.example.victorhom.pomolist.models.Todo;
import com.example.victorhom.pomolist.utils.Comparators;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements TodoListFragment.TodoListListener {
    TimerSettings ts =  TimerSettings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionBar();

        Todo.myList = new ArrayList<>();
        ArrayList<Todo> todos = (ArrayList<Todo>) Todo.listAll(Todo.class);
        for (int i = todos.size() - 1; i >= 0; --i) {
            Todo.myList.add((Todo) todos.get(i));
        }

        TimerSettings.getInstance();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        setOrderSelection();
    }

    @Override
    public void itemClicked(long id) {
        Todo.myList.remove(0);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        TodoListFragment todolist = (TodoListFragment) getFragmentManager().findFragmentById(R.id.todolist);
        todolist.getAdapter().notifyDataSetChanged();
        todolist.onResume();
        ft.commit();
    }

    public void onClickAddTodo(View view){
        EditText todoInputForm = (EditText) findViewById(R.id.editText);
        String todo = todoInputForm.getText().toString();
        if (todo.length() > 0) {
            Todo newtodo = new Todo().setTodo(todo);
            newtodo.save();
            Todo.myList.add(0, newtodo);
        }
        todoInputForm.setText("");
        TodoListFragment todolist = (TodoListFragment) getFragmentManager().findFragmentById(R.id.todolist);
        todolist.onResume();
    }

    public void setOrderSelection() {
        final Spinner orderSelection = (Spinner) findViewById(R.id.orderselect);
        String[] orderTypes = new String[]{"Added Desc","Added Asc", "Priority", "Due Date"};
        ArrayAdapter<String> adapterO = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, orderTypes);
        adapterO.setDropDownViewResource(R.layout.spinner_text);
        orderSelection.setAdapter(adapterO);
        orderSelection.setSelection(0);
        orderSelection.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.background_color));
        final Comparators c = Comparators.getInstance();
        orderSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String selected = orderSelection.getSelectedItem().toString();
                if (selected.equals("Added Desc")) {
                    Collections.sort(Todo.myList, c.getCompByIDReverse());
                } else if (selected.equals("Added Asc")) {
                    Collections.sort(Todo.myList, c.getCompByID());
                } else if (selected.equals("Priority")) {
                    Collections.sort(Todo.myList, c.getCompByPriority());
                } else {
                    Collections.sort(Todo.myList, c.getCompByDate());
                }
                TodoListFragment todolist = (TodoListFragment) getFragmentManager().findFragmentById(R.id.todolist);
                todolist.onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }



    public void setActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);
        overridePendingTransition(R.animator.trans_left_in, R.animator.trans_left_out);
    }

    public TimerSettings getTS() {
        return ts;
    }

    public void onClickPomodoroStarter(View view) {
        Intent intent = new Intent(this, PomodoroActivity.class);
        ArrayList<Integer> pomodoroListIndexes = new ArrayList<>();
        ArrayList<String> pomodoroList = new ArrayList<>();
        for (int i = 0; i < Todo.myList.size(); i++) {
            if (Todo.myList.get(i).getPomo()) {
                pomodoroListIndexes.add(i);
                pomodoroList.add(Todo.myList.get(i).getTodo());
            }
        }
        intent.putIntegerArrayListExtra("todosIndex", pomodoroListIndexes);
        intent.putStringArrayListExtra("todos", pomodoroList);
        intent.putExtra("taskTime", getTS().getTaskTimeMinute());
        intent.putExtra("breakTime", getTS().getBreakTimeMinute());
        startActivity(intent);
        //finish(); // when this is there, the back button on other activities will go to phone home since this is ended.
    }

    public void onClickSetTimerSettings(View view) {
        FragmentManager fm = getFragmentManager();
        TimerSettingsFragment tsf = TimerSettingsFragment.newInstance();
        tsf.show(fm, "");
    }
}
