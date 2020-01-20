package ro.atelieruldigital.news.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import ro.atelieruldigital.news.utils.PrefUtils;
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
        String userSavedPreferences = PrefUtils.getUserPreferences(this);
        String str[] = userSavedPreferences.split(",");
        mPreferences = new ArrayList<>(Arrays.asList(str));
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
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, GoogleSignInActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
