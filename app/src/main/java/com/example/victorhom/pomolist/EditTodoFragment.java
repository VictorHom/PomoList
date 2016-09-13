package com.example.victorhom.pomolist;


import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTodoFragment extends DialogFragment {
    private EditText mEditText;
    private int positionInList;

    public EditTodoFragment() {
        // Required empty public constructor
    }
    public static EditTodoFragment newInstance(String todo, int position) {
        EditTodoFragment frag = new EditTodoFragment();
        Bundle args = new Bundle();
        args.putString("todo", todo);
        args.putString("position", String.valueOf(position));
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_todo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.edit_todo);
        // Fetch arguments from bundle and set title
        String todo = this.getArguments().getString("todo","Edit");
        final String position = getArguments().getString("position");


        getDialog().setTitle("Pomodoro this");
        mEditText.setText(todo);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // need to set update when someone hits save and there is text
        // should be doable given that I have the view and the index
        // also be able to set up todos with more information
        // like when you added, edited, if in the pomo list


        // SAVE button
        ImageButton sb = (ImageButton) view.findViewById(R.id.saveEditTodobutton);
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTodo = mEditText.getText().toString();
                if (updatedTodo.length() > 0) {
                    TodoListFragment todolist = (TodoListFragment) getFragmentManager().findFragmentById(R.id.todolist);
                    Todo.myList.get(Integer.valueOf(position)).setTodo(updatedTodo);
                    todolist.onResume();
                }
                getDialog().cancel();
            }
        });

        // EXIT button
        ImageButton eb = (ImageButton) view.findViewById(R.id.cancelEditTodobutton);
        eb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });

    }



}
