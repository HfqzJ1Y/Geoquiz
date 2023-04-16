package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
//    private Button mNextButton;
//
//    private Button mLastButton;
    private ImageButton mNextButton;
    private ImageButton mLastButton;

    private QuizViewModel quizViewModel;
    private int count = 0;
    private int countTrue = 0;

    private static final int REQUEST_ACTIVITY_CHEAT = 0;

    private boolean mCurrentCheat = false;

    public static Intent newIntent(AppCompatActivity activity){
        Intent i = new Intent(activity,MainActivity.class);
        return i;
    }

    public void setQuestion(){
            mTextView.setText(String.format("%d/%d %s",quizViewModel.getIndex()+1,quizViewModel.getLength(),quizViewModel.currentQuesText()));
    }
    //对作弊警告判断并且处理作弊模块
    public void checkCheat(){
        if (mCurrentCheat&&quizViewModel.getCurrentFinish()){
            quizViewModel.setCheat();
            quizViewModel.setCurrentFinish();
            count+=1;
        }
        mCurrentCheat = false;
    }

    public void checkAnswer(boolean userAnswer){

        if (mCurrentCheat||quizViewModel.isCheat()) {
            Toast.makeText(this, "作弊警告", Toast.LENGTH_SHORT).show();

            checkCheat();
        }else {
            if (quizViewModel.getCurrentFinish()) {
                if (quizViewModel.currentQuesAnswer() == userAnswer) {
                    Toast.makeText(this, R.string.show_true, Toast.LENGTH_SHORT).show();
                    countTrue += 1;
                } else {
                    Toast.makeText(this, R.string.show_false, Toast.LENGTH_SHORT).show();
                }
                count += 1;
                quizViewModel.setCurrentFinish();
            } else {
                Toast.makeText(this, "这题已经答过了 请下一题", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.question_text);
        mTrueButton = findViewById(R.id.true_btn);
        mFalseButton = findViewById(R.id.false_btn);
        mNextButton = findViewById(R.id.next_imgBtn);
        mLastButton = findViewById(R.id.last_imgBtn);
        mCheatButton = findViewById(R.id.cheat_btn);


//viewModel
//错误方法  这样创建viewModel每次creat时就会new一次不能达到容器的效果
//    QuizViewModel quizViewModel = new QuizViewModel();
        //正确方法
        ViewModelProvider provider = new ViewModelProvider(this);
        quizViewModel = provider.get(QuizViewModel.class);
        if (quizViewModel.getList()==null) {
            quizViewModel.creatQuesList(getApplicationContext());

            int length = getIntent().getIntExtra("length", 0);
            for (int i = 7; i < length; i++) {
                if (getIntent().getSerializableExtra(String.format("ques%d", i)) == null) {
                    System.out.println("传入题目为空");
                } else {
                    Question question = (Question) getIntent().getSerializableExtra(String.format("ques%d", i));
                    quizViewModel.addClass(question);
                }
//            quizViewModel.addClass( (Question) getIntent().getSerializableExtra(String.format("ques%d",i)));
            }
        }
        if (savedInstanceState!=null){
            quizViewModel.setIndex(savedInstanceState.getInt("Index"));
            mCurrentCheat = savedInstanceState.getBoolean("CurrentCheat");
        }


        //初始化界面问题
        setQuestion();
        count = 0;

            mTrueButton.setOnClickListener(view -> {
                    checkAnswer(true);

            });

            mFalseButton.setOnClickListener(view -> {
                    checkAnswer(false);

            });


        mNextButton.setOnClickListener(view -> {
            checkCheat();

            quizViewModel.setIndex((quizViewModel.getIndex()+1) % quizViewModel.getLength());
            setQuestion();
            if (quizViewModel.getIndex()==0&&count==quizViewModel.getLength()){
                double x = Double.valueOf(countTrue)/Double.valueOf(count)*100;
                Toast.makeText(this, "正确率为"+x+"%", Toast.LENGTH_SHORT).show();
            }
        } );

        mLastButton.setOnClickListener(view -> {
            checkCheat();

            quizViewModel.setIndex((quizViewModel.getIndex()+6) % quizViewModel.getLength());
            setQuestion();
        });

        mCheatButton.setOnClickListener(view -> {
            Intent i =CheatActivity.newIntent(MainActivity.this,quizViewModel.currentQuesAnswer());
            startActivityForResult(i,REQUEST_ACTIVITY_CHEAT);

        });


        Log.d("aaaaa", "onCreat");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("aaaaa", "onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("aaaaa", "onResume");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Index",quizViewModel.getIndex());
        outState.putBoolean("CurrentCheat",mCurrentCheat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Hello", "World");
        if (requestCode != REQUEST_ACTIVITY_CHEAT) {
            return;
        }
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            if (quizViewModel.getCurrentFinish()) {
                mCurrentCheat = CheatActivity.wasCheat(data);
            }
        }
    }
}