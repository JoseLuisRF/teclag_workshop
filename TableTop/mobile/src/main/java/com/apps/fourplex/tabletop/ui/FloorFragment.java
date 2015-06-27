package com.apps.fourplex.tabletop.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.fourplex.tabletop.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by joseluisrf on 6/26/15.
 */
public class FloorFragment extends Fragment{

    public static final String FLOOR_LEVEL = "floor_level";
    private static final String TAG = FloorFragment.class.getSimpleName();

    public static final int FLOOR_3 = 3;
    public static final int FLOOR_4 = 4;
    public static final int FLOOR_5 = 5;
    public static final int FLOOR_6 = 6;
    public static final int FLOOR_7 = 7;


    private int mFloor = -1;


    private TextView tvLabel;
    private TextView mTvStatus;
    private ImageView mImageStatus;
    private String mImei;
    private Context context;
    private LinearLayout mFloorLinearLayout;
//    private Bitmap mToiletsAvailables;
//    private Bitmap mToiletsUnavailables;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.floor_fragment, container, false);
        tvLabel = (TextView)view.findViewById(R.id.tvLabel);
        mTvStatus = (TextView)view.findViewById(R.id.tvStatus);
        mImageStatus = (ImageView)view.findViewById(R.id.ivStatus);
        mFloorLinearLayout = (LinearLayout)view.findViewById(R.id.llFloor);



        Button btnNotifyMe  = (Button)view.findViewById(R.id.btnNotifyMe);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mImei = sharedPreferences.getString("imei", "");
        context = getActivity();
        Bundle b =  getArguments();
        if(b != null){
            mFloor = b.getInt(FLOOR_LEVEL);
        }
        btnNotifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("devices");
                query.whereEqualTo("imei", mImei);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if(e == null && list.size() > 0){
                            try {
                                ParseQuery<ParseObject> query = ParseQuery.getQuery("devices");
                                ParseObject parseDevice = query.get(list.get(0).getObjectId());
                                parseDevice.put("notify_me",getBinaryNumber(mFloor));
                                parseDevice.saveInBackground();
                                parseDevice.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e == null)
                                            Toast.makeText(context,
                                                    "Notification enabled.",
                                                    Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }catch (Exception ex){
                                Toast.makeText(context,
                                        ex.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }

                        } else{
                            Toast.makeText(context, "Oops Ocurrio un error al buscar el dispositivo",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        return view;
    }

    public String getBinaryNumber(int floorLevel){
        switch (floorLevel){
            case FLOOR_3:
                return Integer.toBinaryString(1);
            case FLOOR_4:
                return Integer.toBinaryString(2);
            case FLOOR_5:
                return Integer.toBinaryString(3);
            case FLOOR_6:
                return Integer.toBinaryString(4);
            case FLOOR_7:
                return Integer.toBinaryString(5);
            default:
                return Integer.toBinaryString(0);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        try {

            switch (mFloor) {
                case FLOOR_3:
                    tvLabel.setText(getString(R.string.label_floor_3));
                    getFloorData("GjQUa6a1JY");
                    break;
                case FLOOR_4:
                    tvLabel.setText(getString(R.string.label_floor_4));
                    getFloorData("QV6BOmkeGL");
                    break;
                case FLOOR_5:
                    tvLabel.setText(getString(R.string.label_floor_5));
                    getFloorData("jAjOxUNdIi");
                    break;
                case FLOOR_6:
                    tvLabel.setText(getString(R.string.label_floor_6));
                    getFloorData("4FR03jBfXk");
                    break;
                case FLOOR_7:
                    tvLabel.setText(getString(R.string.label_floor_7));
                    getFloorData("XT7Y18lkkF");
                    break;
            }
        }
        catch (Exception ex){
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }

    }

    private void getFloorData(String floor_id) throws ParseException {
        Log.i(TAG, "getFloorsData");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("bathrooms");

        Log.i(TAG, "floor_id::" + floor_id);
        ParseObject parseObject = query.get(floor_id);
        boolean male_available = parseObject.getInt("male") == 1 ? false : true;
        boolean female_available = parseObject.getInt("female") == 1 ? false : true;

        Log.i(TAG, "male_available::" + male_available);
//        int sdk = android.os.Build.VERSION.SDK_INT;
        ImageHelper imageHelper = ImageHelper.getInstance(getActivity());

        if(male_available) {
            mTvStatus.setText(getString(R.string.toilets_available));
            mImageStatus.setImageBitmap(imageHelper.getmBitmapBathroomAvailable());


        }
        else {
            mTvStatus.setText(getString(R.string.toilets_unavailable));
            mImageStatus.setImageBitmap(imageHelper.getmBitmapBathroomUnvailable());

        }


    }
}
