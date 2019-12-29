package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TabHost;

import com.google.android.material.tabs.TabLayout;

public class DoorActivity extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    Switch all, thirdFloor, fourthFloor, fifthFloor,
        room301, room302, room303, room304, room305, room306, room307, room308, room309, room310,
        room311, room312, room313, room314, room315, room316, room317, room318, room319, room320,
        room401, room402, room403, room404, room405, room406, room407, room408, room409, room410,
        room411, room412, room413, room414, room415, room416, room417, room418, room419, room420,
        room501, room502, room503, room504, room505, room506, room507, room508, room509, room510,
        room511, room512, room513, room514, room515, room516, room517, room518, room519, room520;

    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        // 뷰페이지 세팅
        viewPager = (ViewPager)findViewById(R.id.dViewPager);
        fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        tabLayout = (TabLayout)findViewById(R.id.dTabLayout);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        backBtn = (ImageButton)findViewById(R.id.dbackBtn);

//        all = (Switch)findViewById(R.id.dAllSw);
//        thirdFloor = (Switch)findViewById(R.id.d3Sw);
//        fourthFloor = (Switch )findViewById(R.id.d4Sw);
//        fifthFloor = (Switch)findViewById(R.id.d5Sw);
//        // 3층
//        room301 = (Switch)findViewById(R.id.room301);
//        room302 = (Switch)findViewById(R.id.room302);
//        room303 = (Switch)findViewById(R.id.room303);
//        room304 = (Switch)findViewById(R.id.room304);
//        room305 = (Switch)findViewById(R.id.room305);
//        room306 = (Switch)findViewById(R.id.room306);
//        room307 = (Switch)findViewById(R.id.room307);
//        room308 = (Switch)findViewById(R.id.room308);
//        room309 = (Switch)findViewById(R.id.room309);
//        room310 = (Switch)findViewById(R.id.room310);
//        room311 = (Switch)findViewById(R.id.room311);
//        room312 = (Switch)findViewById(R.id.room312);
//        room313 = (Switch)findViewById(R.id.room313);
//        room314 = (Switch)findViewById(R.id.room314);
//        room315 = (Switch)findViewById(R.id.room315);
//        room316 = (Switch)findViewById(R.id.room316);
//        room317 = (Switch)findViewById(R.id.room317);
//        room318 = (Switch)findViewById(R.id.room318);
//        room319 = (Switch)findViewById(R.id.room319);
//        room320 = (Switch)findViewById(R.id.room320);
//        // 4층
//        room401 = (Switch)findViewById(R.id.room401);
//        room402 = (Switch)findViewById(R.id.room402);
//        room403 = (Switch)findViewById(R.id.room403);
//        room404 = (Switch)findViewById(R.id.room404);
//        room405 = (Switch)findViewById(R.id.room405);
//        room406 = (Switch)findViewById(R.id.room406);
//        room407 = (Switch)findViewById(R.id.room407);
//        room408 = (Switch)findViewById(R.id.room408);
//        room409 = (Switch)findViewById(R.id.room409);
//        room410 = (Switch)findViewById(R.id.room410);
//        room411 = (Switch)findViewById(R.id.room411);
//        room412 = (Switch)findViewById(R.id.room412);
//        room413 = (Switch)findViewById(R.id.room413);
//        room414 = (Switch)findViewById(R.id.room414);
//        room415 = (Switch)findViewById(R.id.room415);
//        room416 = (Switch)findViewById(R.id.room416);
//        room417 = (Switch)findViewById(R.id.room417);
//        room418 = (Switch)findViewById(R.id.room418);
//        room419 = (Switch)findViewById(R.id.room419);
//        room420 = (Switch)findViewById(R.id.room420);
//        // 5층
//        room501 = (Switch)findViewById(R.id.room501);
//        room502 = (Switch)findViewById(R.id.room502);
//        room503 = (Switch)findViewById(R.id.room503);
//        room504 = (Switch)findViewById(R.id.room504);
//        room505 = (Switch)findViewById(R.id.room505);
//        room506 = (Switch)findViewById(R.id.room506);
//        room507 = (Switch)findViewById(R.id.room507);
//        room508 = (Switch)findViewById(R.id.room508);
//        room509 = (Switch)findViewById(R.id.room509);
//        room510 = (Switch)findViewById(R.id.room510);
//        room511 = (Switch)findViewById(R.id.room511);
//        room512 = (Switch)findViewById(R.id.room512);
//        room513 = (Switch)findViewById(R.id.room513);
//        room514 = (Switch)findViewById(R.id.room514);
//        room515 = (Switch)findViewById(R.id.room515);
//        room516 = (Switch)findViewById(R.id.room516);
//        room517 = (Switch)findViewById(R.id.room517);
//        room518 = (Switch)findViewById(R.id.room518);
//        room519 = (Switch)findViewById(R.id.room519);
//        room520 = (Switch)findViewById(R.id.room520);

