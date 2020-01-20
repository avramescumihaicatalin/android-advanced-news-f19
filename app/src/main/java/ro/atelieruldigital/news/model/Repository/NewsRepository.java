package ro.atelieruldigital.news.model.Repository;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.model.WebService.NewsAPIRequests;
import ro.atelieruldigital.news.model.WebService.NewsWebService;
import timber.log.Timber;

public class NewsRepository {
    // TODO: Add functionality according to API

    private static NewsRepository newsRepository;


    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsAPIRequests newsAPIRequests;

    public NewsRepository() {
        newsAPIRequests = NewsWebService.cteateService(NewsAPIRequests.class);
    }

    public MutableLiveData<ArticleResponse> getArticlesByCategory(String searchString,
                                                                  String from,
                                                                  String to,
                                                                  String sort,
                                                                  String apiKey) {

        MutableLiveData<ArticleResponse> newsResponseReceived = new MutableLiveData<>();
        newsAPIRequests.getArticlesByCategory(searchString, from, to, sort, apiKey)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        if (response.isSuccessful()) {
                            newsResponseReceived.setValue(response.body());
                            Timber.d("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                            Timber.d(newsResponseReceived.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        newsResponseReceived.setValue(null);
                    }
                });
        return newsResponseReceived;
    }

}
