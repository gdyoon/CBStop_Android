package com.boomarang.cbstop.CustomView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boomarang.cbstop.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by YOONGOO on 2017-07-26.
 */

public class PlaceView extends LinearLayout {
    CircleImageView civ_item_place;
    TextView tv_item_place;
    private int mPlaceImageResourceId;
    private Place mPlace;



    public PlaceView(Context context) {
        super(context);

        InitializeView();
    }

    private void InitializeView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.item_place, this, false);
        addView(view);

        civ_item_place = (CircleImageView) findViewById(R.id.civ_item_place);
        tv_item_place = (TextView) findViewById(R.id.tv_item_place);
    }

    public int getmPlaceImageResourceId() {
        return mPlaceImageResourceId;
    }

    public void setmPlaceImageResourceId(int mPlaceImageResourceId) {
        this.mPlaceImageResourceId = mPlaceImageResourceId;
        civ_item_place.setImageResource(this.mPlaceImageResourceId);
    }

    public void setmPlaceName(String mPlaceName) {
        mPlace.setName(mPlaceName);
        tv_item_place.setText(this.mPlace.getName());
    }

    public Place getmPlace() {
        return mPlace;
    }

    public void setmPlace(Place mPlace) {
        this.mPlace = mPlace;
    }
}