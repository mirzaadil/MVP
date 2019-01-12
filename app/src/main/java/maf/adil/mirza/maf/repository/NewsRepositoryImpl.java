package maf.adil.mirza.maf.repository;



import maf.adil.mirza.maf.repository.model.NewsResponse;
import rx.Observable;




public class NewsRepositoryImpl implements NewsRepository {

    private ServicesInterface newsRestService ;

    public NewsRepositoryImpl(ServicesInterface newsRestService) {
        this.newsRestService = newsRestService;
    }

    @Override
    public Observable<NewsResponse> getPopularNews() {
        return  newsRestService.getNewsArticles();
    }
}
