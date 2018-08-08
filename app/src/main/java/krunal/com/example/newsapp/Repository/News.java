package krunal.com.example.newsapp.Repository;

public class News {

    private String PublisherName;

    private String Title;

    private String NewsUrl;

    private String ImageUrl;

    private String TimeAndDate;

    News(String publisherName, String title, String newsUrl, String imageUrl, String timeAndDate) {
        PublisherName = publisherName;
        Title = title;
        NewsUrl = newsUrl;
        ImageUrl = imageUrl;
        TimeAndDate = timeAndDate;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public String getTitle() {
        return Title;
    }

    public String getNewsUrl() {
        return NewsUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getTimeAndDate() {
        return TimeAndDate;
    }
}
