package com.boomarang.cbstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login_id)         EditText et_login_id;
    @BindView(R.id.et_login_pw)         EditText et_login_pw;
    @BindView(R.id.iv_login_background) ImageView iv_login_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int BLUR_SAMPLING = 10;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.img_cover)
                .bitmapTransform(new CenterCrop(getApplicationContext()),
                        new BlurTransformation(getApplicationContext(), BLUR_SAMPLING))
                .into(iv_login_background);
    }


    // 임시 버튼 - OAuth 로 변경
    @OnClick(R.id.btn_login)
    public void OnLoginButtonClicked()
    {
        String userId = et_login_id.getText().toString();
        String userPw = et_login_pw.getText().toString();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("userId", userId)
                .putExtra("userPw", userPw)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}
