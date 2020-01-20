package ro.atelieruldigital.news.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class ArticleResponse {
    // TODO: Add fields according to API
    @SerializedName("status")
    @Expose
    private
    String status;
    @SerializedName("totalResults")
    @Expose
    private
    Integer totalResults;
    @SerializedName("articles")
    @Expose
    ArrayList<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }


    static public class Article {

        @SerializedName("source")
        @Expose
        private
        Source source;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("url")
        @Expose
        private URL articleURL;
        @SerializedName("imageURL")
        @Expose
        private URL imageURL;
        @SerializedName("publishedDate")
        @Expose
        private Date publishedDate;

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public URL getArticleURL() {
            return articleURL;
        }

        public void setArticleURL(URL articleURL) {
            this.articleURL = articleURL;
        }

        public URL getImageURL() {
            return imageURL;
        }

        public void setImageURL(URL imageURL) {
            this.imageURL = imageURL;
        }

        public Date getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(Date publishedDate) {
            this.publishedDate = publishedDate;
        }

        @NonNull
        @Override
        public String toString() {
            return getAuthor();
        }
    }

    class Source {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}