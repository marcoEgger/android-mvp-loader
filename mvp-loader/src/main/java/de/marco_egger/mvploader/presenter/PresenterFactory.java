package de.marco_egger.mvploader.presenter;

/**
 * This is an interface to prevent subclassing the {@link PresenterLoader} for every presenter.
 *
 * @author Marco Egger
 */
public interface PresenterFactory<T extends BasePresenter> {

    T create();
}
