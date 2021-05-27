package pl.studia.hour20;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RetrievePhotoService extends IntentService {
    public static final String ACTION_RECEIVE = "pl.studia.hour20.RECEIVE";
    public static final String ACTION_FAIL = "pl.studia.hour20.action.FAIL";
    public static final String EXTRA_MESSAGE = "pl.studia.hour20.extra.MESSAGE";
    public final static String API_KEY ="56aa0df2e50b64ee8a31d627b87238d3";
    public final static String NUM_PHOTOS ="100";

    public RetrievePhotoService() {
        super("RetrievePhotoService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                HttpURLConnection connection = null;
                URL dataUrl = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key="+ API_KEY+ "&per_page=" + NUM_PHOTOS +"&format=json&nojsoncallback=1");
                connection = (HttpURLConnection) dataUrl.openConnection();
                connection.connect();
                int status = 0;
                status = connection.getResponseCode();
                Log.d("connection", "status " + status);
                if (status ==200) {
                    InputStream is = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String responseString;
                    StringBuilder sb = new StringBuilder();
                    while ((responseString = reader.readLine()) != null) {
                        sb = sb.append(responseString);
                    }
                    String photoData = sb.toString();
                    // Parse


                    // Does DB exists  should in in app ... just use loader
                    //- loader loads from ContentProvider
                    //- When you refresh,
                    // if yes, return DB exists
                    // run
                    // Get Latest Photos Button on app

                    //OPEN DE
                    //DELETE ALL
                    //Load DB


                    // Insert into DB
                    // Delete all


                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(ACTION_RECEIVE);
                    broadcastIntent.putExtra(EXTRA_MESSAGE, photoData);
                    sendBroadcast(broadcastIntent);

                }
            } catch (IOException e) {
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(ACTION_FAIL);
                broadcastIntent.putExtra(EXTRA_MESSAGE, "FAILED TO RETRIEVE DATA");
                sendBroadcast(broadcastIntent);
                e.printStackTrace();
            }



        }
    }


}
