package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Source code of NGUYEN DUY BAO LONG

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public Button mTrueButton;
    public Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private boolean mIsCheater;
    private TextView mQuestionTextView;

    private static final int REQUEST_CODE_CHEAT = 0;

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

        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PrevOnclick();

        Log.d(TAG, "onCreate(Bundle) called");

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
            mIsCheater = false;
            updateQuestion();
        });
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(v -> {
            // Start CheatActivity
            boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
            Intent i = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
            startActivityForResult(i, REQUEST_CODE_CHEAT);
        });

        updateQuestion();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
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

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}