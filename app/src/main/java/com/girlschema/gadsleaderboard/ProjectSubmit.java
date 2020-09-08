package com.girlschema.gadsleaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.girlschema.gadsleaderboard.databinding.ActivityProjectSubmitBinding;
import com.girlschema.gadsleaderboard.network.ApiInterface;
import com.girlschema.gadsleaderboard.network.RetrofitClientInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectSubmit extends AppCompatActivity {
        private ActivityProjectSubmitBinding mActivityProjectSubmitBinding;
        FragmentManager mFragmentManager = getSupportFragmentManager();
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mActivityProjectSubmitBinding = ActivityProjectSubmitBinding.inflate(getLayoutInflater());
      mActivityProjectSubmitBinding.getRoot();
        View view = mActivityProjectSubmitBinding.getRoot();
        setContentView(view);
        mTextView = mActivityProjectSubmitBinding.textView;
        mTextView.setPaintFlags(mTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG );
        //custom toolbar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_submit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Confirm submit
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.:
//                finish();
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }
    public void confirmButton (View view){
        mActivityProjectSubmitBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ProjectSubmit.this,"Button clicked",Toast.LENGTH_SHORT).show();
            submitProject(
                    mActivityProjectSubmitBinding.editTextFname.getText().toString(),
                    mActivityProjectSubmitBinding.editTextEmail.getText().toString(),
                    mActivityProjectSubmitBinding.editTextEmail.getText().toString(),
                    mActivityProjectSubmitBinding.editTextLink.getText().toString()
            );


            }
        });
    }

    private void submitProject(String emailAddress,String fname,String lname,String linkToProject) {
        ApiInterface submitForm = RetrofitClientInstance.getRetrofitInstance().postClient().create(ApiInterface.class);
        Call<ResponseBody> call = submitForm.submitProjectForm(
                fname,
                lname,
                emailAddress,
                linkToProject
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(ProjectSubmit.this, "submitted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProjectSubmit.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}