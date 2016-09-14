package com.example.victorhom.pomolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by victorhom on 9/10/16.
 * A instance contains a string of the action item, a note about that item, a boolean
 * pomo that contains information about whether it is part of the pomodoro list
 * it also contains day, month, year which is the due date information
 * By default, everything is set on instantiation besides the action item
 */
public class Todo {
    private String todo; // the to-do
    private String note; // note about to-do
    private boolean inPomoList; // if in pomo list
    private int dayDue;
    private int monthDue;
    private int yearDue;

    // for testing
    // goal - add to SQL and have a persistent backend
    public static List<Todo> myList = new ArrayList<>(Arrays.asList(
            new Todo("Buy Grocery"),
            new Todo("Take Shower"),
            new Todo("Work on Todo List")
            )
    );

    Todo(String todo) {
        this.todo = todo;
        this.note = "";
        this.inPomoList = false;
        Calendar c = Calendar.getInstance(java.util.TimeZone.getDefault());
        this.dayDue = c.get(Calendar.DAY_OF_MONTH);
        this.monthDue = c.get(Calendar.MONTH);
        this.yearDue = c.get(Calendar.YEAR);
    }

    public String getTodo() {
        return todo;
    }

    public String setTodo(String updatedTodo) {
        this.todo = updatedTodo;
        return this.todo;
    }

    public String getNote() { return note; }

    public String setNote(String note) {
        this.note = note;
        return note;
    }

    public boolean getPomo(){ return inPomoList; }

    public boolean setPomo(boolean b) {
        this.inPomoList = b;
        return b;
    }

    public int getDay() { return dayDue; }

    public int setDay(int day) {
        this.dayDue = day;
        return day;
    }

    public int getMonth() { return monthDue; }

    public int setMonth(int month) {
        this.monthDue = month;
        return month;
    }

    public int getYear() { return yearDue; }

    public int setYear(int year) {
        this.yearDue = year;
        return year;
    }

}
