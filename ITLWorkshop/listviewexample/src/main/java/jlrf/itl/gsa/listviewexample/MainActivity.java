package jlrf.itl.gsa.listviewexample;

import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import jlrf.itl.gsa.listviewexample.db.MyContactsCmd;
import jlrf.itl.gsa.listviewexample.model.MyContact;
import jlrf.itl.gsa.listviewexample.ws.CountriesWS;


public class MainActivity extends ActionBarActivity

{

    ListView lvContacts;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContacts = (ListView)findViewById(R.id.lvContacts);
//        String[] names = {
//                "Jose Luis",
//                "Emilio",
//                "Diana",
//                "Ana",
//                "Jorge",
//                "Francisco",
//                "Oswaldo",
//                "Barragan",
//                "Gina",
//                "Lina",
//                "Rios",
//                "Karla"
//        };
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
//                android.R.id.text1, names);
//        lvContacts.setAdapter(adapter);
//        asyncQueryContacts = new AsyncQueryContacts();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_register_contact){
            RegisterDialog registerDialog = new RegisterDialog(this);
            registerDialog.show();

            registerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    new AsyncQueryContacts().execute();
                }
            });
            return true;
        }

        if(id == R.id.action_test_ws){
            AsyncGetCountries asyncGetCountries = new AsyncGetCountries();
            asyncGetCountries.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume" );
        new AsyncQueryContacts().execute();
    }


    private  class AsyncGetCountries extends  AsyncTask<Object, Object, Object>{
        private final String TAG  = AsyncGetCountries.class.getSimpleName();
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
            if(o instanceof  Exception){
                ((Exception)o).printStackTrace();
                Toast.makeText(MainActivity.this,
                        "Ohh an error has occured",
                        Toast.LENGTH_SHORT).show();
            }
            else if(o instanceof  JSONObject){
                JSONObject jsonObject = (JSONObject)o;

                Log.i(TAG, "jsonObject::" + jsonObject.toString());
            }

        }
    }

    public class AsyncQueryContacts extends AsyncTask<Object, Object, Object >{

        private final String TAG = AsyncQueryContacts.class.getSimpleName();
        @Override
        protected Object doInBackground(Object... objects) {
            MyContactsCmd cmd = new MyContactsCmd(MainActivity.this);
            try {
                List<MyContact> lst =  cmd.getContacts();
                Log.i(TAG, "lst.size()::" + lst.size());
                return lst;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(o instanceof  List){
                List<MyContact> lst = (List < MyContact >)o;
                adapter = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_list_item_1,
                        android.R.id.text1);
                for(MyContact c : lst){
                    adapter.add(c.getName());
                }
                lvContacts.setAdapter(adapter);

            }
            if(o instanceof  Exception){
                Toast.makeText(MainActivity.this,
                        "Exception :" + ((Exception)o).getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

}
