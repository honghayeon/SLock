package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpTeacherActivity extends AppCompatActivity {

    Button signinBtn, signupBtn, continueBtn;
    EditText tName, tId, tPwd1,tPwd2, tCertnum; // 회원가입할 선생님 이름, 인증번호
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teacher);

        signinBtn = (Button)findViewById(R.id.tSigninBtn);
        signupBtn = (Button)findViewById(R.id.tSignupBtn);
        continueBtn = (Button)findViewById(R.id.tContinueBtn);

        tName = (EditText)findViewById(R.id.tName);
        tId = (EditText)findViewById(R.id.tId);
        tPwd1 = (EditText)findViewById(R.id.tPwd1);
        tPwd2 = (EditText)findViewById(R.id.tPwd2);
        tCertnum = (EditText) findViewById(R.id.tCertnum);

        intent = new Intent(getApplicationContext(), SignInActivity.class);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선생님 회원가입 페이지의 continue가 눌리면

                startActivity(intent);
            }
        });
    }
}
