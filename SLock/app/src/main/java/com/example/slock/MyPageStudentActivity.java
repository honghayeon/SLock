package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

        // userName.setText();
        // userJob.setText();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DB 방 바꾸기 또는 비밀번호 업데이트

                finish();
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
