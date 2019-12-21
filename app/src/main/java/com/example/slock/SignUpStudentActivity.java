package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpStudentActivity extends AppCompatActivity {

    Button signinBtn, signupBtn, continueBtn;
    EditText sName, sId, sPwd, sStunum, sRoomnum; // 회원가입할 학생 이름, 아이디, 학번, 방번호
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        signinBtn = (Button)findViewById(R.id.sSigninBtn);
        signupBtn = (Button)findViewById(R.id.sSignupBtn);
        continueBtn = (Button)findViewById(R.id.sContinueBtn);

        EditText sName = (EditText)findViewById(R.id.sName);
        EditText sId = (EditText)findViewById(R.id.sId);
        EditText sPwd = (EditText)findViewById(R.id.sPwd);
        EditText sStunum = (EditText) findViewById(R.id.sStunum);
        EditText sRoomnum = (EditText) findViewById(R.id.sRoomnum);

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
                // 학생 회원가입 페이지의 continue가 눌리면

                startActivity(intent);
            }
        });
    }
}
