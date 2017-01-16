package de.marco_egger.mvploaderexample.model;

import android.support.annotation.NonNull;

/**
 * @author Marco Egger
 */
public final class Task {

    public static final Task[] TASKS = {new Task("Buy some milk"), new Task("Walk the dog"), new Task("Get the kids!")};

    @NonNull
    private final String name;

    public Task(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
