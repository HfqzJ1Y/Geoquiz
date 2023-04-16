package com.example.geoquiz;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;


public class QuizViewModel extends ViewModel {
    //因为没有继承Context和Application类 所以无法使用getString方法获得资源中的内容

    private Context mContext ;
    private ArrayList <Question> mQuestionArrayList;

    public QuizViewModel(){

    }
    public void creatQuesList(Context context){
        mContext = context;
        mQuestionArrayList = new ArrayList<Question>(Arrays.asList(
                new Question(mContext.getResources().getString(R.string.q1),true,true,false),
                new Question(mContext.getResources().getString(R.string.q2),true,true,false),
                new Question(mContext.getResources().getString(R.string.q3),true,true,false),
                new Question(mContext.getResources().getString(R.string.q4),false,true,false),
                new Question(mContext.getResources().getString(R.string.q5),false,true,false),
                new Question(mContext.getResources().getString(R.string.q6),false,true,false),
                new Question(mContext.getResources().getString(R.string.q7),false,true,false)
        ));
    }

    private int mIndex = 0;

//新的方法
    //获取总长度
//    private int length = mQuestionArrayList.size();  ?
    //获取长度
    public int getLength() {
        return mQuestionArrayList.size();
    }
    //当前问题
    public String currentQuesText(){
        return mQuestionArrayList.get(mIndex).getTextQues();
    }
    //当前答案
    public Boolean currentQuesAnswer(){
        return mQuestionArrayList.get(mIndex).isAnswerTrue();
    }
    //是否完成
    public Boolean getCurrentFinish(){
        return mQuestionArrayList.get(mIndex).isFinish();
    }
    //提交完成情况
    public void setCurrentFinish(){
        mQuestionArrayList.get(mIndex).setFinish(false);
    }
    //获取当前问题号
    public int getIndex() {
        return mIndex;
    }
    //设置当前问题号
    public void setIndex(int index) {
        mIndex = index;
    }
    //是否作弊过
    public boolean isCheat(){
        return mQuestionArrayList.get(mIndex).isCheat();
    }
    //设置是否作弊
    public void setCheat(){
        mQuestionArrayList.get(mIndex).setCheat(true);
    }
    //添加问题
    public void addQues(String ques , Boolean ans){
        mQuestionArrayList.add(new Question(ques,ans,true,false));
    }
    public Question getClass(int i){
        return mQuestionArrayList.get(i);
    }
    public void addClass(Question question){
        mQuestionArrayList.add(question);
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        Log.d("QuizLog","QuizViewModel onCleared()被调用");
    }




}
