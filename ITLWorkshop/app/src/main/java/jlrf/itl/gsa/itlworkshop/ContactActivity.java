package jlrf.itl.gsa.itlworkshop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jlrf.itl.gsa.itlworkshop.db.MyContactsCmd;
import jlrf.itl.gsa.itlworkshop.model.MyContact;

/**
 * Created by joseluisrf on 5/23/15.
 */
public class ContactActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int INDEX_IMG_THUMBNAIL = 0;
    public static final int INDEX_IMG_FULL = 1;

    private static  final  String TAG = ContactActivity.class.getSimpleName();
    Context context;
    ImageView mImageViewContact;
    String mCurrentPhotoPath;
    EditText etBriefMessage, etContactName, etContactPhone;

    File[] photoFiles = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        context = this;
        Button btnTakePhoto = (Button)findViewById(R.id.btnTakePhoto);
        Button btnSaveContact = (Button)findViewById(R.id.btnSaveContact);
        mImageViewContact = (ImageView)findViewById(R.id.ivContact);
        etBriefMessage = (EditText)findViewById(R.id.etBriefMesage);
        etContactName = (EditText)findViewById(R.id.etContactName);
        etContactPhone = (EditText)findViewById(R.id.etContactPhone);
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        btnSaveContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContact();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go

            try {
                photoFiles = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(TAG, "ex::" + ex.getMessage());

            }
            // Continue only if the File was successfully created
            if (photoFiles != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFiles[INDEX_IMG_FULL]));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File[] createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());
        File[] imgs = new File[2];
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        try{
        imgs[INDEX_IMG_FULL] = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        imgs[INDEX_IMG_THUMBNAIL] = File.createTempFile(
                "thumbnail_" + imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + imgs[INDEX_IMG_FULL].getAbsolutePath();
        return imgs;
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Exception::" + ex.getMessage());
            return null;
        }
    }



    private void saveContact(){
        //Validating
        if(etBriefMessage.getText().equals("")){
            Toast.makeText(context,
                    getString(R.string.err_brief_message),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(etContactName.getText().equals("")){
            Toast.makeText(context,
                    getString(R.string.err_name),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(etContactPhone.getText().equals("")){
            Toast.makeText(context,
                    getString(R.string.err_phone_number),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        MyContact contact = new MyContact();
        contact.setName(etContactName.getText().toString());
        contact.setBrief_message(etBriefMessage.getText().toString());
        contact.setPhone_number(etContactPhone.getText().toString());
        contact.setPhoto(photoFiles[INDEX_IMG_FULL].getPath());
        contact.setThumbnail_photo(photoFiles[INDEX_IMG_THUMBNAIL].getPath());
        MyContactsCmd cmd = new MyContactsCmd(context);
        try {
            long res = cmd.insert(contact);
            if(res > 0) {
                Toast.makeText(context, "Contact saved",
                        Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK);
                finishActivity(MainActivity.REQUEST_CODE_NEW_CONTACT);
            }
            else
                Toast.makeText(context, "Contact not saved.",
                        Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Exception::" + ex.getMessage());
            Toast.makeText(context, "Ops an error has occurred.",
                    Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE
                && resultCode == RESULT_OK
                && photoFiles[INDEX_IMG_THUMBNAIL] != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(
                            photoFiles[INDEX_IMG_FULL].getAbsolutePath());

            mImageViewContact.setImageBitmap(bitmap);
        }
    }
}
