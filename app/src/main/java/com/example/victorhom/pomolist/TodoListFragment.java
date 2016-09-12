package com.example.victorhom.pomolist;


import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        updateTodos();
        adapter = new ArrayAdapter<String> (
                inflater.getContext(), android.R.layout.simple_list_item_1, todos
        );
        setListAdapter(adapter); // bind the array adapter to the list view


        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public ArrayAdapter<String> getAdapter() {
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
        FragmentManager fm = getFragmentManager();
        EditTodoFragment editTodoDialogFragment = EditTodoFragment.newInstance(todo, position);
        editTodoDialogFragment.show(fm, "fragment_edit_todo");
    }

    @Override
    public void onResume() {
        updateTodos();
        adapter = new ArrayAdapter<String> (inflater.getContext(), android.R.layout.simple_list_item_1, todos);
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
                Todo.myList.remove(position);
                tlf.onResume();
                return true;
            }
        });
    }

    private void updateTodos() {
        todos = new String[Todo.myList.size()];
        for (int i = 0; i < todos.length; i++) {
            todos[i] = Todo.myList.get(i).getTodo();
        }
    }



}
