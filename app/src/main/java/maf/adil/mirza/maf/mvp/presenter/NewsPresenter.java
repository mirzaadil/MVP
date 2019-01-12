package maf.adil.mirza.maf.mvp.presenter;


import maf.adil.mirza.maf.mvp.contract.NewsContract;
import maf.adil.mirza.maf.repository.NewsRepository;
import maf.adil.mirza.maf.repository.model.NewsResponse;
import rx.Scheduler;
import rx.Subscriber;

/**
 * @author Mirza Adil
 * @date 2019-01-12
 * <p>
 * This class contains NewsPresenter .
 * In this call call API.</p>
 */

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {
    private final Scheduler mainScheduler, ioScheduler;
    private NewsRepository newsRepository;

    public NewsPresenter(NewsRepository newsRepository, Scheduler ioSchedulars, Scheduler mainSchedular) {
        this.mainScheduler = mainSchedular;
        this.ioScheduler = ioSchedulars;
        this.newsRepository = newsRepository;
    }


    // Defining the observable for News API Call.

    @Override
    public void loadData() {
        checkViewAttached();
        getView().showLoading();
        addSubscription(newsRepository.getPopularNews().subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribe(new Subscriber<NewsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsResponse popularNewsResponse) {
                        getView().hideLoading();
                        getView().showNewsResults(popularNewsResponse);

                    }
                }));

    }
}
