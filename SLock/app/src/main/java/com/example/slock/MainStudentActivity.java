package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class MainStudentActivity extends AppCompatActivity {

    TextView userName, mainDoorState, doorState;
    ImageButton myPage;
    Button logoutBtn, requsetBtn;
    TimePicker time1, time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);

        userName = (TextView)findViewById(R.id.msUser); // user 이름 받아와서 userName.setText(); 해주기

        myPage = (ImageButton)findViewById(R.id.msImage);

        logoutBtn = (Button)findViewById(R.id.msLogoutBtn);
        requsetBtn = (Button)findViewById(R.id.msRequBtn);

        time1 = (TimePicker)findViewById(R.id.msTime1);
        time2 = (TimePicker)findViewById(R.id.msTime2);

        mainDoorState = (TextView)findViewById(R.id.msMainDoorState);
        doorState = (TextView)findViewById(R.id.msDoorState);

        // DB 값 받아오기
//        if(result.equals("OPEN")){
//            mainDoorState.setText("Open");
//            mainDoorState.setTextColor(getResources().getColor(R.color.green));
//        }
//        else{
//            mainDoorState.setText("Close");
//            mainDoorState.setTextColor(getResources().getColor(R.color.red));
//        }
//
//        if(result.equals("OPEN")){
//            doorState.setText("Open");
//            doorState.setTextColor(getResources().getColor(R.color.green));
//        }
//        else{
//            doorState.setText("Close");
//            doorState.setTextColor(getResources().getColor(R.color.red));
//        }

        userName.setText(SharedPreference.getAttribute(getApplicationContext(), "name") + ",");

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MyPageStudentActivity.class);
                startActivity(intent1);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreference.removeAttribute(getApplicationContext(), "job");
                SharedPreference.removeAttribute(getApplicationContext(), "id");
                SharedPreference.removeAttribute(getApplicationContext(), "name");
                SharedPreference.removeAttribute(getApplicationContext(), "roomnumber");

                Intent intent2 = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent2);

                finish();
            }
        });

        requsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 요청 DB에 보내기

                ToastCustom("요청 완료");
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
