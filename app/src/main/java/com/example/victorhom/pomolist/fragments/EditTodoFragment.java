package com.example.victorhom.pomolist.fragments;


import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.example.victorhom.pomolist.R;
import com.example.victorhom.pomolist.models.Todo;


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
    private DatePicker dueDate;
    private Spinner priorityPicker;
    private ImageButton exitButton;
    private ImageButton saveButton;

    public EditTodoFragment() {
        // Required empty public constructor
    }
    // return frag with task item data;
    public static EditTodoFragment newInstance(Todo todo, int position) {
        EditTodoFragment frag = new EditTodoFragment();
        Bundle args = new Bundle();
        args.putString("todo", todo.getTodo());
        args.putString("note", todo.getNote());
        args.putString("pomo", String.valueOf(todo.getPomo()));
        args.putString("day", String.valueOf(todo.getDay()));
        args.putString("month", String.valueOf(todo.getMonth()));
        args.putString("year", String.valueOf(todo.getYear()));
        args.putString("priority", String.valueOf(todo.getPriorityLevel()));
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
        priorityPicker = (Spinner) view.findViewById(R.id.priority_picker);
        day = Integer.valueOf(this.getArguments().getString("day"));
        month = Integer.valueOf(this.getArguments().getString("month"));
        year = Integer.valueOf(this.getArguments().getString("year"));
        dueDate = (DatePicker) view.findViewById(R.id.due_date_picker);
        inPomodoroList = (ToggleButton) view.findViewById(R.id.toggle_pomodoro);
        saveButton = (ImageButton) view.findViewById(R.id.saveEditTodobutton);
        exitButton = (ImageButton) view.findViewById(R.id.cancelEditTodobutton);

        setTodoTextOnScreen();
        setTodoNoteOnScreen();
        setCalendarOnScreen();
        setPriorityPickerOnScreen();
        setPomodoroToggleOnScreen();
        setExitButton();
        setSaveButton();

        // Show soft keyboard automatically and request focus to field
        todoEditText.requestFocus();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }

    private void setTodoTextOnScreen() {
        String todo = this.getArguments().getString("todo","Edit");
        todoEditText.setText(todo);
    }

    private void setTodoNoteOnScreen() {
        String note = this.getArguments().getString("note","");
        noteEditText.setText(note);
    }

    private void setCalendarOnScreen() {
        if (dueDate != null) {
            dueDate.updateDate(year, month, day);
            dueDate.refreshDrawableState();
        }
    }

    private void setPriorityPickerOnScreen() {
        int priorityLevel = Integer.valueOf(this.getArguments().getString("priority"));
        Integer[] priorityLevels = new Integer[3];
        for (int i = 0; i < priorityLevels.length; i++) {
            priorityLevels[i] = i + 1;
        }
        ArrayAdapter<Integer> adapterB = new ArrayAdapter<Integer>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item, priorityLevels);
        adapterB.setDropDownViewResource(R.layout.spinner_text);
        if (priorityPicker != null) {
            priorityPicker.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            priorityPicker.setAdapter(adapterB);
            priorityPicker.setSelection(priorityLevel - 1);
        }
    }

    private void setPomodoroToggleOnScreen() {
        boolean pomo = Boolean.valueOf(this.getArguments().getString("pomo"));
        inPomodoroList.setChecked(pomo);
    }

    private void setExitButton() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        };
        exitButton.setOnClickListener(listener);
    }

    private void setSaveButton() {
        final String position = getArguments().getString("position");
        View.OnClickListener listener = new View.OnClickListener() {
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

                    if (dueDate != null) {
                        updateTodo.setDay(dueDate.getDayOfMonth());
                        updateTodo.setMonth(dueDate.getMonth());
                        updateTodo.setYear(dueDate.getYear());
                    }

                    if (priorityPicker != null) {
                        updateTodo.setPriorityLevel(Integer.valueOf(priorityPicker.getSelectedItem().toString()));
                    }
                    updateTodo.save();
                    todolist.onResume();
                }
                getDialog().cancel();
            }
        };
        saveButton.setOnClickListener(listener);
    }





}
