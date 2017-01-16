package de.marco_egger.mvploaderexample.model.source;

import android.support.annotation.NonNull;

/**
 * @author Marco Egger
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository instance;

    @NonNull
    private final TasksDataSource dataSource;

    // Here you could include more data sources for e.g. remote access

    private TasksRepository(@NonNull TasksDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    public static TasksRepository getInstance(TasksDataSource tasksDataSource) {
        if (instance == null) {
            instance = new TasksRepository(tasksDataSource);
        }

        return instance;
    }

    @Override
    public void getTasks(@NonNull GetTasksCallback callback) {
        // Use the appropriate data source for your application (e.g. previously cached, disk, network)
        dataSource.getTasks(callback);
    }
}
