package com.girlschema.gadsleaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.girlschema.gadsleaderboard.databinding.ActivityProjectSubmitBinding;

public class ProjectSubmit extends AppCompatActivity {
        private ActivityProjectSubmitBinding mActivityProjectSubmitBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mActivityProjectSubmitBinding = ActivityProjectSubmitBinding.inflate(getLayoutInflater());
      mActivityProjectSubmitBinding.getRoot();
        View view = mActivityProjectSubmitBinding.getRoot();
        setContentView(view);
        //custom toolbar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_submit);
        //Confirm submit
    }
    public void confirmButton (View view){
        mActivityProjectSubmitBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectSubmit.this,"Button clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}