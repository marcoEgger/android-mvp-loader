package de.marco_egger.mvploader.presenter;

import android.support.annotation.NonNull;

/**
 * This is the base interface that should be implemented by every presenter.
 *
 * @author Marco Egger
 */
public interface BasePresenter<V> {

    /**
     * This method is called when the view is (re-)attached. This can be called several times, e.g. on first
     * instantiation of the view or on recreation of a configuration change.
     *
     * @param view the view that got attached to the presenter
     */
    void onViewAttached(@NonNull V view);

    /**
     * This method is called when the view is detached. It should be used to free all references to the view
     * or memory leaks can occur!
     */
    void onViewDetached();

    /**
     * This method is called when the view is destroyed completely. Use this to free up all memory of the
     * presenter.
     */
    void onDestroyed();

}
