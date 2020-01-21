package ro.atelieruldigital.news.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.model.NewsAPIRequests;
import ro.atelieruldigital.news.model.WebService.NewsWebService;
import ro.atelieruldigital.news.recycler_view.CustomHorizontalAdapter;
import ro.atelieruldigital.news.recycler_view.CustomVerticalAdapter;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

    EditText mEditTextInput;
    Button mButtonDatePicker;
    DatePicker mDatePicker;
    Spinner mSpinnerSortBy;
    Button mButtonStartSearching;
    RecyclerView mRecyclerViewSearch;

    ArrayList<String> spinnerList;
    ArrayList<ArticleResponse.Article> mArticles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();

        setDataSpinner();
        setSpinner();

//        getDataFromServer("apple", "2020-01-12", "popularity");

    }

    private void setSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSortBy.setAdapter(spinnerAdapter);
    }

    private void initView() {
        mEditTextInput = findViewById(R.id.edit_text_input);
        mButtonDatePicker = findViewById(R.id.button_date_picker);
        mDatePicker = findViewById(R.id.date_picker);
        mSpinnerSortBy = findViewById(R.id.spinner_sort_by);
        mButtonStartSearching = findViewById(R.id.button_start_searching);
        mRecyclerViewSearch = findViewById(R.id.recycler_view_search);
    }

    private void setDataSpinner() {
        spinnerList = new ArrayList<>();
        spinnerList.add("popularity");
        spinnerList.add("publishedAt");
        spinnerList.add("relevancy");
    }

    private void getDataFromServer(String searchingString, String date, String sortBy) {
        Retrofit newsWebServiceRetrofit = NewsWebService.getRetrofitClient();
        NewsAPIRequests newsAPIRequests = newsWebServiceRetrofit.create(NewsAPIRequests.class);

        Call<ArticleResponse> call = newsAPIRequests.queryArticles(searchingString, date, date,
                sortBy, "534a091354c14911aa44a800e5270924");

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
        RecyclerView verticalRecyclerView = findViewById(R.id.recycler_view_search);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(App.getAppContext(), RecyclerView.VERTICAL, false));
        CustomHorizontalAdapter customHorizontalAdapter = new CustomHorizontalAdapter(mArticles);
        verticalRecyclerView.setAdapter(customHorizontalAdapter);
    }

    public void startSearchingOnClick(View view) {
        String searchString = "";
        String date = "";
        String sortBy = "";

        if (mEditTextInput != null) {
            searchString = mEditTextInput.getText().toString();
            if (TextUtils.isEmpty(searchString)) {
                Toast.makeText(this, getResources().getString(R.string.hint_introduce_a_subject_for_searching), Toast.LENGTH_SHORT).show();
            }
        }

        date = "2020-01-12";
        if (mSpinnerSortBy != null) {
            sortBy = mSpinnerSortBy.getSelectedItem().toString();
        }

        getDataFromServer(searchString, date, sortBy);
    }

    public void displayDatePickerOnClick(View view) {
        // set today as default date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

//        // display the date picker dialog
        DialogFragment datePickerFragment = new DatePickerFragment();
        ((DatePickerFragment) datePickerFragment).setYear(year);
        ((DatePickerFragment) datePickerFragment).setMonth(month);
        ((DatePickerFragment) datePickerFragment).setDay(day);
        datePickerFragment.show(getSupportFragmentManager(), "DatePicker");
        mButtonDatePicker.setText("AAAA-ZZ-LL");
    }
}
