package com.example.victorhom.pomolist.adapters;

import android.content.Context;
import android.graphics.Color;
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
        View v= super.getView(position, convertView, parent);
        TextView text = (TextView) v.findViewById (android.R.id.text1);
        text.setText(todos.get(position).getTodo());

        // priority level will adjust the text color
        if (todos.get(position).getPriorityLevel() == 1) {
            v.setBackgroundColor(Color.parseColor("#EA3712"));
            v.getBackground().setAlpha(51);
        }

        if (todos.get(position).getPomo()) {
            text.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.pomolist_launcher, 0);
        }


        return v;
    }

}
