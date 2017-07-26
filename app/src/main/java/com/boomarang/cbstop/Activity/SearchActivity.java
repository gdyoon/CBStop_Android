package com.boomarang.cbstop.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.boomarang.cbstop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.sp_search_category)
    Spinner sp_search_category;
    private String searchedValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        AddItemListToSpinner();
    }

    private void AddItemListToSpinner()
    {
        ArrayAdapter<CharSequence> adapter
                = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);
        sp_search_category.setAdapter(adapter);

    }

    @OnClick(R.id.btn_search_search)
    public void OnSearchButtonClicked()
    {
        int position = sp_search_category.getSelectedItemPosition();

        Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
    }

}
