package ro.atelieruldigital.news.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import ro.atelieruldigital.news.R;

public class SearchActivity extends AppCompatActivity {

    EditText mEditTextInput;
    Button mButtonDatePicker;
    DatePicker mDatePicker;
    Spinner mSpinnerSortBy;
    RecyclerView mRecyclerViewSearch;

    ArrayList<String> spinnerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();

        setDataSpinner();
        setSpinner();

        initRecyclerView();
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
        mRecyclerViewSearch = findViewById(R.id.recycler_view_search);
    }

    private void setDataSpinner() {
        spinnerList = new ArrayList<>();
        spinnerList.add("popularity");
        spinnerList.add("publishedAt");
        spinnerList.add("relevancy");
    }

    private void initRecyclerView() {

    }

}
