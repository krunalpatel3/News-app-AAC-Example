package krunal.com.example.newsapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import krunal.com.example.newsapp.NetworkApi.Utility;
import krunal.com.example.newsapp.Repository.News;
import krunal.com.example.newsapp.Repository.Repository;

public class HealthViewModel extends AndroidViewModel {

    private LiveData<List<News>> mNewList;

    private Repository mRepository;

    public HealthViewModel(@NonNull Application application) {
        super(application);

        mRepository = new Repository(application, Utility.Health);
        mNewList = mRepository.GiveNews();
    }

    public LiveData<List<News>> getNews(){
        return mNewList;
    }
}
