package ro.atelieruldigital.news.preferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.HomeActivity;
import ro.atelieruldigital.news.utils.PrefUtils;

public class UserPreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextPreferences;
    private TextView mTextViewPreferencesList;
    private FloatingActionButton mFab;
    private Button mButtonClearList;
    private Button mButtonSavePreferences;
    private ConstraintLayout mConstraintLayoutPreferences;

    private ArrayList<String> mPreferencesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        initView();

        mTextViewPreferencesList.setText(PrefUtils.getUserPreferences(this));
        mPreferencesList = new ArrayList<>();
        mFab.setOnClickListener(this);
        mButtonClearList.setOnClickListener(this);
        mButtonSavePreferences.setOnClickListener(this);

        getPreferedList();
    }

    private void getPreferedList() {
        if (TextUtils.isEmpty(PrefUtils.getUserPreferences(this))) {
            clearPreferencesList();
        } else {
            mButtonClearList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorAccent));
            mTextViewPreferencesList.setText(PrefUtils.getUserPreferences(this));
        }

    }

    private void initView() {
        mEditTextPreferences = findViewById(R.id.edit_text_preferences);
        mFab = findViewById(R.id.fab_add_preference);
        mButtonClearList = findViewById(R.id.button_clear_list);
        mButtonSavePreferences = findViewById(R.id.button_save_preferences);
        mConstraintLayoutPreferences = findViewById(R.id.constraint_layout_preferences);
        mTextViewPreferencesList = findViewById(R.id.text_view_preference_list);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_preference: {
                addPreference();
                break;
            }
            case R.id.button_clear_list: {
                clearPreferencesList();
                break;
            }
            case R.id.button_save_preferences: {
                savePreferences();
                break;
            }
        }

    }

    private void savePreferences() {
        Toast.makeText(this, "Preferintele au fost salvate", Toast.LENGTH_SHORT).show();
        PrefUtils.setUserPreferences(this, mTextViewPreferencesList.getText().toString());
        Intent goToHomeActivity = new Intent(this, HomeActivity.class);
        startActivity(goToHomeActivity);
    }

    private void clearPreferencesList() {
        mButtonClearList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorGrey));
        mPreferencesList.clear();
        mTextViewPreferencesList.setText("");
    }

    private void addPreference() {
        mButtonClearList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorAccent));
        String introducedText;
        String previousText;
        if (mEditTextPreferences != null && !TextUtils.isEmpty(mEditTextPreferences.getText())) {
            introducedText = mEditTextPreferences.getText().toString();
            mPreferencesList.add(introducedText);
            mEditTextPreferences.setText("");
            if (mTextViewPreferencesList != null) {
                previousText = mTextViewPreferencesList.getText().toString();
                if (!TextUtils.isEmpty(previousText)) {
                    mTextViewPreferencesList.setText(previousText + ", " + introducedText);
                } else {
                    mTextViewPreferencesList.setText(introducedText);
                }

            }
        }
    }
}
