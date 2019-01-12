package maf.adil.mirza.maf.repository;



import maf.adil.mirza.maf.repository.model.NewsResponse;
import rx.Observable;

/**
 *@author Mirza Adil
 * @date 2019-01-12.
 * <p>
 * This class contains the Update News Interface .
 * </p>
 */
public interface NewsRepository {
    Observable<NewsResponse> getPopularNews();
}
