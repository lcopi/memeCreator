package wsu.csc5991.memecreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HelpActivity extends AppCompatActivity {
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
            return true;
        }

        if (id == R.id.help) {
            goToHelp(findViewById(R.id.help));
            //takePicture();
            return true;
        }

        if (id == R.id.about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setTitle("Help");
    }

    //going to help activity
    public void goToHelp(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
    }
}
