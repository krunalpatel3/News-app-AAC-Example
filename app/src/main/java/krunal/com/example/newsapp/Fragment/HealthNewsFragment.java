package krunal.com.example.newsapp.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import krunal.com.example.newsapp.Activity.WebViewActivity;
import krunal.com.example.newsapp.Adapter.OnItemClickListener;
import krunal.com.example.newsapp.Adapter.RecycleAdapter;
import krunal.com.example.newsapp.NetworkApi.Utility;
import krunal.com.example.newsapp.R;
import krunal.com.example.newsapp.Repository.News;
import krunal.com.example.newsapp.ViewModel.HealthViewModel;

import static android.os.Build.ID;

public class HealthNewsFragment extends Fragment {

    private HealthViewModel mHealthViewModel;
    private RecycleAdapter mRecyclerAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHealthViewModel = ViewModelProviders.of(this).get(HealthViewModel.class);

        if (Utility.CheckInternetConnection(getContext())){

            Refresh();

            mRecyclerAdapter.SetOnClickListener(new OnItemClickListener() {
                @Override
                public void OnClick(News news) {
                    String getNewsUrl = news.getNewsUrl();
                    Intent intent = new Intent(getContext(),WebViewActivity.class);
                    intent.putExtra(Utility.NewsUrl,getNewsUrl);
                    startActivity(intent);
                }
            });

        }else {
            Utility.MakeToastMessage(getContext());
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }
        });

    }

    private void Refresh(){
        if (Utility.CheckInternetConnection(getContext())) {
            mHealthViewModel.getNews().observe(this, new Observer<List<News>>() {
                @Override
                public void onChanged(@Nullable List<News> news) {
                    mRecyclerAdapter.addNews(news);
                }
            });
            mSwipeRefreshLayout.setRefreshing(false);
        }else {
            Utility.MakeToastMessage(getContext());
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.healthnews_fragment, container, false);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHealthNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAdapter = new RecycleAdapter(getContext());
        mSwipeRefreshLayout.setRefreshing(true);
        recyclerView.setAdapter(mRecyclerAdapter);



        return view;
    }
}
