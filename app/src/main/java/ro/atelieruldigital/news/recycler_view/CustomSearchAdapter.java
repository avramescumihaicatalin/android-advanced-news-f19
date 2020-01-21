package ro.atelieruldigital.news.recycler_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ro.atelieruldigital.news.App;
import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.web_view.WebViewActivity;

public class CustomSearchAdapter extends RecyclerView.Adapter<CustomHorizontalViewHolder> {

    private ArrayList<ArticleResponse.Article> mArticleList;
    public static final String WEB_VIEW_URL = "WEB_VIEW_URL";

    public CustomSearchAdapter(ArrayList<ArticleResponse.Article> articleList) {
        this.mArticleList = articleList;
    }

    @NonNull
    @Override
    public CustomHorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomHorizontalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_layout_news_card,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHorizontalViewHolder holder, int position) {
        holder.mTextViewTitle.setText(mArticleList.get(position).getTitle());
        if (mArticleList.get(position).getDescription().length() > 150) {
            holder.mTextViewDescription.setText(mArticleList.get(position).getDescription().substring(0, 150) + "...");
        } else {
            holder.mTextViewDescription.setText(mArticleList.get(position).getDescription());
        }
        holder.mButtonFindMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebViewActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    private void startWebViewActivity(int position) {
        Bundle bundle = new Bundle();
        String URL = mArticleList.get(position).getArticleURL().toString();
        if (URL != null) {
            bundle.putString(WEB_VIEW_URL, URL);
            Intent intentWebView = new Intent(App.getAppContext(), WebViewActivity.class);
            intentWebView.putExtras(bundle);
            intentWebView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getAppContext().startActivity(intentWebView);
        } else {
            Toast.makeText(App.getAppContext(), "Nu exista un URL valid pentru acest articol", Toast.LENGTH_SHORT).show();
        }
    }
}
