package krunal.com.example.newsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import krunal.com.example.newsapp.NetworkApi.Utility;
import krunal.com.example.newsapp.R;
import krunal.com.example.newsapp.Repository.News;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>{

    private List<News> mNewsList = new ArrayList<>();
    private LayoutInflater mlayoutInflater;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public RecycleAdapter(Context context){
        Log.i("re","RecycleAdapter call");
        this.mContext = context;
        mlayoutInflater = LayoutInflater.from(context);
    }

    public void SetOnClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.news_item_list,parent,false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        //Log.i("re","RecycleAdapter Call");
        //Log.i("re",String.valueOf(mNewsList.size()));
        News getNewsList = mNewsList.get(position);

        String ImageUrl = getNewsList.getImageUrl();
        if (ImageUrl != null && !TextUtils.isEmpty(ImageUrl) && !ImageUrl.equals("")) {
            Picasso.get()
                    .load(ImageUrl)
                    .fit()
                    .centerCrop()
                    .error(R.mipmap.placeholdernews)
                    .into(holder.mImageView);
            Log.v(getClass().getSimpleName(), ImageUrl);
        } else {
            Picasso.get()
                    .load(R.mipmap.placeholdernews)
                    .into(holder.mImageView);
        }

        holder.mTitle.setText(getNewsList.getTitle());
        holder.mDate.setText(mContext.getString(
                R.string.headline_date_format,
                getNewsList.getPublisherName(),
                Utility.getElapsedTime(getNewsList.getTimeAndDate())
                ));
        holder.Bind(mNewsList.get(position),mOnItemClickListener);
    }

    public void addNews(List<News> newsList){
        this.mNewsList = newsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    static class RecycleViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView mTitle,mDate;

        RecycleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_news_image);
            mTitle = itemView.findViewById(R.id.tv_news_title);
            mDate = itemView.findViewById(R.id.tv_news_source);
        }

        void Bind(News news, OnItemClickListener listener){
            itemView.setOnClickListener(v -> {
                listener.OnClick(news);
            });
        }
    }
}
