package com.boomarang.cbstop.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.boomarang.cbstop.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;


public class CoverActivity extends AppCompatActivity {

    @BindView(R.id.iv_cover_background)  ImageView iv_cover_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int BLUR_SAMPLING = 10;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.img_cover)
                .bitmapTransform(new CenterCrop(getApplicationContext()),
                        new BlurTransformation(getApplicationContext(), BLUR_SAMPLING))
                .into(iv_cover_background);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }},1500);
    }


}
