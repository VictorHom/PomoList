package com.example.victorhom.pomolist;

import java.util.Date;

/**
 * Created by victorhom on 9/10/16.
 */
public class Todo {
    private String todo; // the to-do
    private String note; // note about to-do
    private Date date; // date created
    private Date edited;
    private boolean pomo; // if in pomo list

    // for testing
    // goal - add to SQL and have a persistent backend
    public static final Todo[] myList = {
            new Todo("Buy Grocery", "Tomato, Notepad"),
            new Todo("Take Shower", "It\'s been a few days"),
            new Todo("Work on Todo List", "Submit project by 9/23")
    };

    private Todo(String todo, String note) {
        this.todo = todo;
        this.note = note;
    }

    public String getTodo() {
        return todo;
    }

    public String getDescription() {
        return note;
    }


}
