package jlrf.itl.gsa.listviewexample.ws;


import android.util.Log;

import org.json.JSONObject;

/**
 * Created by joseluisrf on 6/20/15.
 */
public class CountriesWS extends WebService {

    private static final String TAG = CountriesWS.class.getSimpleName();
    private static final String METHOD_ALL = "all";
    public CountriesWS(){
        this.URL_SERVICE = "http://services.groupkt.com/country/get/";
    }

    public JSONObject getCountries() throws Exception{
        String res = wsGet(METHOD_ALL);
        Log.i(TAG, "res::" + res);
        return new JSONObject(res);
    }

}
