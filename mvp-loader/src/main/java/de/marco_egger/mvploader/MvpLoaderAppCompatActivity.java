package de.marco_egger.mvploader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import de.marco_egger.mvploader.presenter.BasePresenter;
import de.marco_egger.mvploader.presenter.PresenterFactory;
import de.marco_egger.mvploader.presenter.PresenterSupportLoader;

/**
 * Subclass this activity to automatically handle presenter creation and decoupling from the lifecycle events.
 *
 * @author Marco Egger
 * @see MvpLoaderActivity
 */
public abstract class MvpLoaderAppCompatActivity<P extends BasePresenter<V>, V> extends AppCompatActivity {

    private static final int DEFAULT_LOADER_ID = 101;

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportLoaderManager().initLoader(getLoaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterSupportLoader<>(MvpLoaderAppCompatActivity.this, getPresenterFactory());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P presenter) {
                MvpLoaderAppCompatActivity.this.presenter = presenter;
                onPresenterPrepared();
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                presenter = null;
                onPresenterDestroyed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //noinspection unchecked
        presenter.onViewAttached((V) this);
    }

    @Override
    protected void onPause() {
        presenter.onViewDetached();
        super.onPause();
    }

    /**
     * Instance of {@link PresenterFactory} use to create a presenter when needed. This instance should (must!)
     * not contain {@link android.app.Activity} context reference since it will be keep on rotations.
     *
     * @return the presenter factory that should be used to create new presenters
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    protected int getLoaderId() {
        return DEFAULT_LOADER_ID;
    }

    /**
     * Hook for subclasses that notifies about the presenter before its View is attached.
     * Can be use to initialize the presenter.
     */
    protected void onPresenterPrepared() {
    }

    /**
     * Hook for subclasses before the screen gets destroyed.
     */
    protected void onPresenterDestroyed() {
    }
}
