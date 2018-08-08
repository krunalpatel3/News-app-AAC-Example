package krunal.com.example.newsapp.NetworkApi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Utility {

    public static final String Business = "business";
    public static final String Sports = "sports";
    public static final String Health = "health";
    public static final String Technology = "technology";

    public static final String NewsUrl = "NewsUrl";
   // public static final String CountryName = "In";
   // public static final String API_KEY = "2c7289536d1e48a182819ff5387c8a2f";

    public static String getFinalUrl(String category){
        String BASE_URL;
        if(category.matches("")){
            BASE_URL ="https://newsapi.org/v2/top-headlines?country=In" +
                    "&apiKey=2c7289536d1e48a182819ff5387c8a2f";
        }else{
            BASE_URL = "https://newsapi.org/v2/top-headlines?country=In&" +
                    "category=" + category +"&apiKey=2c7289536d1e48a182819ff5387c8a2f";
        }
        return BASE_URL;
    }


    /**
     * @param utcTimeString Time in UTC:+00 - Example: 2018-05-10T10:13:00Z
     * @return Formatted String of time elapsed by now in min/hrs/days
     */
    public static String getElapsedTime(String utcTimeString) {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            long timeElapsedInSeconds =
                    (System.currentTimeMillis() - dateFormat.parse(utcTimeString).getTime()) / 1000;

            if (timeElapsedInSeconds < 60) {
                return "less than 1m";
            } else if (timeElapsedInSeconds < 3600) {
                timeElapsedInSeconds = timeElapsedInSeconds / 60;
                return timeElapsedInSeconds + "m";
            } else if (timeElapsedInSeconds < 86400) {
                timeElapsedInSeconds = timeElapsedInSeconds / 3600;
                return timeElapsedInSeconds + "h";
            } else {
                timeElapsedInSeconds = timeElapsedInSeconds / 86400;
                return timeElapsedInSeconds + "d";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean CheckInternetConnection(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static void MakeToastMessage(Context context){
        Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
    }


}
