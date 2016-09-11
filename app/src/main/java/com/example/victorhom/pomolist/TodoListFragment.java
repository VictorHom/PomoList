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


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends ListFragment {


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

        String[] todos = new String[Todo.myList.length];
        for (int i = 0; i < todos.length; i++) {
            todos[i] = Todo.myList[i].getTodo();
        }
//
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
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

}
