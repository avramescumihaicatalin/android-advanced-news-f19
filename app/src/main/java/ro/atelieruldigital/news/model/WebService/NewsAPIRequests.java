package ro.atelieruldigital.news.model.WebService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.atelieruldigital.news.model.ArticleResponse;

public interface NewsAPIRequests {
    // TODO: Add functionality according to API
    // TODO: To be used as Retrofit's API

    @GET("v2/everything")
    Call<ArticleResponse> getArticlesByCategory(@Query("q") String searchString,
                                                @Query("from") String from,
                                                @Query("to") String to,
                                                @Query("sortBy") String sort,
                                                @Query("apiKey") String apiKey);

    @GET("v2/top-headlines")
    Call<ArticleResponse> getLatestNews(@Query("sources") String searchString,
                                      @Query("apiKey") String apiKey);
}

