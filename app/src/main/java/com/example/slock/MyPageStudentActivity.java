package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyPageStudentActivity extends AppCompatActivity {

    TextView userName, userJob;
    ImageButton backBtn;
    Button continueBtn;
    EditText mytRoomnum, mytPwd1, mytPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_student);

        userName = (TextView)findViewById(R.id.mysUser);
        userJob = (TextView)findViewById(R.id.mysJob);

        backBtn = (ImageButton)findViewById(R.id.mysbackBtn);
        continueBtn = (Button)findViewById(R.id.mysContinueBtn);

        mytRoomnum = (EditText)findViewById(R.id.mysRoom);
        mytPwd1 = (EditText)findViewById(R.id.mysPwd1);
        mytPwd2 = (EditText)findViewById(R.id.mysPwd2);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 방 바꾸기 또는 비밀번호 업데이트

                finish();
            }
        });

    }
}
