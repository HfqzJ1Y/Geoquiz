package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AddActivity extends AppCompatActivity {
    private EditText mAddText;

    private Button mAddBtn;

    private TimerTask task=new TimerTask() {
        @Override
        public void run() {
            finish();
        }
    };

    public static Intent newIntent(AppCompatActivity activity){
        Intent i = new Intent(activity,AddActivity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        mAddText = findViewById(R.id.add_text);

        mAddBtn = findViewById(R.id.add_btn);

        mAddBtn.setOnClickListener(view -> {
            mAddText.getText().toString();
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            Timer timer = new Timer();
            timer.schedule(task,1000);
        });



    }
}