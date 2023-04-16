package com.example.geoquiz;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    private TextView mStartTitle ;
    private Button mStartOpen;
    private Button mStartAdd;

    final private static String QUES_LENGTH = "com.example.geoquize.start.ques.length";

    private QuizViewModel mQuizViewModel;

    private static final int REQUEST_ACTIVITY_ADD = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mStartTitle = findViewById(R.id.start_title);
        mStartOpen = findViewById(R.id.start_open);
        mStartAdd = findViewById(R.id.start_add);

        ViewModelProvider provider = new ViewModelProvider(this);
        mQuizViewModel = provider.get(QuizViewModel.class);
        mQuizViewModel.creatQuesList(getApplicationContext());
        if (savedInstanceState !=null){
            int length = savedInstanceState.getInt(QUES_LENGTH);
            for (int i = 7; i < length ; i++) {
                mQuizViewModel.addClass((Question) savedInstanceState.getSerializable(String.format("ques%d",i)));

            }
        }

        mStartTitle.setText(String.format("题库中共有%d题", mQuizViewModel.getLength()));

        mStartOpen.setOnClickListener(view -> {
            Intent i = MainActivity.newIntent(StartActivity.this);
            i.putExtra("length",mQuizViewModel.getLength());
            for (int j = 7; j <mQuizViewModel.getLength() ; j++) {
                i.putExtra(String.format("ques%d",j),mQuizViewModel.getClass(j));
            }
            startActivity(i);
        });

        mStartAdd.setOnClickListener(view -> {
            Intent i = AddActivity.newIntent(StartActivity.this);
            startActivityForResult(i,REQUEST_ACTIVITY_ADD);
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ACTIVITY_ADD) {
            if (resultCode == Activity.RESULT_OK) {
                String ques = AddActivity.getQues(data);
                Boolean ans = AddActivity.getAns(data);
                mQuizViewModel.addQues(ques,ans);
                mStartTitle.setText(String.format("题库中共有%d题", mQuizViewModel.getLength()));
            }

        };

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int length = mQuizViewModel.getLength();
        outState.putInt(QUES_LENGTH,length);
        for (int i = 7; i < length ; i++) {
            outState.putSerializable(String.format("ques%d",i),mQuizViewModel.getClass(i));
        }
    }
}