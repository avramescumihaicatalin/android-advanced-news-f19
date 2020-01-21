package ro.atelieruldigital.news.model.WebService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.atelieruldigital.news.model.ArticleResponse;

public class NewsWebService {
    // TODO: Add functionality according to API

    // TODO: Load Token
    static final String API_KEY = "4b3b375b6f9e462b8513e1471c5428b9";
    private static final String BASE_URL="https://newsapi.org/";
    private NewsWebService newsApiService;
    public static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        // TODO: Initialize newsApiService with Retrofit
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return retrofit;
    }

    public Call<ArticleResponse> queryArticles(String searchString, String from,
                                               String to, String sort, String apiKey) {
        return newsApiService.queryArticles(searchString, from, to , sort, apiKey);
    }
}

