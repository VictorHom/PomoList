package com.example.victorhom.pomolist;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends ListFragment {
    private ArrayAdapter<String> adapter;
    private String[] todos;
    private LayoutInflater inflater;

    static interface TodoListListener {
        void itemClicked(long id);
    }
    private TodoListListener listener;

    public TodoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        todos = new String[Todo.myList.size()];
        for (int i = 0; i < todos.length; i++) {
            todos[i] = Todo.myList.get(i).getTodo();
        }

//
        adapter = new ArrayAdapter<String> (
                inflater.getContext(), android.R.layout.simple_list_item_1, todos
        );
        setListAdapter(adapter); // bind the array adapter to the list view

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (TodoListListener) context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }

    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    public String[] getTodos() {
        return todos;
    }

    public String[] setData(List<String> input) {
        String[] arr = input.toArray(new String[input.size()]);
        return arr;
    }


    @Override
    public void onResume() {
        // right now for testing - I am using myList to add on todos
        todos = new String[Todo.myList.size()];
        for (int i = 0; i < todos.length; i++) {
            todos[i] = Todo.myList.get(i).getTodo();
        }

        adapter = new ArrayAdapter<String> (
                inflater.getContext(), android.R.layout.simple_list_item_1, todos
        );
        adapter.notifyDataSetChanged();
        setListAdapter(adapter); // bind the array adapter to the list view
        super.onResume();

    }

}
