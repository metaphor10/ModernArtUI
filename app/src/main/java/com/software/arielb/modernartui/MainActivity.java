package com.software.arielb.modernartui;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private static final String TAG=MainActivity.class.getName();
    private RelativeLayout board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board=(RelativeLayout)findViewById(R.id.one_view_to_bind_them_all);
        SeekBar seekBar=(SeekBar)findViewById(R.id.seekbar);

        View topRight=(View) findViewById(R.id.topright);
        View topCenter=(View) findViewById(R.id.topcenter);
        View topLeft=(View) findViewById(R.id.topleft);
        View belowTopAboveMiddle=(View) findViewById(R.id.belowtopabovemddle);
        View middle=(View) findViewById(R.id.middle);
        View bottomLeft=(View) findViewById(R.id.bottomOfPlace);
        View bottomRight=(View) findViewById(R.id.bottomright);
        final ArrayList<View> myViews=new ArrayList<View>();
        myViews.add(topRight);
        myViews.add(topCenter);
        myViews.add(topLeft);
        myViews.add(belowTopAboveMiddle);
        myViews.add(middle);
        myViews.add(bottomLeft);
        myViews.add(bottomRight);

        int topRightColor=((ColorDrawable) topRight.getBackground()).getColor();
        int topCenterColor=((ColorDrawable) topCenter.getBackground()).getColor();
        int topLeftColor=((ColorDrawable) topLeft.getBackground()).getColor();
        int belowTopAboveMiddleColor=((ColorDrawable) belowTopAboveMiddle.getBackground()).getColor();
        int middleColor=((ColorDrawable) middle.getBackground()).getColor();
        int bottomLeftColor=((ColorDrawable) bottomLeft.getBackground()).getColor();
        int bottomRightColor=((ColorDrawable) bottomRight.getBackground()).getColor();
        final int []myColors=new int[7];
        myColors[0]=topRightColor;
        myColors[1]=topCenterColor;
        myColors[2]=topLeftColor;
        myColors[3]=belowTopAboveMiddleColor;
        myColors[4]=middleColor;
        myColors[5]=bottomLeftColor;
        myColors[6]=bottomRightColor;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int deltaColor=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                deltaColor=progress;
                float []hsv=new float[3];
                for (int i=0;i<7;i++){
                    Color.colorToHSV(myColors[i], hsv);
                    hsv[0] = hsv[0] + deltaColor;
                    hsv[0] = hsv[0] % 360;
                    myViews.get(i).setBackgroundColor(Color.HSVToColor(Color.alpha(myColors[i]), hsv));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
        if (id == R.id.additional_information) {
            Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
            DialogFragment additionalInfoFragment=new AdditionalInfoFragment();
            additionalInfoFragment.show(getFragmentManager(),"Additonal Info");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
