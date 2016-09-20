package com.example.victorhom.pomolist.fragments;


import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.victorhom.pomolist.R;
import com.example.victorhom.pomolist.models.Todo;
import com.example.victorhom.pomolist.adapters.TodoListAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends ListFragment {
    private TodoListAdapter adapter;
    private String[] todos;
    private int[] todosColor;
    private LayoutInflater inflater;

    public static interface TodoListListener {
        void itemClicked(long id);
    }
    private TodoListListener listener;

    public TodoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;

        TodoListFragment yourListView = (TodoListFragment) (TodoListFragment) getFragmentManager().findFragmentById(R.id.todolist);
        ////
        adapter = new TodoListAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, (ArrayList<Todo>) Todo.myList);
        yourListView.setListAdapter(adapter);

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public TodoListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (TodoListListener) context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showEditDialog(l, v, position, id);
    }

    // this is the modal to edit the todo
    private void showEditDialog(ListView l, View v, int position, long id) {
        String todo = l.getAdapter().getItem(position).toString();
        Todo todoObject = Todo.myList.get(position);
        FragmentManager fm = getFragmentManager();
        EditTodoFragment editTodoDialogFragment = EditTodoFragment.newInstance(todoObject, position);
        editTodoDialogFragment.show(fm, "fragment_edit_todo");
    }

    @Override
    public void onResume() {
        adapter = new TodoListAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, (ArrayList<Todo>) Todo.myList);
        adapter.notifyDataSetChanged();
        setListAdapter(adapter); // bind the array adapter to the list view
        super.onResume();

    }

    // goal - open up a quick action for options to edit, add to pomo?, etc
    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        final TodoListFragment tlf = this;

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Todo.myList.get(position).delete();
            Todo.myList.remove(position);
            tlf.onResume();
            return true;
            }
        });
    }

}
