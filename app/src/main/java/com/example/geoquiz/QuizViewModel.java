package com.example.geoquiz;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizViewModel extends ViewModel {

    private ArrayList <Question> mQuestionArrayList;
    private void creatQuesList(){
        mQuestionArrayList = new ArrayList<Question>();
        mQuestionArrayList.addAll(
                Arrays.asList(
                    new Question(R.string.q1,true,true,false),
                    new Question(R.string.q2,true,true,false),
                    new Question(R.string.q3,true,true,false),
                    new Question(R.string.q4,false,true,false),
                    new Question(R.string.q5,false,true,false),
                    new Question(R.string.q6,false,true,false),
                    new Question(R.string.q7,false,true,false)
                )
        );


    }

    private Question[] mQuestionsBank = new Question[]{
            new Question(R.string.q1,true,true,false),
            new Question(R.string.q2,true,true,false),
            new Question(R.string.q3,true,true,false),
            new Question(R.string.q4,false,true,false),
            new Question(R.string.q5,false,true,false),
            new Question(R.string.q6,false,true,false),
            new Question(R.string.q7,false,true,false),
    };
    private int mIndex = 0;

    private int length = mQuestionsBank.length;

    public int getLength() {
        return length;
    }


    public int currentQuesText(){
        return mQuestionsBank[mIndex].getTextResId();
    }
    public Boolean currentQuesAnswer(){
        return mQuestionsBank[mIndex].isAnswerTrue();
    }

    public Boolean getCurrentFinish(){
        return mQuestionsBank[mIndex].isFinish();
    }
    public void setCurrentFinish(boolean mFinish){
        mQuestionsBank[mIndex].setFinish(mFinish);
    }
    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public boolean isCheat(){
        return mQuestionsBank[mIndex].isCheat();
    }
    public void setCheat(){
        mQuestionsBank[mIndex].setCheat(true);
    }


    @Override
    protected void onCleared(){
        super.onCleared();
        Log.d("QuizLog","QuizViewModel onCleared()被调用");
    }




}
