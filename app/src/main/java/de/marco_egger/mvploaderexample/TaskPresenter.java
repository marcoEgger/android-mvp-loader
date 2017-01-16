package de.marco_egger.mvploaderexample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import de.marco_egger.mvploaderexample.model.Task;
import de.marco_egger.mvploaderexample.model.source.TasksDataSource;
import de.marco_egger.mvploaderexample.model.source.TasksRepository;
import de.marco_egger.mvploaderexample.tasks.TasksContract;

import java.util.Arrays;
import java.util.List;

/**
 * @author Marco Egger
 */
public class TaskPresenter implements TasksContract.Presenter {

    @NonNull
    private final TasksRepository tasksRepository;

    @Nullable
    private TasksContract.View view;

    @Nullable
    private List<Task> missedTasks = null;
    @Nullable
    private String missedError = null;

    public TaskPresenter(@NonNull TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public void onViewAttached(@NonNull TasksContract.View view) {
        this.view = view;

        // Check if loading the tasks finished while the view was not attached
        if (missedTasks != null) {
            this.view.showTasks(missedTasks);
            missedTasks = null;
        }

        if (missedError != null) {
            this.view.showErrorMessage(missedError);
            missedError = null;
        }
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void loadTasks() {
        // In a normal application you should call this asynchronously
        // This library fits perfect with RxJava/RxAndroid to do that!
        tasksRepository.getTasks(new TasksDataSource.GetTasksCallback() {
            @Override
            public void onTasksLoaded(Task[] tasks) {
                if (view != null) {
                    view.showTasks(Arrays.asList(tasks));
                } else {
                    missedTasks = Arrays.asList(tasks);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (view != null) {
                    view.showErrorMessage(throwable.getLocalizedMessage());
                } else {
                    missedError = throwable.getLocalizedMessage();
                }
            }
        });
    }
}
