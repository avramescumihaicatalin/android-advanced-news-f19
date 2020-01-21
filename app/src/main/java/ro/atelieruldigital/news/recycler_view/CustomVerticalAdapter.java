package ro.atelieruldigital.news.recycler_view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.HomeActivity;
import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.view_model.NewsViewModel;

public class CustomVerticalAdapter extends RecyclerView.Adapter<CustomVerticalViewHolder> {

    private ArrayList<ArticleResponse> mArticleList;
    ArrayList<ArticleResponse.Article> getmArticleList = new ArrayList<>();
    private ArrayList<String> mPreferences;

    CustomHorizontalAdapter customHorizontalAdapter;
    NewsViewModel newsViewModel;
    HomeActivity homeActivity;

    public CustomVerticalAdapter(ArrayList<ArticleResponse> articleList, ArrayList<String> preferencesList, HomeActivity homeActivity) {
        this.mArticleList = articleList;
        this.mPreferences = preferencesList;
        this.homeActivity=homeActivity;
    }

    @NonNull
    @Override
    public CustomVerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        populare();
        return new CustomVerticalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_layout_news_card,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVerticalViewHolder holder, int position) {
        holder.mTextView.setText(mPreferences.get(position));
        populare();
        holder.mHorizontalRecyclerView.setLayoutManager(new LinearLayoutManager(App.getAppContext(), RecyclerView.HORIZONTAL, false));
        if (customHorizontalAdapter == null) {
            customHorizontalAdapter = new CustomHorizontalAdapter(getmArticleList);
            holder.mHorizontalRecyclerView.setAdapter(customHorizontalAdapter);
            holder.mHorizontalRecyclerView.setItemAnimator(new DefaultItemAnimator());
            holder.mHorizontalRecyclerView.setNestedScrollingEnabled(true);
        } else {
            customHorizontalAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mPreferences.size();
    }

    public void populare() {
        newsViewModel = ViewModelProviders.of(homeActivity).get(NewsViewModel.class);
        newsViewModel.init();
        newsViewModel.getNewsRepo().observe(homeActivity, newsResponse -> {
            List<ArticleResponse.Article> newsArticles = newsResponse.getArticles();
            getmArticleList.addAll(newsArticles);
            this.notifyDataSetChanged();
        });

    }


}
