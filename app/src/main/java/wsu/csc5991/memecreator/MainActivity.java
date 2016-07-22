package wsu.csc5991.memecreator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //----------------------------------------------------------------
    // Constants
    //----------------------------------------------------------------

    // Declare application constants
    private final String APP_NAME = "Camera";
    private final String APP_VERSION = "1.0";
    public final static String MEME = "wsu.csc5991.memecreator.MEME";

    // Declare image constants
    private static final int REQUEST_CODE = 17; // for camera
    private static final int REQUEST_CODE2 = 27;
    private static final int REQUEST_CODE3 = 22; // for gallery

    //----------------------------------------------------------------
    // Variables
    //----------------------------------------------------------------

    // Declare control variables
    private TextView tvThumbnail;
    private TextView tvFolder;
    private TextView tvPhoto;
    private TextView tvProperties;

    // Declare other variables
    String[] cameras;
    private Bitmap imageBitmap;
    private String imageFolder;

    //----------------------------------------------------------------
    // onCreateOptionsMenu
    // Purpose: creates the menu
    //----------------------------------------------------------------
    // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_main, menu);
        return true;
    }

    //----------------------------------------------------------------
    // onOptionsItemSelected
    // Purpose: when menu selected this executes
    //----------------------------------------------------------------

     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            goToSettings(findViewById(R.id.settings));
            return true;
        }

         if (id == R.id.help) {
             goToHelp(findViewById(R.id.help));
             return true;
         }

         if (id == R.id.about) {
             goToAbout(findViewById(R.id.about));
             return true;
         }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");
    }

    //going to help activity
    public void goToHelp(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    //going to settings activity
    public void goToSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    //going to about activity
    public void goToAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


    //----------------------------------------------------------------
    // takePicture
    //----------------------------------------------------------------
    public void takePicture(View view)
    {
        Intent intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentPhoto, REQUEST_CODE);
    }

    //----------------------------------------------------------------
    // goToGallery
    //----------------------------------------------------------------
    public void goToGallery(View view){

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE3);

    }

    //----------------------------------------------------------------
    // onActivityResult
    //----------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent)
    {
        // Test if activity ended successfully and is camera activity
        if (resultCode != RESULT_OK)
        {
            // Show error message
            Toast.makeText(getApplicationContext(), APP_NAME +
                    " error accessing gallery or taking picture or video with device's " +
                    "camera application.", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == REQUEST_CODE) // for camera
        {
            Bitmap reSizedBitmap;
            imageBitmap = (Bitmap) intent.getExtras().get("data");
            BitmapDrawable drawable = new BitmapDrawable(getResources(), imageBitmap);
            goToMemeActivity(imageBitmap);


        }
        else if (requestCode == REQUEST_CODE3) //for gallery
        {
            Uri targetUri = intent.getData();
            // textTargetUri.setText(targetUri.toString());
            goToMemeActivity(targetUri);
        }
    }

    public void goToMemeActivity(Bitmap bitmap){
        Intent intent = new Intent(this, MemeActivity.class);
        intent.putExtra("Image", bitmap);
        startActivity(intent);
    }

    public void goToMemeActivity(Uri targetUri){
        Intent intent = new Intent(this, MemeActivity.class);
        intent.putExtra("uri", targetUri);
        startActivity(intent);
    }
}
