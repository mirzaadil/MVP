package maf.adil.mirza.maf.repository;


import maf.adil.mirza.maf.repository.model.NewsResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Mirza Adil
 * @date 2019-01-12
 * <p>
 * This class contains the Services Interface of the API.
 * All of the attributes in this class shall be static. So, that they can be used from anywhere
 * without even declaring the object of this class.</p>
 */

public interface ServicesInterface {

    @GET("everything?q=bitcoin&apiKey=c01ad71902334a63b9927de5c360505a")
    Observable<NewsResponse> getNewsArticles();
}
