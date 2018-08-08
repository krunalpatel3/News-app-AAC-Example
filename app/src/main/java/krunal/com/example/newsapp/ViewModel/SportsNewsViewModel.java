package krunal.com.example.newsapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import krunal.com.example.newsapp.NetworkApi.Utility;
import krunal.com.example.newsapp.Repository.News;
import krunal.com.example.newsapp.Repository.Repository;

public class SportsNewsViewModel extends AndroidViewModel {

    private LiveData<List<News>> mNewsList;

    private Repository mRepository;

    public SportsNewsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application, Utility.Sports);
        mNewsList = mRepository.GiveNews();
    }

    public LiveData<List<News>> getNews(){
        return mNewsList;
    }
}
