package maf.adil.mirza.maf.mvp.presenter;

/**
 *@author Mirza Adil
 *@date 2019-01-12
 * <p>
 * This class contains the MVP Generic Presenter.
 *.</p>
 */
public interface MvpPresenter <V extends MvpView>{

    void attachView(V mvpView);
    void detachView();
}
