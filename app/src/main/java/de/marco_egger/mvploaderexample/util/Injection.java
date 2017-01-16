package de.marco_egger.mvploaderexample.util;

import android.support.annotation.NonNull;
import de.marco_egger.mvploaderexample.model.source.TasksRepository;
import de.marco_egger.mvploaderexample.model.source.local.TasksMockedDataSource;

/**
 * Enables injection of mock implementations for {@link de.marco_egger.mvploaderexample.model.source.TasksDataSource}
 * at compile time. This is useful for testing, since it allows us to use a fake instance of the class to isolate
 * the dependencies and run a test hermetically.
 *
 * @author Marco Egger
 */
public class Injection {

    @NonNull
    public static TasksRepository provideTasksRepository() {
        return TasksRepository.getInstance(TasksMockedDataSource.getInstance());
    }
}
