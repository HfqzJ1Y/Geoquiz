package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class AddActivity extends AppCompatActivity {
    private EditText mAddText;
    private RadioButton mTureChoice;
    private Button mAddBtn;

    private static String ADD_QUES = "com.example.geoquize.add.ques";

    private static String ADD_ANS = "com.example.geoquize.add.ans";

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
    //设置返回值
    private void setAddQues(String ques,boolean ans){
        Intent data = new Intent();
        data.putExtra(ADD_QUES,ques);
        data.putExtra(ADD_ANS,ans);

        setResult(Activity.RESULT_OK,data);
    }
    //获取新问题
    public static String getQues(Intent result){
        return result.getStringExtra(ADD_QUES);
    }
    //获取新答案
    public static Boolean getAns(Intent result){
        return result.getBooleanExtra(ADD_ANS,true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        mAddText = findViewById(R.id.add_text);
        mTureChoice = findViewById(R.id.True_choice);
        mAddBtn = findViewById(R.id.add_btn);

        mAddBtn.setOnClickListener(view -> {
            String ques = mAddText.getText().toString();
            if (mTureChoice.isChecked()){
                setAddQues(ques,true);
            }else {
                setAddQues(ques,false);
            }

            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            Timer timer = new Timer();
            timer.schedule(task,1000);
        });



    }
}