package de.marco_egger.mvploaderexample.tasks;

import de.marco_egger.mvploader.presenter.BasePresenter;
import de.marco_egger.mvploaderexample.model.Task;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter for tasks.
 *
 * @author Marco Egger
 */
public interface TasksContract {

    interface View {

        void showTasks(List<Task> tasks);

        void showErrorMessage(String string);

    }

    interface Presenter extends BasePresenter<View> {

        void loadTasks();

    }
}
