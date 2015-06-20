package jlrf.itl.gsa.listviewexample.ws;

import android.net.Uri;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by jose.ramos.fernandez on 28/01/2015.
 */
public class WebService {
    protected String URL_SERVICE;
    private static final String
            TAG = WebService.class.getSimpleName();
    protected HttpURLConnection urlConnection = null;

    public WebService(){

    }
    protected String wsGet(String methodName) throws  Exception{

        return wsGet(methodName, null);
    }


    protected String wsGet(String methodName, Map<String, String> params) throws Exception{

        BufferedReader reader = null;
        String str_url = URL_SERVICE + methodName;

        if(params != null)
        {
            String getUrl = str_url + "?";
            Uri.Builder uriBuilder = Uri.parse(getUrl).buildUpon();
            for(Map.Entry<String, String> param : params.entrySet()) {
                uriBuilder.appendQueryParameter(param.getKey(),
                        param.getValue());
            }
            str_url = uriBuilder.build().toString();

        } //END IF
        Log.i(TAG, "url::" + str_url);

        URL url = null;
        urlConnection = null;
        InputStream inputStream = null;
        StringBuffer buffer = new StringBuffer();

        try {
            url = new URL(str_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();

            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
            if(buffer == null){
                // Stream was empty.  No point in parsing.
                return null;
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                reader.close();
            }
        }
        catch (Exception ex){
            if(reader != null)
                reader.close();
        }

        return buffer.toString();

    }
}
