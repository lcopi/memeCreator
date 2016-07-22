package wsu.csc5991.memecreator;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ayush on 6/19/2016.
 */
public class TopSectionFragment extends Fragment {

    private static EditText topText;
    private static EditText bottomText;

    TopSectionListener activityCommander;

    public interface TopSectionListener{
        public void createMeme(String top, String bottom);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander=(TopSectionListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_section_fragment,container,false);

        topText = (EditText) view.findViewById(R.id.etTopText);
        bottomText = (EditText) view.findViewById(R.id.etBottomText);
        final Button button = (Button) view.findViewById(R.id.btn);

        button.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );

        return view;
    }

    //Calls this when the button is clicked
    public void buttonClicked(View view){
        activityCommander.createMeme(topText.getText().toString(),bottomText.getText().toString());
    }


}
