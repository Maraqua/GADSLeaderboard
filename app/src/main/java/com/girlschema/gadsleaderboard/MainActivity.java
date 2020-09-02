package com.girlschema.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.girlschema.gadsleaderboard.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends FragmentActivity {
    private ActivityMainBinding activityMainBinding;

    //number of pages
    private static  final  int NUM_PAGES = 2;
    //number of fragments
    private  static final  int LEARNING_LEADERS = 0;
    private  static  final int SKILL_LEADERS =1;
    //swipe
    private  ViewPager mViewPager;
    //provide pages to the view
    private PagerAdapter mPageAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        activityMainBinding.getRoot();
        View view  = activityMainBinding.getRoot();
        setContentView(view);
        //instantiate the view pager and pager adapter
        mViewPager = activityMainBinding.viewPager;
        mPageAdapter = new MainActivityAdapter(getSupportFragmentManager());
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setAdapter(mPageAdapter);
        TabLayout tabLayout = activityMainBinding.tabLayout;
        tabLayout.setupWithViewPager(mViewPager);

     
        
    }

    @Override
    public void onBackPressed() {
        if(mViewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 2
     * ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class MainActivityAdapter extends FragmentStatePagerAdapter {
        public MainActivityAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case LEARNING_LEADERS:
                    return new LeadersBoardFragment();
                case SKILL_LEADERS:
                    return new SkillIqFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case LEARNING_LEADERS:
                    return "Learning Leaders";
                case SKILL_LEADERS:
                    return "Skill IQ Leaders";
            }
            return null;
        }
    }
}