//        if(all.isChecked()){
//            setThirdFloor(true);
//            setFourthFloor(true);
//            setFifthFloor(true);
//        }
//        else{
//            setThirdFloor(false);
//            setFourthFloor(false);
//            setFifthFloor(false);
//        }
//
//        if(thirdFloor.isChecked()){
//            setThirdFloor(true);
//        }
//        else{
//            setThirdFloor(false);
//        }
//
//        if(fourthFloor.isChecked()){
//            setFourthFloor(true);
//        }
//        else{
//            setFourthFloor(false);
//        }
//
//        if(fifthFloor.isChecked()){
//            setFifthFloor(true);
//        }
//        else{
//            setFifthFloor(false);
//        }


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    private void setFifthFloor(boolean sw) {
//        if(sw){
//            room501.setChecked(true);
//            room502.setChecked(true);
//            room503.setChecked(true);
//            room504.setChecked(true);
//            room505.setChecked(true);
//            room506.setChecked(true);
//            room507.setChecked(true);
//            room508.setChecked(true);
//            room509.setChecked(true);
//            room510.setChecked(true);
//            room511.setChecked(true);
//            room512.setChecked(true);
//            room513.setChecked(true);
//            room514.setChecked(true);
//            room515.setChecked(true);
//            room516.setChecked(true);
//            room517.setChecked(true);
//            room518.setChecked(true);
//            room519.setChecked(true);
//            room520.setChecked(true);
//        }
//        else{
//            room501.setChecked(false);
//            room502.setChecked(false);
//            room503.setChecked(false);
//            room504.setChecked(false);
//            room505.setChecked(false);
//            room506.setChecked(false);
//            room507.setChecked(false);
//            room508.setChecked(false);
//            room509.setChecked(false);
//            room510.setChecked(false);
//            room511.setChecked(false);
//            room512.setChecked(false);
//            room513.setChecked(false);
//            room514.setChecked(false);
//            room515.setChecked(false);
//            room516.setChecked(false);
//            room517.setChecked(false);
//            room518.setChecked(false);
//            room519.setChecked(false);
//            room520.setChecked(false);
//        }
//    }
//
//    private void setFourthFloor(boolean sw) {
//        if(sw){
//            room401.setChecked(true);
//            room402.setChecked(true);
//            room403.setChecked(true);
//            room404.setChecked(true);
//            room405.setChecked(true);
//            room406.setChecked(true);
//            room407.setChecked(true);
//            room408.setChecked(true);
//            room409.setChecked(true);
//            room410.setChecked(true);
//            room411.setChecked(true);
//            room412.setChecked(true);
//            room413.setChecked(true);
//            room414.setChecked(true);
//            room415.setChecked(true);
//            room416.setChecked(true);
//            room417.setChecked(true);
//            room418.setChecked(true);
//            room419.setChecked(true);
//            room420.setChecked(true);
//        }
//        else{
//            room401.setChecked(false);
//            room402.setChecked(false);
//            room403.setChecked(false);
//            room404.setChecked(false);
//            room405.setChecked(false);
//            room406.setChecked(false);
//            room407.setChecked(false);
//            room408.setChecked(false);
//            room409.setChecked(false);
//            room410.setChecked(false);
//            room411.setChecked(false);
//            room412.setChecked(false);
//            room413.setChecked(false);
//            room414.setChecked(false);
//            room415.setChecked(false);
//            room416.setChecked(false);
//            room417.setChecked(false);
//            room418.setChecked(false);
//            room419.setChecked(false);
//            room420.setChecked(false);
//        }
//    }
//
//    private void setThirdFloor(boolean sw) {
//        if(sw){
//            room301.setChecked(true);
//            room302.setChecked(true);
//            room303.setChecked(true);
//            room304.setChecked(true);
//            room305.setChecked(true);
//            room306.setChecked(true);
//            room307.setChecked(true);
//            room308.setChecked(true);
//            room309.setChecked(true);
//            room310.setChecked(true);
//            room311.setChecked(true);
//            room312.setChecked(true);
//            room313.setChecked(true);
//            room314.setChecked(true);
//            room315.setChecked(true);
//            room316.setChecked(true);
//            room317.setChecked(true);
//            room318.setChecked(true);
//            room319.setChecked(true);
//            room320.setChecked(true);
//        }
//        else{
//            room301.setChecked(false);
//            room302.setChecked(false);
//            room303.setChecked(false);
//            room304.setChecked(false);
//            room305.setChecked(false);
//            room306.setChecked(false);
//            room307.setChecked(false);
//            room308.setChecked(false);
//            room309.setChecked(false);
//            room310.setChecked(false);
//            room311.setChecked(false);
//            room312.setChecked(false);
//            room313.setChecked(false);
//            room314.setChecked(false);
//            room315.setChecked(false);
//            room316.setChecked(false);
//            room317.setChecked(false);
//            room318.setChecked(false);
//            room319.setChecked(false);
//            room320.setChecked(false);
//        }
//    }


}
