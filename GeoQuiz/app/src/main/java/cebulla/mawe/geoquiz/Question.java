package cebulla.mawe.geoquiz;

import android.widget.Button;

import com.bignerdranch.android.geoquiz.R;

/**
 * Created by malakhov on 05.11.2015.
 */
public class Question {
    private int mTextRestId;
    private boolean mAnswerTrue;

    public Question(int mTextRestId, boolean mAnswerTrue) {
        this.mTextRestId = mTextRestId;
        this.mAnswerTrue = mAnswerTrue;
    }



    public int getTextResId(){
        return mTextRestId;
    }

    private void setmTextRestId(int textRestId){
        mTextRestId = textRestId;

    }

    public boolean ismAnswerTrue(){
        return mAnswerTrue;
    }

    public  void setmAnswerTrue(boolean answerTrue){
        mAnswerTrue = answerTrue;
    }
}
