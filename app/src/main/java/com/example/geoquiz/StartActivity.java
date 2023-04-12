package com.example.geoquiz;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private TextView mStartTitle ;
    private Button mStartOpen;
    private Button mStartAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mStartTitle = findViewById(R.id.start_title);
        mStartOpen = findViewById(R.id.start_open);
        mStartAdd = findViewById(R.id.start_add);

        mStartOpen.setOnClickListener(view -> {
            Intent i = MainActivity.newIntent(StartActivity.this);
            startActivity(i);
        });

        mStartAdd.setOnClickListener(view -> {
            Intent i = AddActivity.newIntent(StartActivity.this);
            startActivityForResult(i,0);
        });



    }
}