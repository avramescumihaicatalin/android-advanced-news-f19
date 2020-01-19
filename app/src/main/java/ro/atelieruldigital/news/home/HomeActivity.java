package ro.atelieruldigital.news.home;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseActivity;
import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.model.NewsAPIRequests;
import ro.atelieruldigital.news.model.WebService.NewsWebService;
import ro.atelieruldigital.news.recycler_view.CustomVerticalAdapter;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {

    ArrayList<ArticleResponse.Article> mArticles;
    ArrayList<String> mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setPreferencesListForTest();
        getDataFromServer();
    }

    private void setPreferencesListForTest() {
        mPreferences = new ArrayList<>();
        mPreferences.add("sport");
        mPreferences.add("fotbal");
        mPreferences.add("masini");
    }

    private void getDataFromServer() {
        Retrofit newsWebServiceRetrofit = NewsWebService.getRetrofitClient();
        NewsAPIRequests newsAPIRequests = newsWebServiceRetrofit.create(NewsAPIRequests.class);

        Call<ArticleResponse> call = newsAPIRequests.queryArticles("apple", "2020-01-12", "2020-01-12",
                "popularity", "534a091354c14911aa44a800e5270924");

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArticleResponse> call, @NotNull Response<ArticleResponse> response) {

                if (response.body() != null) {
                    ArticleResponse articleResponse = response.body();
                    mArticles = articleResponse.getArticles();
                    setRecyclerView();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull Throwable t) {
                Timber.e(t, "Failed to get data:");
                System.out.println("Fail to GET data");

            }
        });
    }

    private void setRecyclerView() {
        RecyclerView verticalRecyclerView = findViewById(R.id.vertical_recycler_view);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(App.getAppContext(), RecyclerView.VERTICAL, false));
        CustomVerticalAdapter customVerticalAdapter = new CustomVerticalAdapter(mArticles, mPreferences);
        verticalRecyclerView.setAdapter(customVerticalAdapter);
    }
}
