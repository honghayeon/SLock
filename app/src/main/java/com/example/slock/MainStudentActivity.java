package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainStudentActivity extends AppCompatActivity {

    TextView userName;
    ImageView myPage;
    Button logoutBtn, requsetBtn;
    TimePicker time1, time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);

        userName = (TextView)findViewById(R.id.msUser); // user 이름 받아와서 userName.setText(); 해주기

        myPage = (ImageView)findViewById(R.id.msImage);

        logoutBtn = (Button)findViewById(R.id.msLogoutBtn);
        requsetBtn = (Button)findViewById(R.id.msRequBtn);

        time1 = (TimePicker)findViewById(R.id.msTime1);
        time2 = (TimePicker)findViewById(R.id.msTime2);

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPageStudentActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        requsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 시간 요청 보내면
                Toast.makeText(getApplicationContext(), "요청 완료",Toast.LENGTH_SHORT); //토스트 커스텀 코드 가져오기
            }
        });

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

        int width = dm.widthPixels;

        if(width <= 500){
            time1.setIs24HourView(true);
            time2.setIs24HourView(true);
        }
    }
}
