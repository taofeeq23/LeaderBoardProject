package com.gads.project.leaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity implements OnClickListener, SubmitDialog.ConfirmDialogListener {

    private static final String TAG = "SubmissionActivity";
    EditText etFirstName, etLastName, etEmailAddress, etGithubLink;
    Button Submit;
    HourAndSkillsApi hourAndSkillsApi;
    Retrofit retrofit;
    TextView textView;

    private boolean mIsConfirmed = false;

    Dialog successDialog, failedDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        Toolbar toolbar = findViewById(R.id.awesome_toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                finish();
            }
            });

            etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        etEmailAddress = findViewById(R.id.emailAddress);
        etGithubLink = findViewById(R.id.etGit_link);
        Submit = findViewById(R.id.btnSubmit);
        Submit.setOnClickListener(this);

        successDialog = new Dialog(this);
        failedDialog = new Dialog(this);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        hourAndSkillsApi = retrofit.create(HourAndSkillsApi.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String emailAddress = etEmailAddress.getText().toString();
                String githubLink = etGithubLink.getText().toString();


                if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || githubLink.isEmpty()) {
                    Toast.makeText(this, "Fill out all the fields", Toast.LENGTH_SHORT).show();

                } else {
                    openSubmitDialog();
                }
                break;
        }
    }

    public void createPostRequest() {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String emailAddress = etEmailAddress.getText().toString();
        String githubLink = etGithubLink.getText().toString();

        if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || githubLink.isEmpty()){
            Toast.makeText(this, "Fill out all the fields", Toast.LENGTH_SHORT).show();
        }

        Call<Void> submitModelCall = hourAndSkillsApi.createPost(emailAddress, firstName, lastName, githubLink);
        submitModelCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    openErrorDialog();
                    return;
                }
                Log.d(TAG, "onResponse: Coded Message " + response.code());
                clear();
                openSuccessDialog();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                openErrorDialog();
                Log.d(TAG, "onFailure: Failed to Upload " + t.getMessage());

            }
        });
    }

    private void openSubmitDialog() {
        SubmitDialog confirmDialog = new SubmitDialog();
        confirmDialog.show(getSupportFragmentManager(), "Confirm");
    }

    private void openErrorDialog() {
        ErrorDialog errorDialog = new ErrorDialog();
        errorDialog.show(getSupportFragmentManager(), "Error");
    }

    private void openSuccessDialog() {
        SuccessDialog successDialog = new SuccessDialog();
        successDialog.show(getSupportFragmentManager(), "Success");
    }

    public static void isEmpty(String string){
        string.equals("");
    }

    @Override
    public void confirmSubmission(boolean isConfirmed) {
        mIsConfirmed = isConfirmed;
        Log.d("Confirm", mIsConfirmed + "Interface");
        if (mIsConfirmed)
            createPostRequest();
    }

    public void clear(){
        etFirstName.setText("");
        etLastName.setText("");
        etEmailAddress.setText("");
        etGithubLink.setText("");
    }
}