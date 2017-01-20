package de.marco_egger.mvploader;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import de.marco_egger.mvploader.presenter.BasePresenter;
import de.marco_egger.mvploader.presenter.PresenterFactory;
import de.marco_egger.mvploader.presenter.PresenterLoader;

/**
 * Subclass this activity to automatically handle presenter creation and decoupling from the lifecycle events.
 *
 * @author Marco Egger
 */
public abstract class MvpLoaderActivity<P extends BasePresenter> extends Activity {

    private static final int DEFAULT_LOADER_ID = 101;

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(getLoaderId(), null, new android.app.LoaderManager.LoaderCallbacks<P>() {
            @Override
            public android.content.Loader<P> onCreateLoader(int i, Bundle bundle) {
                return new PresenterLoader<>(MvpLoaderActivity.this, getPresenterFactory());
            }

            @Override
            public void onLoadFinished(android.content.Loader<P> loader, P p) {
                MvpLoaderActivity.this.presenter = presenter;
                onPresenterPrepared();
            }

            @Override
            public void onLoaderReset(android.content.Loader<P> loader) {
                presenter = null;
                onPresenterDestroyed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //noinspection unchecked
        presenter.onViewAttached(this);
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
