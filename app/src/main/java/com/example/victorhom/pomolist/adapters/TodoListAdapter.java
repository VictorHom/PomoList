package com.example.victorhom.pomolist.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.victorhom.pomolist.R;
import com.example.victorhom.pomolist.models.Todo;

import java.util.ArrayList;

/**
 * Created by victorhom on 9/17/16.
 */
public class TodoListAdapter extends ArrayAdapter<Todo> {

    private ArrayList<Todo> todos;
    private LayoutInflater inflater = null;

    public TodoListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public TodoListAdapter(Context context, int resource, ArrayList<Todo> todos) {
        super(context, resource, todos);
        this.todos = todos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            v = inflater.inflate(R.layout.todo_row, null);
        }
        TextView todoText = (TextView) v.findViewById(R.id.todo_text);
        TextView date = (TextView) v.findViewById(R.id.due_date);
        TextView tomato =  (TextView) v.findViewById(R.id.tomato);
        TextView priorityText = (TextView) v.findViewById(R.id.priority);
        Todo todo = (Todo) todos.get(position);


        if (todo != null && v != null) {
            todoText.setText(todo.getTodo().toString());
            date.setText(todos.get(position).getMonth()+ "/" + todos.get(position).getDay()+ "/" + todos.get(position).getYear());
            if (todos.get(position).getPomo()) {
                tomato.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.todo_pomo_icon, 0);
            }
            if (todo.getPriorityLevel() == 1) {
                priorityText.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.tomato));
            } else if (todo.getPriorityLevel() == 2) {
                priorityText.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yellow));
            } else {
                priorityText.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
            }
        }

        return v;
    }

}
