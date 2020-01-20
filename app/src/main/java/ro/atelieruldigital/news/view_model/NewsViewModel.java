package ro.atelieruldigital.news.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ro.atelieruldigital.news.model.ArticleResponse;
import ro.atelieruldigital.news.model.Repository.NewsRepository;
import timber.log.Timber;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<ArticleResponse> mutableLiveData;
    private NewsRepository newsRepository;
    static final String API_KEY = "4b3b375b6f9e462b8513e1471c5428b9";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    String currentDate = dtf.format(LocalDateTime.now()).toString();


    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.
                getArticlesByCategory("Tesla", "2019-01-01", currentDate, "popularity", API_KEY);
    }

    public LiveData<ArticleResponse> getNewsRepo() {
        return mutableLiveData;
    }

}
