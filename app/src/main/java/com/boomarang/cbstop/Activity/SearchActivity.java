package com.boomarang.cbstop.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.boomarang.cbstop.Api.ApiUtils;
import com.boomarang.cbstop.Api.PlaceList;
import com.boomarang.cbstop.Api.PlaceService;
import com.boomarang.cbstop.Constants.Category;
import com.boomarang.cbstop.Constants.Resource;
import com.boomarang.cbstop.CustomView.Place;
import com.boomarang.cbstop.CustomView.PlaceView;
import com.boomarang.cbstop.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private final String LOG_TAG = SearchActivity.class.getSimpleName();

    @BindView(R.id.sp_search_category)  Spinner sp_search_category;
    @BindView(R.id.tb_search_bar)       Toolbar tb_search_bar;
    @BindView(R.id.et_search_search)    EditText et_search_search;
    @BindView(R.id.layout_search_list)  LinearLayout layout_search_list;

    private PlaceService mPlaceService;
    private final int MENU_PLACE_REGION = 0;
    private final int MENU_PLACE_NAME   = 1;
    private final int MENU_PLACE_ADDR   = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mPlaceService = ApiUtils.getPlaceService();
        AddItemListToSpinner();
        SetOnMenuItemClickListener();

        Intent intent = getIntent();
        String searchedValue = intent.getStringExtra("searchValue");

        et_search_search.setText(searchedValue);
    }

    private void AddItemListToSpinner()
    {
        ArrayAdapter<CharSequence> adapter
                = ArrayAdapter.createFromResource(this, R.array.category, R.layout.spinner_category_item);
        sp_search_category.setAdapter(adapter);
    }

    private void SetOnMenuItemClickListener()
    {
        tb_search_bar.inflateMenu(R.menu.menu_main_search);
        tb_search_bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_main_search){
                    int position = sp_search_category.getSelectedItemPosition();
                    String searchedValue = et_search_search.getText().toString();

                    switch (position)
                    {
                        case MENU_PLACE_REGION:
                            LoadPlaceData(MENU_PLACE_REGION, searchedValue);
                            break;
                        case MENU_PLACE_NAME:
                            LoadPlaceData(MENU_PLACE_NAME, searchedValue);
                            break;
                        case MENU_PLACE_ADDR:
                            LoadPlaceData(MENU_PLACE_ADDR, searchedValue);
                            break;
                    }
                }
                return false;
            }
        });
    }


    private void LoadPlaceData(int currentMenuIndex, String... params)
    {
        if(currentMenuIndex == MENU_PLACE_REGION)
        {
            if      (params.length == 1) LoadPlace(params[0]);
            else if (params.length == 2) LoadPlace(params[0], params[1]);
        }
        else if(currentMenuIndex == MENU_PLACE_NAME)
        {
            LoadPlaceName(params[0]);
        }
        else if(currentMenuIndex == MENU_PLACE_ADDR)
        {
            LoadPlaceAddress(params[0]);
        }
    }


    private void LoadPlace(String paramRegion)
    {
        mPlaceService.getPlaces(paramRegion).enqueue(new Callback<PlaceList>() {
            @Override
            public void onResponse(Call<PlaceList> call, Response<PlaceList> response) {

                if(response.isSuccessful()) {
                    layout_search_list.removeAllViews();

                    List<Place> retPlaceList = response.body().getPlaces();

                    for(Place element : retPlaceList)
                    {
                        int resourceId = CategoryToImageResource(element.getCategory());
                        AddPlaceView(resourceId, element);
                    }
                }
                else{

                }

            }

            @Override
            public void onFailure(Call<PlaceList> call, Throwable t) {
                Log.d(LOG_TAG, "response error : " + t.getMessage());
            }
        });
    }

    private void LoadPlace(String paramRegion, String paramCategory)
    {
        mPlaceService.getPlaces(paramRegion, paramCategory).enqueue(new Callback<PlaceList>() {
            @Override
            public void onResponse(Call<PlaceList> call, Response<PlaceList> response) {

                if(response.isSuccessful()) {
                    layout_search_list.removeAllViews();

                    List<Place> retPlaceList = response.body().getPlaces();

                    for(Place element : retPlaceList)
                    {
                        int resourceId = CategoryToImageResource(element.getCategory());
                        AddPlaceView(resourceId, element);
                    }
                }
                else{

                }

            }

            @Override
            public void onFailure(Call<PlaceList> call, Throwable t) {
                Log.d(LOG_TAG, "response error : " + t.getMessage());
            }
        });
    }

    private void LoadPlaceName(String paramName)
    {
        mPlaceService.getPlacesWithName(paramName).enqueue(new Callback<PlaceList>() {
            @Override
            public void onResponse(Call<PlaceList> call, Response<PlaceList> response) {

                if(response.isSuccessful()) {
                    layout_search_list.removeAllViews();

                    List<Place> retPlaceList = response.body().getPlaces();

                    for(Place element : retPlaceList)
                    {
                        int resourceId = CategoryToImageResource(element.getCategory());
                        AddPlaceView(resourceId, element);
                    }
                }
                else{

                }

            }

            @Override
            public void onFailure(Call<PlaceList> call, Throwable t) {
                Log.d(LOG_TAG, "response error : " + t.getMessage());
            }
        });
    }

    private void LoadPlaceAddress(String paramAddress)
    {
        mPlaceService.getPlacesWithAddr(paramAddress).enqueue(new Callback<PlaceList>() {
            @Override
            public void onResponse(Call<PlaceList> call, Response<PlaceList> response) {

                if(response.isSuccessful()) {
                    layout_search_list.removeAllViews();

                    List<Place> retPlaceList = response.body().getPlaces();

                    for(Place element : retPlaceList)
                    {
                        int resourceId = CategoryToImageResource(element.getCategory());
                        AddPlaceView(resourceId, element);
                    }
                }
                else{

                }

            }

            @Override
            public void onFailure(Call<PlaceList> call, Throwable t) {
                Log.d(LOG_TAG, "response error : " + t.getMessage());
            }
        });
    }


    private int CategoryToImageResource(String paramCategory)
    {
        int returnResourceId = 0;

        switch(paramCategory)
        {
            case Category.HISTORIC: returnResourceId = 0; break;
            case Category.CAMPING:  returnResourceId = 1; break;
            case Category.TREE:     returnResourceId = 2; break;
            case Category.EVENT:    returnResourceId = 3; break;
            case Category.FOOD:     returnResourceId = 4; break;
            case Category.BED:      returnResourceId = 5; break;
        }

        return Resource.ICON_CATEGORY_RESOURCE[returnResourceId];
    }

    private void AddPlaceView(int paramResourceId, Place paramPlace)
    {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.gravity = Gravity.CENTER;

        final PlaceView placeView = new PlaceView(getApplicationContext());
        placeView.setLayoutParams(param);
        placeView.setmPlace(paramPlace);
        placeView.setmPlaceImageResourceId(paramResourceId);
        placeView.setmPlaceName(paramPlace.getName());
        placeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place place = placeView.getmPlace();
                Intent intent = new Intent(getApplicationContext(), SearchDetailActivity.class);
                intent.putExtra("place", place);

                startActivity(intent);
            }
        });

        layout_search_list.addView(placeView);
    }


    @OnClick({R.id.layout_category_historic,
            R.id.layout_category_camping,
            R.id.layout_category_tree,
            R.id.layout_category_event,
            R.id.layout_category_food,
            R.id.layout_category_bed
    })
    public void OnCategoryButtonClicked(View paramView)
    {
        int position = sp_search_category.getSelectedItemPosition();
        String region = et_search_search.getText().toString();
        String selectedCategory = null;


        switch(paramView.getId())
        {
            case R.id.layout_category_historic: selectedCategory = Category.HISTORIC; break;
            case R.id.layout_category_camping:  selectedCategory = Category.CAMPING;  break;
            case R.id.layout_category_tree:     selectedCategory = Category.TREE;     break;
            case R.id.layout_category_event:    selectedCategory = Category.EVENT;    break;
            case R.id.layout_category_food:     selectedCategory = Category.FOOD;     break;
            case R.id.layout_category_bed:      selectedCategory = Category.BED;      break;
        }

        SearchCategory(position, region, selectedCategory);
    }




    private void SearchCategory(int position, String region, String selectedCategory)
    {
        // 카테고리만 선택 됐을 때
        if(selectedCategory != null && position == MENU_PLACE_REGION && region.equals(""))
        {
            PrintToast(selectedCategory + "를 기준으로 검색 합니다");
            LoadPlaceData(MENU_PLACE_REGION, region, selectedCategory);
        }
        // 스피너에서 현재 선택한 것이 지역이면서 카테고리가 선택됐을 때
        else if(selectedCategory != null && position == MENU_PLACE_REGION)
        {
            PrintToast(region +"와(과) " + selectedCategory + "(을)를 기준으로 검색 합니다");
            LoadPlaceData(MENU_PLACE_REGION, region, selectedCategory);
        }
        // 둘다 선택이 안됐을 때
        else
        {
            PrintToast("지역과 카테고리를 선택해주세요");
        }
    }



    private void PrintToast(String message)
    {
        Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_SHORT).show();
    }
}
