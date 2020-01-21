package ro.atelieruldigital.news.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseActivity;
import ro.atelieruldigital.news.log_in.GoogleSignInActivity;
import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.model.NewsAPIRequests;
import ro.atelieruldigital.news.model.WebService.NewsWebService;
import ro.atelieruldigital.news.recycler_view.CustomVerticalAdapter;
import ro.atelieruldigital.news.search.SearchActivity;
import ro.atelieruldigital.news.utils.PrefUtils;
import timber.log.Timber;

public class HomeActivity extends BaseActivity {

    ArrayList<ArticleResponse.Article> mArticles;
    ArrayList<String> mPreferences;
    HashMap<String, ArrayList<ArticleResponse.Article>> mDataSet;

    FloatingActionButton fabSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        setPreferencesList();
        getDataFromServer();
    }

    private void initView() {
        fabSearch = findViewById(R.id.fab_search);
    }

    private void setPreferencesList() {
        mPreferences = new ArrayList<>();
        String userSavedPreferences = PrefUtils.getUserPreferences(this);
        String str[] = userSavedPreferences.split(",");
        mPreferences = new ArrayList<>(Arrays.asList(str));
    }

    private void getDataFromServer() {
        mDataSet = new HashMap<>();
        getDataAndSaveToDataSet(mPreferences.get(0));
    }

    private void getDataAndSaveToDataSet(String searchString) {
        Retrofit newsWebServiceRetrofit = NewsWebService.getRetrofitClient();
        NewsAPIRequests newsAPIRequests = newsWebServiceRetrofit.create(NewsAPIRequests.class);

        Call<ArticleResponse> call = newsAPIRequests.queryArticles(searchString, "2020-01-12", "2020-01-12",
                "popularity", "534a091354c14911aa44a800e5270924");

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArticleResponse> call, @NotNull Response<ArticleResponse> response) {

                if (response.body() != null) {
                    ArticleResponse articleResponse = response.body();
                    mArticles = articleResponse.getArticles();

                    mDataSet.put(searchString, mArticles);

                    if (mDataSet.size() < mPreferences.size()) {
                        getDataAndSaveToDataSet(mPreferences.get(mDataSet.size()));
                    } else if (mDataSet.size() == mPreferences.size()) {
                        setRecyclerView();
                    }
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
        CustomVerticalAdapter customVerticalAdapter = new CustomVerticalAdapter(mDataSet, mPreferences);
        verticalRecyclerView.setAdapter(customVerticalAdapter);
    }

    public void onBackPressed() {
        Timber.d("onBackPressed Called");
        Toast.makeText(this,"Back button pressed. Exit App",Toast.LENGTH_LONG).show();
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, GoogleSignInActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.action_search) {
            Intent intentGoToSearchActivity = new Intent(this, SearchActivity.class);
            startActivity(intentGoToSearchActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToSearchActivity(View view) {
        Intent intentGoToSearchActivity = new Intent(this, SearchActivity.class);
        startActivity(intentGoToSearchActivity);
    }
}
