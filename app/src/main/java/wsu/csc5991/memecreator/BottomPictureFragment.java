package wsu.csc5991.memecreator;

import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Ayush on 6/19/2016.
 */
public class BottomPictureFragment extends android.support.v4.app.Fragment {

    private static TextView topMemeText;
    private static TextView bottomMemeText;
    private RelativeLayout rlBottomPicFragement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_picture_fragment,container,false);
        topMemeText=(TextView)view.findViewById(R.id.tvTopMemeText);
        bottomMemeText=(TextView)view.findViewById(R.id.tvBottomMemeText);
        rlBottomPicFragement=(RelativeLayout) view.findViewById(R.id.rlBottomPicFragment);
        return view;
    }

    public void  setMemeText(String top, String bottom){
        topMemeText.setText(top);
        bottomMemeText.setText(bottom);
    }

    //vivek added for camera
    public void seBbottomFragmentBackgroundPicture(BitmapDrawable drawableIN){
        rlBottomPicFragement.setBackgroundDrawable(drawableIN);

    }

}
