package com.apps.fourplex.tabletop;

import android.app.Dialog;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.fourplex.tabletop.obj.Response;
import com.apps.fourplex.tabletop.obj.UtilityHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.parse.Parse;
import com.parse.ParseObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private NfcAdapter mNfcAdapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        try {
            Parse.initialize(this, "NGP3ilfLS8gSeQGLgAddnTo4hQkOA4OhQqPQ0mXD", "P6lCCgijwKwuoMfp879XKw5PTgsdwulcmqa0UefH");
            //Check  PlayServices
            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            String imei =telephonyManager.getDeviceId();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor =  sharedPreferences.edit();
            editor.putString("imei", imei);
            editor.commit();

            int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getApplicationContext());
            if (resultCode == ConnectionResult.SUCCESS) {
                Intent intentRegisterGCM = new Intent(this, RegistrationIntentService.class);
                startService(intentRegisterGCM);

            } else if (resultCode == ConnectionResult.SERVICE_MISSING ||
                    resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
                    resultCode == ConnectionResult.SERVICE_DISABLED) {
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1);
                dialog.show();
            }

            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
            if (mNfcAdapter == null) {
                // Stop here, we definitely need NFC
                Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
                finish();
                return;

            }

        }
        catch (Exception ex){
            Toast.makeText(this, "Exception::"+ ex.getMessage(), Toast.LENGTH_LONG).show();
        }





    }

    public void onBtnCafeteria_Click(View view){
        Intent intentCafeteria = new Intent(this, CafeteriaActivity.class);
        startActivity(intentCafeteria);
    }

    public void onBtnWc_Click(View view){
        Intent intentCafeteria = new Intent(this, WcActivity.class);
        startActivity(intentCafeteria);
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

        return super.onOptionsItemSelected(item);
    }


}
