package com.example.slock;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
//                Intent intent = new Intent(getApplicationContext(), MainTeacherActivity.class);
//                startActivity(intent);

                ToastCustom("로그인 성공");

                Intent intent = new Intent(getApplicationContext(), MainStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ToastCustom(String word){
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toastborder, (ViewGroup)findViewById(R.id.toast_layout_root));
        TextView text = layout.findViewById(R.id.toastTxt);

        Toast toast = new Toast(getApplicationContext());
        text.setText(word);
//        toast.setGravity(Gravity.CENTER, 0, 500);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setView(layout);
        toast.show();
    }
}
