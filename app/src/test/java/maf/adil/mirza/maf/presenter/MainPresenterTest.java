package maf.adil.mirza.maf.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import maf.adil.mirza.maf.mvp.contract.NewsContract;
import maf.adil.mirza.maf.mvp.presenter.BasePresenter;
import maf.adil.mirza.maf.mvp.presenter.NewsPresenter;
import maf.adil.mirza.maf.repository.NewsRepository;
import maf.adil.mirza.maf.repository.model.ArticlesItem;
import maf.adil.mirza.maf.repository.model.NewsResponse;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;


/**
 * @author Mirza Adil
 * @date 2019-01-13
 * This class create for Unit testing.
 */

public class MainPresenterTest {
    @Mock
    private
    NewsRepository userRepository;
    @Mock
    private
    NewsContract.View view;

    private NewsPresenter userSearchPresenter;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        userSearchPresenter = new NewsPresenter(userRepository, Schedulers.immediate(), Schedulers.immediate());
        userSearchPresenter.attachView(view);
    }

    @Test
    public void ensureNewsApiReturnsResults() {
        NewsResponse newsResponse = getNewsList();
        when(userRepository.getPopularNews()).thenReturn(Observable.just(newsResponse));
        userSearchPresenter.loadData();
        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view, never()).showError(anyString());
    }

    @Test(expected = BasePresenter.MvpViewNotAttachedException.class)
    public void search_NotAttached_ThrowsMvpException() {
        userSearchPresenter.detachView();
        userSearchPresenter.loadData();
        verify(view, never()).showLoading();
        verify(view, never()).showNewsResults(any());
    }

    @Test
    public void ensureNYTimesApiShowErrorMessage() {
        String errorMsg = "No internet";
        when(userRepository.getPopularNews()).thenReturn(Observable.error(new IOException(errorMsg)));
        userSearchPresenter.loadData();
        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view, never()).showNewsResults(any());
        verify(view).showError(errorMsg);
    }

    private NewsResponse getNewsList() {
        List<ArticlesItem> articlesItems = new ArrayList<>();
        articlesItems.add(news1FullDetails());
        //  PopularNewsResponse popularNewsResponse  = new PopularNewsResponse("OK","Copyright (c) 2018 The New York Times Company. All Rights Reserved.",1704,popularNews);

        return new NewsResponse("OK", 7491, articlesItems);
    }

    private ArticlesItem news1FullDetails() {
        return new ArticlesItem("2018-12-31T18:56:47Z",
                "Kate Clark",
                "https://techcrunch.com/wp-content/uploads/2018/12/GettyImages-1064373142.jpg?w=600",
                "Venture capitalists remain bullish on Bitcoin and its underlying technology despite sinking crypto prices.",
                null,
                "NYSE operator’s crypto project Bakkt brings in $182M",
                "http://techcrunch.com/2018/12/31/nyse-operators-crypto-project-bakkt-brings-in-182m/",
                "The Intercontinental Exchange’s (ICE) cryptocurrency project Bakkt celebrated New Year’s Eve with the announcement of a $182.5 million equity round from a slew of notable institutional investors. ICE, the operator of several global exchanges, including the Ne… [+3472 chars]");
    }


}
