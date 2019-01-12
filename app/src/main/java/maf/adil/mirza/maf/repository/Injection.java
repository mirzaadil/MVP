package maf.adil.mirza.maf.repository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mirza Adil
 * @date 2019-01-12
 * <p>
 * This class contains the Services Interface Injection .
 * All of the attributes in this class shall be static. So, that they can be used from anywhere
 * without even declaring the object of this class.</p>
 */

public class Injection {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static OkHttpClient okHttpClient;
    private static ServicesInterface nyTimesRestService;
    private static Retrofit retrofitInstance;



    static ServicesInterface provideNYTimesRestService() {
        if (nyTimesRestService == null) {
            nyTimesRestService = getRetrofitInstance().create(ServicesInterface.class);
        }
        return nyTimesRestService;
    }

    static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }

        return okHttpClient;
    }

    static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(Injection.getOkHttpClient()).baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }
}
