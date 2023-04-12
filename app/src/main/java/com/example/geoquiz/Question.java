package com.example.geoquiz;

import java.io.Serializable;

public class Question implements Serializable {
//Serializable 接口对复杂类型进行打包 只能打包简单类  复杂类型需要手动打包

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mFinish;
    private boolean mCheat;

    public Question(int textResId, boolean answerTrue, boolean finish,boolean cheat) {
        mTextResId = textResId;
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
    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
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
