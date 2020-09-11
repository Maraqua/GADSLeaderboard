package com.girlschema.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.girlschema.gadsleaderboard.databinding.ActivityProjectSubmitBinding;
import com.girlschema.gadsleaderboard.network.ApiInterface;
import com.girlschema.gadsleaderboard.network.RetrofitClientInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectSubmit extends AppCompatActivity {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/u/0/d/e/")
            .addConverterFactory(GsonConverterFactory.create());
    public static Retrofit sRetrofit  = retrofitBuilder.build();
        private ActivityProjectSubmitBinding mActivityProjectSubmitBinding;
    private TextView mTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mActivityProjectSubmitBinding = ActivityProjectSubmitBinding.inflate(getLayoutInflater());
      mActivityProjectSubmitBinding.getRoot();
        View view = mActivityProjectSubmitBinding.getRoot();
        setContentView(view);
        final EditText fName= mActivityProjectSubmitBinding.editTextFname;
        final EditText lName = mActivityProjectSubmitBinding.editTextLname;
        final EditText eMail = mActivityProjectSubmitBinding.editTextEmail;
        final EditText lInk=  mActivityProjectSubmitBinding.editTextLink;
        mTextView = mActivityProjectSubmitBinding.textView;
        mTextView.setPaintFlags(mTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //custom toolbar

        mActivityProjectSubmitBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProjectSubmit.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Confirm submit
        mActivityProjectSubmitBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( fName.length() == 0)
                {
                    fName.requestFocus();
                    fName.setError("Empty Field");

                }else if(lName.length() == 0)
                {
                    lName.requestFocus();
                    lName.setError("Empty Field");
                }
                else if(eMail.length() == 0)
                {
                    eMail.requestFocus();
                    eMail.setError("Empty Field");
                }
                else if(lInk.length() == 0)
                {
                    lInk.requestFocus();
                    lInk.setError("Empty Field");
                }else {
                    mActivityProjectSubmitBinding.showFields.setVisibility(View.INVISIBLE);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProjectSubmit.this);
                    final AlertDialog dialogConfirm = alertDialogBuilder.create();
                    dialogConfirm.show();
                    final LayoutInflater layoutInflater = ProjectSubmit.this.getLayoutInflater();
                    final View alertView = layoutInflater.inflate(R.layout.confirm_submit, null);
                    dialogConfirm.getWindow().setContentView(alertView);
                    ImageView cancelBtn = alertView.findViewById(R.id.ImageViewCancel);
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialogConfirm.dismiss();

                        }
                    });

                    Button confirmBtn = alertView.findViewById(R.id.confirmBtn);
                    confirmBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogConfirm.dismiss();
                            executeSubmitProject(
                                    fName.getText().toString(),
                                    lName.getText().toString(),
                                    eMail.getText().toString(),
                                    lInk.getText().toString()
                            );


                        }
                    });

                }
            }
        });

    }



    private void executeSubmitProject(String fname,String lname,String emailAddress,String linkToProject) {
        ApiInterface apiInterface = sRetrofit.create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.submitProjectForm(
                fname,
                lname,
                emailAddress,
                linkToProject
        );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
              if (response.isSuccessful()){
                  mActivityProjectSubmitBinding.showFields.setVisibility(View.INVISIBLE);
                  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProjectSubmit.this);
                  AlertDialog dialogSuccess = alertDialogBuilder.create();
                  dialogSuccess.show();
                  LayoutInflater layoutInflater = ProjectSubmit.this.getLayoutInflater();
                  View view = layoutInflater.inflate(R.layout.success_dialog,null);
                  dialogSuccess.getWindow().setContentView(view);

              }

            }



            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mActivityProjectSubmitBinding.showFields.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProjectSubmit.this);
                AlertDialog dialogError = alertBuilder.create();
                dialogError.show();
                LayoutInflater layoutInflater = ProjectSubmit.this.getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.error_dialog,null);
                dialogError.getWindow().setContentView(view);

            }
        });

    }
}