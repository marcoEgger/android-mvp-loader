package de.marco_egger.mvploaderexample.model.source;

import android.support.annotation.NonNull;
import de.marco_egger.mvploaderexample.model.Task;

/**
 * @author Marco Egger
 */
public interface TasksDataSource {

    interface GetTasksCallback {

        void onTasksLoaded(Task[] tasks);

        void onError(Throwable throwable);

    }

    void getTasks(@NonNull GetTasksCallback callback);
}
