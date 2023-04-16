package com.example.geoquiz;

import java.io.Serializable;

public class Question implements Serializable {
//Serializable 接口对复杂类型进行打包 只能打包简单类  复杂类型需要手动打包

//    private int mTextResId; 由于需要添加题目 且string.xml无法通过代码进行更 所以将存储地址的int 类型的ResId更改为 String类型直接存储题目
//    在QuizViewModel初始化时候 使用getString 用ResId直接获取String中的内容为其初始化
    private String mTextQues;
    private boolean mAnswerTrue;
    private boolean mFinish;
    private boolean mCheat;

    public Question(String TextQues, boolean answerTrue, boolean finish,boolean cheat) {
        mTextQues = TextQues;
        mAnswerTrue = answerTrue;
        mFinish = finish;
        mCheat =cheat;
    }


    public boolean isFinish() {
        return mFinish;
    }
    public void setFinish(boolean finish) {
        mFinish = finish;
    }
    public String getTextQues() {
        return mTextQues;
    }

    public void setTextQues(String TextQues) {
        mTextQues = TextQues;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isCheat() {
        return mCheat;
    }
    public void setCheat(boolean cheat) {
        mCheat = cheat;
    }
}
