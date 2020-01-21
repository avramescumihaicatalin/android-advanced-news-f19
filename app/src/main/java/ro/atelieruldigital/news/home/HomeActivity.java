package ro.atelieruldigital.news.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseActivity;
import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.recycler_view.CustomHorizontalAdapter;
import ro.atelieruldigital.news.recycler_view.CustomVerticalAdapter;
import ro.atelieruldigital.news.utils.PrefUtils;
import ro.atelieruldigital.news.view_model.NewsViewModel;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {

    ArrayList<ArticleResponse> articleResponses = new ArrayList<>();
    ArrayList<String> mPreferences;

    RecyclerView verticalRecyclerView;
    CustomVerticalAdapter customVerticalAdapter;
    CustomHorizontalAdapter customHorizontalAdapter;
    NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setPreferencesListForTest();
        setRecyclerView();
//        getDataFromServer();
    }

    private void setPreferencesListForTest() {
        mPreferences = new ArrayList<>();
        String userSavedPreferences = PrefUtils.getUserPreferences(this);
        String str[] = userSavedPreferences.split(",");
        mPreferences = new ArrayList<>(Arrays.asList(str));
    }

//    private void getDataFromServer() {
//        Retrofit newsWebServiceRetrofit = NewsWebService.getRetrofitClient();
//        NewsAPIRequests newsAPIRequests = newsWebServiceRetrofit.create(NewsAPIRequests.class);
//
//        Call<ArticleResponse> call = newsAPIRequests.getArticlesByCategory("apple", "*", "2020-01-12",
//                "popularity", "534a091354c14911aa44a800e5270924");
//
//        call.enqueue(new Callback<ArticleResponse>() {
//            @Override
//            public void onResponse(@NotNull Call<ArticleResponse> call, @NotNull Response<ArticleResponse> response) {
//
//                if (response.body() != null) {
//                    ArticleResponse articleResponse = response.body();
//                    mArticles = articleResponse.getArticles();
//                    setRecyclerView();
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull Throwable t) {
//                Timber.e(t, "Failed to get data:");
//                System.out.println("Fail to GET data");
//
//            }
//        });
//    }

//
//    private void setRecyclerView() {
//        verticalRecyclerView = findViewById(R.id.vertical_recycler_view);
//        if (customVerticalAdapter == null) {
//            verticalRecyclerView.setLayoutManager(new LinearLayoutManager(App.getAppContext(), RecyclerView.VERTICAL, false));
//            customVerticalAdapter = new CustomVerticalAdapter(articleResponses, mPreferences);
//            verticalRecyclerView.setAdapter(customVerticalAdapter);
//        }else {
//            newsAdapter.notifyDataSetChanged();
//        }
//    }


    private void setRecyclerView() {
        RecyclerView verticalRecyclerView = findViewById(R.id.vertical_recycler_view);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(App.getAppContext(), RecyclerView.VERTICAL, false));
        CustomVerticalAdapter customVerticalAdapter = new CustomVerticalAdapter(articleResponses, mPreferences, this);
        verticalRecyclerView.setAdapter(customVerticalAdapter);
    }

    public void onBackPressed() {
        Timber.d("onBackPressed Called");
        Toast.makeText(this, "Back button pressed. Exit App", Toast.LENGTH_LONG).show();
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }
}
