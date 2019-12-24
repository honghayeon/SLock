package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyPageTeacherActivity extends AppCompatActivity {

    TextView userName, userJob;
    ImageButton backBtn;
    Button continueBtn;
    EditText mytPwd1, mytPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_teacher);

        userName = (TextView)findViewById(R.id.mytUser);
        userJob = (TextView)findViewById(R.id.mytJob);

        backBtn = (ImageButton)findViewById(R.id.mytbackBtn);
        continueBtn = (Button)findViewById(R.id.mytContinueBtn);

        mytPwd1 = (EditText)findViewById(R.id.mytPwd1);
        mytPwd2 = (EditText)findViewById(R.id.mytPwd2);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 비밀번호 업데이트

                finish();
            }
        });

    }
}
