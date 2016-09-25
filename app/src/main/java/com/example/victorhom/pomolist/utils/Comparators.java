package com.example.victorhom.pomolist.utils;

import com.example.victorhom.pomolist.models.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by victorhom on 9/25/16.
 */
public class Comparators {
    private static Comparators instance = null;

    protected Comparators() {

    }
    public static Comparators getInstance() {
        if (instance == null){
            instance = new Comparators();
        }
        return instance;
    }

    public static Comparator<Todo> getCompByID() {
        Comparator comp = new Comparator<Todo>() {
            @Override
            public int compare(Todo a, Todo b) {
                return (int) (a.getId() - b.getId());
            }
        };
        return comp;
    }

    public static Comparator<Todo> getCompByIDReverse() {
        Comparator comp = new Comparator<Todo>() {
            @Override
            public int compare(Todo a, Todo b) {
                return (int) (b.getId() - a.getId());
            }
        };
        return comp;
    }

    public static Comparator<Todo> getCompByPriority() {
        Comparator comp = new Comparator<Todo>() {
            @Override
            public int compare(Todo a, Todo b) {
                return a.getPriorityLevel() - b.getPriorityLevel();
            }
        };
        return comp;
    }

    public static Comparator<Todo> getCompByDate() {
        Comparator comp = new Comparator<Todo>() {
            @Override
            public int compare(Todo a, Todo b) {
                String expectedPattern = "MM/dd/yyyy";
                SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
                String userInputA = a.getDay() + "/" + a.getMonth() + "/" + a.getYear();
                String userInputB = b.getDay() + "/" + b.getMonth() + "/" + b.getYear();
                try {
                    Date dateA = formatter.parse(userInputA);
                    Date dateB = formatter.parse(userInputB);
                    return dateA.compareTo(dateB);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 1;
                }
            }
        };
        return comp;
    }
}
