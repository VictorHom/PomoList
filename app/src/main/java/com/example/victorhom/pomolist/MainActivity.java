package com.example.victorhom.pomolist;

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
    public void itemClicked(long id) {}

    
    public void onClickAddTodo(View view){
        EditText todoInputForm = (EditText) findViewById(R.id.editText);
        String todo = todoInputForm.getText().toString();
        todoInputForm.setText("");

//        TodoListFragment updatedTodosFrags = new TodoListFragment();
//
//        List<String> updatedTodos = new ArrayList<>(Arrays.asList(updatedTodosFrags.getTodos()));
//        updatedTodos.add(0, todo);
//        updatedTodosFrags.setData(updatedTodos);
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.todolist, updatedTodosFrags);
//
//        ft.replace(R.id.todolist, getFragmentManager().findFragmentById(R.id.todolist));
//        ft.commit();


    }
}
