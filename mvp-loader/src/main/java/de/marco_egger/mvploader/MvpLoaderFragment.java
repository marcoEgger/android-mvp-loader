package de.marco_egger.mvploader;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import de.marco_egger.mvploader.presenter.BasePresenter;
import de.marco_egger.mvploader.presenter.PresenterFactory;
import de.marco_egger.mvploader.presenter.PresenterLoader;

/**
 * @author Marco Egger
 */
public abstract class MvpLoaderFragment<P extends BasePresenter<V>, V> extends Fragment {

    private static final int DEFAULT_LOADER_ID = 101;

    protected P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(getLoaderId(), null, new android.app.LoaderManager.LoaderCallbacks<P>() {
            @Override
            public android.content.Loader<P> onCreateLoader(int i, Bundle bundle) {
                return new PresenterLoader<>(getActivity(), getPresenterFactory());
            }

            @Override
            public void onLoadFinished(android.content.Loader<P> loader, P p) {

            }

            @Override
            public void onLoaderReset(android.content.Loader<P> loader) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        //noinspection unchecked
        presenter.onViewAttached((V) this);
    }

    @Override
    public void onPause() {
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
