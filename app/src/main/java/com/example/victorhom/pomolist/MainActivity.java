package com.example.victorhom.pomolist;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements TodoListFragment.TodoListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
