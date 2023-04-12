package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private TextView mCheatText;
    private Button mCheatButton;

    private boolean mAnswerIsTrue;

    private boolean IsCheat =false;

    private static final  String EXTRA_ANSWER = "com.example.geoquize.answer";
    private static final String EXTRA_CHEAT = "con.example.geoquize.cheat";

    public static Intent newIntent(AppCompatActivity activity,boolean answer){
        Intent i = new Intent(activity,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER,answer);
        return i;
    }

    public static boolean wasCheat(Intent result){
        return result.getBooleanExtra(EXTRA_CHEAT,false);
    }

    private void setIsCheat(boolean isCheat){
        Intent data = new Intent();
        data.putExtra(EXTRA_CHEAT,isCheat);
        setResult(Activity.RESULT_OK,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mCheatButton = findViewById(R.id.cheat_btn);
        mCheatText = findViewById(R.id.cheat_text);

        if (savedInstanceState!=null){
            IsCheat = savedInstanceState.getBoolean("IsCheat");
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER,false);

        mCheatButton.setOnClickListener(view -> {
            if (mAnswerIsTrue) {
                mCheatText.setText(R.string.cn_true);
            }else {
                mCheatText.setText(R.string.cn_false);
            }
            setIsCheat(true);//已作弊设置返回消息
            IsCheat = true;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("IsCheat",IsCheat);
    }
}