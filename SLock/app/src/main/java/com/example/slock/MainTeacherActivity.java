package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        userName = (TextView)findViewById(R.id.mtUser);

        myPage = (ImageButton)findViewById(R.id.mtImage);
        logBtn = (ImageButton)findViewById(R.id.mtLogBtn);
        doorBtn = (ImageButton)findViewById(R.id.mtDoorBtn);

        logoutBtn = (Button)findViewById(R.id.mtLogoutBtn);
        reserveBtn = (Button)findViewById(R.id.mtReserBtn);

        time1 = (TimePicker)findViewById(R.id.mtTime1);
        time2 = (TimePicker)findViewById(R.id.mtTime2);


        // DB 값 받아서 현관문 스위치 값 설정
        // 스위치 바뀌면 DB 값도 변경

        userName.setText(SharedPreference.getAttribute(getApplicationContext(), "name"));

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MyPageTeacherActivity.class);
                startActivity(intent1);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreference.removeAttribute(getApplicationContext(), "job");
                SharedPreference.removeAttribute(getApplicationContext(), "id");
                SharedPreference.removeAttribute(getApplicationContext(), "name");

                Intent intent2 = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent2);

                finish();
            }
        });

        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 예약 버튼 누르면 DB에 값 전달
                // time 값

               ToastCustom("예약 완료");
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(intent3);
            }
        });

        doorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent4 = new Intent(getApplicationContext(), DoorActivity.class);
                 startActivity(intent4);
            }
        });

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics(); // 화면 넓이 받아와서 timePicker를 24시간 형식으로 바꿀지 결정
        int width = dm.widthPixels;

        if(width <= 500){
            time1.setIs24HourView(true);
            time2.setIs24HourView(true);
        }
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
