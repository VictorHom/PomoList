package com.example.victorhom.pomolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by victorhom on 9/10/16.
 */
public class Todo {
    private String todo; // the to-do
    private String note; // note about to-do
    private boolean pomo; // if in pomo list
    private int day;
    private int month;
    private int year;

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
        this.pomo = false;
        this.day = Calendar.DAY_OF_MONTH;
        this.month = Calendar.MONTH;
        this.year = Calendar.YEAR;
    }

    public String getTodo() {
        return todo;
    }

    public String setTodo(String updatedTodo) {
        this.todo = updatedTodo;
        return this.todo;
    }

    public String setNote(String note) {
        this.note = note;
        return note;
    }

    public boolean togglePomo() {
        this.pomo = !this.pomo;
        return this.pomo;
    }

    public int setDay(int day) {
        this.day = day;
        return day;
    }

    public int setMonth(int month) {
        this.month = month;
        return month;
    }

    public int setYear(int year) {
        this.year = year;
        return year;
    }

    public String getDescription() {
        return note;
    }


}
