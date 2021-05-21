package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button mTrueButton;
    public Button mFalseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            // Does nothing yet, but soon!
        });

        mFalseButton = findViewById(R.id.false_button);
//        mFalseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Correct!",Toast.LENGTH_SHORT).show();
//            }
//        });
        mFalseButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            // Does nothing yet, but soon!
        });
    }

}