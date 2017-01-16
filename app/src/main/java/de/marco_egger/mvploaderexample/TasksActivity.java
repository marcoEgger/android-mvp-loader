package de.marco_egger.mvploaderexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import de.marco_egger.mvploader.MvpLoaderAppCompatActivity;
import de.marco_egger.mvploader.presenter.PresenterFactory;
import de.marco_egger.mvploaderexample.model.Task;
import de.marco_egger.mvploaderexample.tasks.TasksContract;
import de.marco_egger.mvploaderexample.util.Injection;

import java.util.List;

public class TasksActivity extends MvpLoaderAppCompatActivity<TaskPresenter, TasksContract.View>
        implements TasksContract.View {

    private TasksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new TasksAdapter();
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    @Override
    public void showTasks(List<Task> tasks) {
        adapter.replaceTasks(tasks);
    }

    @Override
    public void showErrorMessage(String string) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(string)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @NonNull
    @Override
    protected PresenterFactory<TaskPresenter> getPresenterFactory() {
        return new PresenterFactory<TaskPresenter>() {
            @Override
            public TaskPresenter create() {
                // Inject the correct repository, this is need for better testing ability
                return new TaskPresenter(Injection.provideTasksRepository());
            }
        };
    }

    @Override
    protected void onPresenterPrepared() {
        super.onPresenterPrepared();

        // Load the tasks every time this fragment is (re-)created and presenter is attached
        presenter.loadTasks();
    }
}
