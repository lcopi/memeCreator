package wsu.csc5991.memecreator;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MemeActivity extends AppCompatActivity implements TopSectionFragment.TopSectionListener{

    // Declare other variables
    String[] cameras;
    private Bitmap imageBitmap;
    private String imageFolder;

    // Declare application constants
    private final String APP_NAME = "Camera";
    private final String APP_VERSION = "1.0";
    public final static String MEME = "wsu.csc5991.memecreator.MEME";


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
            goToHelp(findViewById(R.id.about));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);

        getSupportActionBar().setTitle("Create Meme");
        Intent intent = getIntent();

        if (intent.getExtras().containsKey("uri")){
            Bundle bd = getIntent().getExtras();
            Uri uri = bd.getParcelable("uri");
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                BitmapDrawable drawable = new BitmapDrawable(getResources(), imageBitmap);
                BottomPictureFragment bottomFragment = (BottomPictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
                bottomFragment.seBbottomFragmentBackgroundPicture(drawable);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            imageBitmap = (Bitmap) intent.getParcelableExtra("Image");
            BitmapDrawable drawable = new BitmapDrawable(getResources(), imageBitmap);
            BottomPictureFragment bottomFragment = (BottomPictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
            bottomFragment.seBbottomFragmentBackgroundPicture(drawable);
        }
    }

    //Called by Top Fragment when the button is clicked
    @Override
    public void createMeme(String top, String bottom) {
        BottomPictureFragment bottomFragment = (BottomPictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        bottomFragment.setMemeText(top,bottom);
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
    // savePicture
    //----------------------------------------------------------------
    public void savePicture(View view)
    {
        // Declare variables
        String imageFileName;
        FileOutputStream fileOut = null;
        File imageFile;
        Bitmap imageBitmapFull;

        // Set variables
        imageFileName = imageFolder + File.separator + APP_NAME +
                "_PHOTO_";

        // Attempt to create file from image and show image from file
        try
        {
            // Create file from image
            fileOut = new FileOutputStream(imageFileName);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    fileOut);
            fileOut.close();
        }
        catch (Exception e)
        {
            // Show error message
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(APP_NAME + " Message");

            builder.setMessage("Error creating file from image or " +
                    "showing image from file.");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }
    }


}


