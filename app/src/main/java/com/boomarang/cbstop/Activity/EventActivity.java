package com.boomarang.cbstop.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.boomarang.cbstop.Constants.Resource;
import com.boomarang.cbstop.R;
import com.boomarang.cbstop.event.BedFragment;
import com.boomarang.cbstop.event.CampingFragment;
import com.boomarang.cbstop.event.EventFragment;
import com.boomarang.cbstop.event.FoodFragment;
import com.boomarang.cbstop.event.HistoricFragment;
import com.boomarang.cbstop.event.TreeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {

    @BindView(R.id.vp_event)            ViewPager viewPager;
    @BindView(R.id.layout_event_tab)    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);



        Fragment[] arrFragments = new Fragment[6];
        arrFragments[0] = new HistoricFragment();
        arrFragments[1] = new CampingFragment();
        arrFragments[2] = new TreeFragment();
        arrFragments[3] = new EventFragment();
        arrFragments[4] = new FoodFragment();
        arrFragments[5] = new BedFragment();

        EventPagerAdapter adapter = new EventPagerAdapter(getSupportFragmentManager(), arrFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        for(int i=0; i<tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(Resource.ICON_CATEGORY_RESOURCE[i]);
        }
    }



    class EventPagerAdapter extends FragmentPagerAdapter {

        Fragment[] arrFragments;

        public EventPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
            super(fm);
            this.arrFragments = arrFragments;


        }

        @Override
        public Fragment getItem(int position) {
            return arrFragments[position];
        }

        @Override
        public int getCount() {
            return arrFragments.length;
        }
    }
}
