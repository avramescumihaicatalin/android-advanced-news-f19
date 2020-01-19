package ro.atelieruldigital.news.preferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ro.atelieruldigital.news.R;

public class UserPreferences extends AppCompatActivity implements View.OnClickListener {

    EditText mEditTextPreferences;
    TextView mTextViewPreferencesList;
    FloatingActionButton mFab;
    Button mButtonClearList;
    ConstraintLayout mConstraintLayoutPreferences;

    ArrayList<String> mPreferencesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        initView();

        mPreferencesList = new ArrayList<>();
        mFab.setOnClickListener(this);
        mButtonClearList.setOnClickListener(this);


    }

    private void initView() {
        mEditTextPreferences = findViewById(R.id.edit_text_preferences);
        mFab = findViewById(R.id.fab_add_preference);
        mButtonClearList = findViewById(R.id.button_clear_list);
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
        }

    }

    private void clearPreferencesList() {
        mPreferencesList.clear();
        mTextViewPreferencesList.setText("");
    }

    private void addPreference() {
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
