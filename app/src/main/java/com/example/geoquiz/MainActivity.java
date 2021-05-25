package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class        MainActivity extends AppCompatActivity {

    public Button mTrueButton;
    public Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }



    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrevOnclick();

        mQuestionTextView = findViewById(R.id.question_text_view);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(v -> {
            checkAnswer(true);
//            Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            // Does nothing yet, but soon!
        });


        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(v -> {
            checkAnswer(false);

//            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            // Does nothing yet, but soon!
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(v -> {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        });

//        mPrevButton = findViewById(R.id.pre_button);
//        mPrevButton.setOnClickListener(v ->{
//            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
//            updateQuestion();
//        })
        updateQuestion();
    }
    private void updatePrev() throws ArrayIndexOutOfBoundsException{
        if (mCurrentIndex > 0){
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
            updateQuestion();
        }

    }
    private void PrevOnclick(){
        mPrevButton = findViewById(R.id.pre_button);
        mPrevButton.setOnClickListener(v -> updatePrev());
    }
}