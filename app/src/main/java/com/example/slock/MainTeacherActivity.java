package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainTeacherActivity extends AppCompatActivity {

    TextView userName;
    ImageButton myPage, logBtn, doorBtn;
    Button logoutBtn, reserveBtn;
    TimePicker time1, time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        userName = (TextView)findViewById(R.id.mtUser); // user 이름 받아와서 userName.setText(); 해주기

        myPage = (ImageButton)findViewById(R.id.mtImage);
        logBtn = (ImageButton)findViewById(R.id.mtLogBtn);
        doorBtn = (ImageButton)findViewById(R.id.mtDoorBtn);

        logoutBtn = (Button)findViewById(R.id.mtLogoutBtn);
        reserveBtn = (Button)findViewById(R.id.mtReserBtn);

        time1 = (TimePicker)findViewById(R.id.mtTime1);
        time2 = (TimePicker)findViewById(R.id.mtTime2);

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPageTeacherActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 예약 버튼 누르면
                Toast.makeText(getApplicationContext(), "예약 완료",Toast.LENGTH_SHORT); //토스트 커스텀 코드 가져오기
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent2 = new Intent(getApplicationContext(), LogActivity.class);
                //startActivity(intent2);
            }
        });

        doorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent3 = new Intent(getApplicationContext(), DoorActivity.class);
                 startActivity(intent3);
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
