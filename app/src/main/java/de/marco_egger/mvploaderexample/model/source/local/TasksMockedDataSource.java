package de.marco_egger.mvploaderexample.model.source.local;

import android.support.annotation.NonNull;
import de.marco_egger.mvploaderexample.model.Task;
import de.marco_egger.mvploaderexample.model.source.TasksDataSource;

import java.io.IOException;
import java.util.Random;

/**
 * @author Marco Egger
 */
public class TasksMockedDataSource implements TasksDataSource {

    private static TasksMockedDataSource instance;

    private Random random;

    private TasksMockedDataSource() {
        random = new Random();
    }

    public static TasksMockedDataSource getInstance() {
        if (instance == null) {
            instance = new TasksMockedDataSource();
        }

        return instance;
    }

    @Override
    public void getTasks(@NonNull GetTasksCallback callback) {
        // We're just "mocking" this method with static data or throwing an error (randomly)

        // Fail in one out of 5 cases
        if (random.nextInt(5) == 0) {
            callback.onError(new IOException("Could not read from disk"));
        } else {
            callback.onTasksLoaded(Task.TASKS);
        }
    }
}
