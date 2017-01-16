package de.marco_egger.mvploader.presenter;

import android.content.Context;
import android.content.Loader;
import android.support.annotation.NonNull;

/**
 * @author Marco Egger
 */
public final class PresenterLoader<T extends BasePresenter> extends Loader<T> {

    @NonNull
    private final PresenterFactory<T> factory;
    private T presenter;

    public PresenterLoader(Context context, @NonNull PresenterFactory<T> factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        // If we already own an instance, simply deliver it
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise force a reload
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // Create the presenter using the factory
        presenter = factory.create();

        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        if (presenter != null) {
            presenter.onDestroyed();
            presenter = null;
        }
    }
}
