package maf.adil.mirza.maf.repository;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import maf.adil.mirza.maf.repository.model.ArticlesItem;
import maf.adil.mirza.maf.repository.model.NewsResponse;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mirza Adil
 * @date 2019-01-13
 * This class create for Unit testing.
 */

public class NewsRepositoryImplTest {
    @Mock
    private ServicesInterface newsRestService;

    private NewsRepository newsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        newsRepository = new NewsRepositoryImpl(newsRestService);
    }
    @Test
    public void news_200OkResponse_InvokesCorrectApiCalls() {
        //Given
        when(newsRestService.getNewsArticles()).thenReturn(Observable.just(getNewsList()));


        //When
        TestSubscriber<NewsResponse> subscriber = new TestSubscriber<>();
        newsRestService.getNewsArticles().subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        verify(newsRestService).getNewsArticles();

    }

    @Test
    public void ensureNewsAPiOtherHttpErrorsTerminatedWithError() {
        //Given
        when(newsRestService.getNewsArticles()).thenReturn(get403ForbiddenError());
        //When
        TestSubscriber<NewsResponse> subscriber = new TestSubscriber<>();
        newsRepository.getPopularNews().subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertError(HttpException.class);
        verify(newsRestService).getNewsArticles();
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

    private Observable<NewsResponse> get403ForbiddenError() {
        return Observable.error(new HttpException(
                Response.error(403, ResponseBody.create(MediaType.parse("application/json"), "Forbidden"))));

    }
}