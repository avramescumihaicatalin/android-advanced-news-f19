package ro.atelieruldigital.news.recycler_view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.ArticleResponse;

public class CustomVerticalAdapter extends RecyclerView.Adapter<CustomVerticalViewHolder> {

    private HashMap<String, ArrayList<ArticleResponse.Article>> mDataSet;
    private ArrayList<String> mPreferences;

    public CustomVerticalAdapter(HashMap<String, ArrayList<ArticleResponse.Article>> mDataSet, ArrayList<String> preferencesList) {
        this.mDataSet = mDataSet;
        this.mPreferences = preferencesList;
    }

    @NonNull
    @Override
    public CustomVerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomVerticalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_layout_news_card,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVerticalViewHolder holder, int position) {
        holder.mTextView.setText(mPreferences.get(position));
        holder.mHorizontalRecyclerView.setLayoutManager(new LinearLayoutManager(App.getAppContext(), RecyclerView.HORIZONTAL, false));
        CustomHorizontalAdapter customHorizontalAdapter = new CustomHorizontalAdapter(mDataSet.get(mPreferences.get(position)));
        holder.mHorizontalRecyclerView.setAdapter(customHorizontalAdapter);

    }

    @Override
    public int getItemCount() {
        return mPreferences.size();
    }
}
