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
import krunal.com.example.newsapp.ViewModel.BusinessNewsViewModel;



public class BusinessNewsFragment extends Fragment {

    private BusinessNewsViewModel mBusinessNewsViewModel;
    private RecycleAdapter mRecycleAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBusinessNewsViewModel = ViewModelProviders.of(this).get(BusinessNewsViewModel.class);
        if (Utility.CheckInternetConnection(getContext())){

            Refresh();

            mRecycleAdapter.SetOnClickListener(new OnItemClickListener() {
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

    public void Refresh(){
        if (Utility.CheckInternetConnection(getContext())) {
            mBusinessNewsViewModel.getNews().observe(this, new Observer<List<News>>() {
                @Override
                public void onChanged(@Nullable List<News> news) {
                    mRecycleAdapter.addNews(news);
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

        View view = inflater.inflate(R.layout.businessnews_fragment, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBusinessNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleAdapter = new RecycleAdapter(getContext());
        mSwipeRefreshLayout.setRefreshing(true);
        recyclerView.setAdapter(mRecycleAdapter);

        return view;
    }
}
