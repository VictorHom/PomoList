package com.example.victorhom.pomolist.fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.victorhom.pomolist.R;
import com.example.victorhom.pomolist.activities.MainActivity;
import com.example.victorhom.pomolist.models.TimerSettings;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimerSettingsFragment extends DialogFragment {
    private Spinner taskTimePicker;
    private Spinner breakTimePicker;

    public TimerSettingsFragment() {
        // Required empty public constructor
    }

    public static TimerSettingsFragment newInstance() {
        TimerSettingsFragment frag = new TimerSettingsFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_timer_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // hide the top header bar in this fragment
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setSpinner(view, (MainActivity) getActivity());
        setSaveButton();
        setExitButton();
    }

    private void setSaveButton() {
        Button saveButton = (Button) getView().findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity me = (MainActivity) getActivity();
                int taskTime = Integer.valueOf(taskTimePicker.getSelectedItem().toString());
                int breakTime = Integer.valueOf(breakTimePicker.getSelectedItem().toString());
                TimerSettings.setTaskTimeMinute(taskTime);
                me.getTS().setBreakTimeMinute(breakTime);
                getDialog().dismiss();
            }
        });
    }

    private void setExitButton() {
        Button exitButton = (Button) getView().findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
            }
        });
    }

    private void setSpinner(View view, MainActivity ma) {
        setBreakTimeSpinner(view, ma);
        setTaskTimeSpinner(view, ma);
    }

    private void setBreakTimeSpinner(View view, MainActivity ma) {
        breakTimePicker = (Spinner) view.findViewById(R.id.break_time);
        // set the dropdown for selecting the break time
        Integer[] breakTimes = new Integer[10];
        for (int i = 0; i < breakTimes.length; i++) {
            breakTimes[i] = i + 1;
        }
        ArrayAdapter<Integer> adapterB = new ArrayAdapter<Integer>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item, breakTimes);
        adapterB.setDropDownViewResource(R.layout.spinner_text);
        breakTimePicker.setAdapter(adapterB);
        breakTimePicker.setSelection(ma.getTS().getBreakTimeMinute() - 1);
    }

    private void setTaskTimeSpinner(View view, MainActivity ma) {
        taskTimePicker = (Spinner) view.findViewById(R.id.task_time);
        // set the dropdown for selecting the task time
        Integer[] taskTimes = new Integer[25];
        for (int i = 0; i < taskTimes.length; i++) {
            taskTimes[i] = i + 1;
        }
        ArrayAdapter<Integer> adapterT = new ArrayAdapter<Integer>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item, taskTimes);
        adapterT.setDropDownViewResource(R.layout.spinner_text);
        taskTimePicker.setAdapter(adapterT);
        taskTimePicker.setSelection(ma.getTS().getTaskTimeMinute() - 1);
    }
}
