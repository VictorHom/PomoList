package com.example.victorhom.pomolist;


import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTodoFragment extends DialogFragment {
    private EditText todoEditText;
    private EditText noteEditText;
    private ToggleButton inPomodoroList;
    private int day;
    private int month;
    private int year;
    DatePicker dueDate;

    private int positionInList;

    public EditTodoFragment() {
        // Required empty public constructor
    }
    public static EditTodoFragment newInstance(Todo todo, int position) {
        EditTodoFragment frag = new EditTodoFragment();
        Bundle args = new Bundle();
        args.putString("todo", todo.getTodo());
        args.putString("note", todo.getNote());
        args.putString("pomo", String.valueOf(todo.getPomo()));
        args.putString("day", String.valueOf(todo.getDay()));
        args.putString("month", String.valueOf(todo.getMonth()));
        args.putString("year", String.valueOf(todo.getYear()));
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
        todoEditText = (EditText) view.findViewById(R.id.edit_todo);
        noteEditText = (EditText) view.findViewById(R.id.todo_note);
        inPomodoroList = (ToggleButton) view.findViewById(R.id.toggle_pomodoro);
        dueDate = (DatePicker) view.findViewById(R.id.due_date_picker);



        // getting all the values from the bundle
        String todo = this.getArguments().getString("todo","Edit");
        String note = this.getArguments().getString("note","");
        boolean pomo = Boolean.valueOf(this.getArguments().getString("pomo"));

        day = Integer.valueOf(this.getArguments().getString("day"));
        month = Integer.valueOf(this.getArguments().getString("month"));
        year = Integer.valueOf(this.getArguments().getString("year"));
        
        // set values
        final String position = getArguments().getString("position");
        todoEditText.setText(todo);
        noteEditText.setText(note);
        inPomodoroList.setChecked(pomo);
        dueDate.updateDate(year, month, day);
        dueDate.refreshDrawableState();


        // Show soft keyboard automatically and request focus to field
        todoEditText.requestFocus();
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
                String updatedTodo = todoEditText.getText().toString();
                String updatedNote = noteEditText.getText().toString();
                if (updatedTodo.length() > 0) {
                    TodoListFragment todolist = (TodoListFragment) getFragmentManager().findFragmentById(R.id.todolist);
                    Todo updateTodo = Todo.myList.get(Integer.valueOf(position));

                    // set updated data in todo
                    updateTodo.setTodo(updatedTodo);
                    updateTodo.setNote(updatedNote);
                    updateTodo.setPomo(inPomodoroList.isChecked());
                    updateTodo.setDay(dueDate.getDayOfMonth());
                    updateTodo.setMonth(dueDate.getMonth());
                    updateTodo.setYear(dueDate.getYear());
                    updateTodo.save();
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
