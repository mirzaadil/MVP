package maf.adil.mirza.maf.mvp.contract;



import java.util.List;

import maf.adil.mirza.maf.mvp.presenter.MvpPresenter;
import maf.adil.mirza.maf.mvp.presenter.MvpView;
import maf.adil.mirza.maf.repository.model.NewsResponse;

/**
 * @author Mirza Adil.
 * @data 2019-01-12
 * <p>
 * This class contains the Contract Interface.
 * Define functions.</p>
 */
public interface NewsContract {


    interface View extends MvpView {

        void showNewsResults(NewsResponse popularNews);

        void showError(String message);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends MvpPresenter<View> {
        void loadData();
    }
}
