package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    Button signupBtn, continueBtn;
    EditText inId, inPwd; // 로그인하기 위해 입력한 id와 pwd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signupBtn = (Button)findViewById(R.id.inSignupBtn);
        continueBtn = (Button)findViewById(R.id.inContinueBtn);

        inId = (EditText)findViewById(R.id.inId);
        inPwd = (EditText)findViewById(R.id.inPwd);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 페이지의 continue가 눌리면
            }
        });
    }
}
