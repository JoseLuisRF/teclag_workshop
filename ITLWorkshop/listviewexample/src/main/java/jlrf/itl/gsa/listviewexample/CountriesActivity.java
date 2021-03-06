package jlrf.itl.gsa.listviewexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import jlrf.itl.gsa.listviewexample.model.Country;
import jlrf.itl.gsa.listviewexample.ui.CountriesAdapter;
import jlrf.itl.gsa.listviewexample.ws.CountriesWS;

/**
 * Created by joseluisrf on 6/20/15.
 */
public class CountriesActivity extends AppCompatActivity{

    private ListView lvCountries;
    private Context context;
    private ProgressDialog progressDialog;
    private CountriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_activity);
        lvCountries = (ListView)findViewById(R.id.lvCountries);
        context = this;

        AsyncGetCountries asyncGetCountries = new AsyncGetCountries();
        asyncGetCountries.execute();

    }

    private  class AsyncGetCountries extends AsyncTask<Object, Object, Object> {
        private final String TAG  = AsyncGetCountries.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object... objects) {
            CountriesWS ws = new CountriesWS();
            try {
                JSONObject jsonObject = ws.getCountries();
                Log.i(TAG, "jsonObject.length()::" + jsonObject.length());

                return jsonObject;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return ex;
            }


        }

        @Override
        protected void onPostExecute(Object o) {
            progressDialog.dismiss();
            if(o instanceof  Exception){
                ((Exception)o).printStackTrace();
                Toast.makeText(context,
                        "Ohh an error has occurred",
                        Toast.LENGTH_SHORT).show();
            }
            else if(o instanceof  JSONObject){
                JSONObject jsonObject = (JSONObject)o;
                adapter = new CountriesAdapter(context);
                try {
                    JSONArray jsonArray = jsonObject.getJSONObject("RestResponse").getJSONArray("result");
                    Log.i(TAG, "jsonArray.length()::" + jsonArray.length());

                    adapter.clear();
                    for (int i = 0; i< jsonArray.length(); i++) {
                        adapter.add(Country.parse(jsonArray.getJSONObject(i)));
                    }
                    lvCountries.setAdapter(adapter);
                }
                catch (Exception ex){
                    Toast.makeText(context, "oops an error has occurred",
                            Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
