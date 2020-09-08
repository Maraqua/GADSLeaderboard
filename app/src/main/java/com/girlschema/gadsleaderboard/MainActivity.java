package com.girlschema.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.girlschema.gadsleaderboard.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mActivityMainBinding;

    //number of pages
    private static  final  int NUM_PAGES = 2;
    //number of fragments
    private  static final  int LEARNING_LEADERS = 0;
    private  static  final int SKILL_LEADERS =1;
    //swipe
    private  ViewPager mViewPager;
    //provide pages to the view
    private PagerAdapter mPageAdapter;
    private  Button mSubmitProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mActivityMainBinding.getRoot();
        View view  = mActivityMainBinding.getRoot();
        setContentView(view);
        //toolbar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        //go to ProjectSubmitActivity
        mSubmitProject = findViewById(R.id.submitProjectBtn);

        //instantiate the view pager and pager adapter
        mViewPager = mActivityMainBinding.viewPager;
        mPageAdapter = new MainActivityAdapter(getSupportFragmentManager());
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setAdapter(mPageAdapter);
        TabLayout tabLayout = mActivityMainBinding.tabLayout;
        tabLayout.setupWithViewPager(mViewPager);

     
        
    }
    public void projectSubmit(View view) {
        mSubmitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProjectSubmit.class);
                startActivity(intent);
            }
        });
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