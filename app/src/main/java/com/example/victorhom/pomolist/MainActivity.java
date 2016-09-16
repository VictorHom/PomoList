package com.example.victorhom.pomolist;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodoListFragment.TodoListListener{
    TimerSettings ts =  TimerSettings.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TimerSettings.getInstance();
        setContentView(R.layout.activity_main);
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


    // strange - the hardcoded todolist are strange on scroll, they don't entirely scroll
    public void onClickAddTodo(View view){
        EditText todoInputForm = (EditText) findViewById(R.id.editText);
        String todo = todoInputForm.getText().toString();
        if (todo.length() > 0) {
            Todo.myList.add(0, new Todo(todo));
        }
        todoInputForm.setText("");
        TodoListFragment todolist = (TodoListFragment) getFragmentManager().findFragmentById(R.id.todolist);
        todolist.onResume();
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
    }

    public void onClickSetTimerSettings(View view) {
        FragmentManager fm = getFragmentManager();
        TimerSettingsFragment tsf = TimerSettingsFragment.newInstance();
        tsf.show(fm, "");
    }
}
