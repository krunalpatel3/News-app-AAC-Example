package krunal.com.example.newsapp.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import krunal.com.example.newsapp.NetworkApi.Utility;

public class Repository {

    private static final String TAB = Repository.class.getSimpleName();

    private MutableLiveData<List<News>> mutableLiveData = new MutableLiveData<>();

    private Context mcontext;

    public Repository(Context context, String category){
        Log.i(TAB,"Repository call");
        this.mcontext = context;
        getAllNews(category);
    }

    public LiveData<List<News>> GiveNews(){
        try{
            Thread.sleep(1550);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAB,"GiveNews call");
        return mutableLiveData;
    }

    private void getAllNews(String category){
        Log.i(TAB,"getAllNews call");

        String getfinalUrl = Utility.getFinalUrl(category);

        List<News> NewsList = new ArrayList<>();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, getfinalUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAB,"onResponse call");
                    if (response.getInt("totalResults") != 0){

                        JSONArray jsonArrayArticles = response.getJSONArray("articles");

                        for (int i = 0; i < jsonArrayArticles.length(); i++){

                            JSONObject innerjsonObject = jsonArrayArticles.getJSONObject(i);

                            JSONObject sourcejsonObject = innerjsonObject.getJSONObject("source");

                            String getName = sourcejsonObject.getString("name");
                            Log.i(TAB,getName);

                            String getTitle = innerjsonObject.getString("title");
                            Log.i(TAB,getTitle);

                            String getUrl = innerjsonObject.getString("url");
                            Log.i(TAB,getUrl);

                            String getImageUrl = innerjsonObject.getString("urlToImage");
                            Log.i(TAB,getImageUrl);

                            String getDateAndTime = innerjsonObject.getString("publishedAt");
                            Log.i(TAB,getDateAndTime);

                           NewsList.add(new News(getName,getTitle,getUrl,getImageUrl,getDateAndTime));

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(mcontext);
        rQueue.add(stringRequest);

        mutableLiveData.postValue(NewsList);
        Log.i(TAB,String.valueOf(NewsList.size()));

    }







}
