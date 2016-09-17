package com.example.victorhom.pomolist;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


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


        taskTimePicker = (Spinner) view.findViewById(R.id.task_time);
        breakTimePicker = (Spinner) view.findViewById(R.id.break_time);

        Button saveButton = (Button) getView().findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity me = (MainActivity) getActivity();
                int taskTime = Integer.valueOf(taskTimePicker.getSelectedItem().toString());
                int breakTime = Integer.valueOf(breakTimePicker.getSelectedItem().toString());
                me.getTS().setTaskTimeMinute(taskTime);
                me.getTS().setBreakTimeMinute(breakTime);
                getDialog().dismiss();
            }
        });

        Button exitButton = (Button) getView().findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
            }
        });

        // set the dropdown for selecting the task time
        Integer[] taskTimes = new Integer[25];
        for (int i = 0; i < taskTimes.length; i++) {
            taskTimes[i] = i + 1;
        }
        ArrayAdapter<Integer> adapterT = new ArrayAdapter<Integer>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item, taskTimes);
        taskTimePicker.setAdapter(adapterT);

        // set the dropdown for selecting the break time
        Integer[] breakTimes = new Integer[10];
        for (int i = 0; i < breakTimes.length; i++) {
            breakTimes[i] = i + 1;
        }
        ArrayAdapter<Integer> adapterB = new ArrayAdapter<Integer>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item, breakTimes);
        breakTimePicker.setAdapter(adapterB);

        MainActivity ma = (MainActivity) getActivity();
        taskTimePicker.setSelection(ma.getTS().getTaskTimeMinute() - 1);
        breakTimePicker.setSelection(ma.getTS().getBreakTimeMinute() - 1);

    }

}
