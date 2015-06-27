package com.apps.fourplex.tabletop.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.apps.fourplex.tabletop.R;

/**
 * Created by joseluisrf on 6/27/15.
 */
public class ImageHelper {

    private Bitmap mBitmapBathroomAvailable;
    private Bitmap mBitmapBathroomUnvailable;

    protected ImageHelper(Context context){

        Bitmap d = BitmapFactory.decodeResource(context.getResources(), R.drawable.toilet_available);
        Bitmap d2 =  BitmapFactory.decodeResource(context.getResources(), R.drawable.toilet_not_available);
        int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
        mBitmapBathroomAvailable = Bitmap.createScaledBitmap(d, 512, nh, true);
        mBitmapBathroomUnvailable = Bitmap.createScaledBitmap(d2, 512, nh, true);
    }

    private static ImageHelper instance = null;
    public static ImageHelper getInstance(Context context){
        if(instance == null) {
            instance = new ImageHelper(context);
        }
        return instance;
    }


    public Bitmap getmBitmapBathroomAvailable() {
        return mBitmapBathroomAvailable;
    }

    public void setmBitmapBathroomAvailable(Bitmap mBitmapBathroomAvailable) {
        this.mBitmapBathroomAvailable = mBitmapBathroomAvailable;
    }

    public Bitmap getmBitmapBathroomUnvailable() {
        return mBitmapBathroomUnvailable;
    }

    public void setmBitmapBathroomUnvailable(Bitmap mBitmapBathroomUnvailable) {
        this.mBitmapBathroomUnvailable = mBitmapBathroomUnvailable;
    }
}
