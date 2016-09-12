package com.example.victorhom.pomolist;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTodoFragment extends DialogFragment {
    private EditText mEditText;

    public EditTodoFragment() {
        // Required empty public constructor
    }
    public static EditTodoFragment newInstance(String title, String todo, String random, int position) {
        EditTodoFragment frag = new EditTodoFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("todo", todo);
        args.putString("random", random);
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
        String title = this.getArguments().getString("title", "Pomodoro this");
        String todo = this.getArguments().getString("todo","Edit");
        String random = getArguments().getString("random", "also random");
        String position = getArguments().getString("position");


        getDialog().setTitle(title);
        mEditText.setText(todo+random+position);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


}
