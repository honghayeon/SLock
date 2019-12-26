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

        all = (Switch)findViewById(R.id.dAllSw);
        thirdFloor = (Switch)findViewById(R.id.d3Sw);
        fourthFloor = (Switch )findViewById(R.id.d4Sw);
        fifthFloor = (Switch)findViewById(R.id.d5Sw);
        // 3층
        room301 = (Switch)findViewById(R.id.room301);
        room302 = (Switch)findViewById(R.id.room302);
        room303 = (Switch)findViewById(R.id.room303);
        room304 = (Switch)findViewById(R.id.room304);
        room305 = (Switch)findViewById(R.id.room305);
        room306 = (Switch)findViewById(R.id.room306);
        room307 = (Switch)findViewById(R.id.room307);
        room308 = (Switch)findViewById(R.id.room308);
        room309 = (Switch)findViewById(R.id.room309);
        room310 = (Switch)findViewById(R.id.room310);
        room311 = (Switch)findViewById(R.id.room311);
        room312 = (Switch)findViewById(R.id.room312);
        room313 = (Switch)findViewById(R.id.room313);
        room314 = (Switch)findViewById(R.id.room314);
        room315 = (Switch)findViewById(R.id.room315);
        room316 = (Switch)findViewById(R.id.room316);
        room317 = (Switch)findViewById(R.id.room317);
        room318 = (Switch)findViewById(R.id.room318);
        room319 = (Switch)findViewById(R.id.room319);
        room320 = (Switch)findViewById(R.id.room320);
        // 4층
        room401 = (Switch)findViewById(R.id.room401);
        room402 = (Switch)findViewById(R.id.room402);
        room403 = (Switch)findViewById(R.id.room403);
        room404 = (Switch)findViewById(R.id.room404);
        room405 = (Switch)findViewById(R.id.room405);
        room406 = (Switch)findViewById(R.id.room406);
        room407 = (Switch)findViewById(R.id.room407);
        room408 = (Switch)findViewById(R.id.room408);
        room409 = (Switch)findViewById(R.id.room409);
        room410 = (Switch)findViewById(R.id.room410);
        room411 = (Switch)findViewById(R.id.room411);
        room412 = (Switch)findViewById(R.id.room412);
        room413 = (Switch)findViewById(R.id.room413);
        room414 = (Switch)findViewById(R.id.room414);
        room415 = (Switch)findViewById(R.id.room415);
        room416 = (Switch)findViewById(R.id.room416);
        room417 = (Switch)findViewById(R.id.room417);
        room418 = (Switch)findViewById(R.id.room418);
        room419 = (Switch)findViewById(R.id.room419);
        room420 = (Switch)findViewById(R.id.room420);
        // 5층
        room501 = (Switch)findViewById(R.id.room501);
        room502 = (Switch)findViewById(R.id.room502);
        room503 = (Switch)findViewById(R.id.room503);
        room504 = (Switch)findViewById(R.id.room504);
        room505 = (Switch)findViewById(R.id.room505);
        room506 = (Switch)findViewById(R.id.room506);
        room507 = (Switch)findViewById(R.id.room507);
        room508 = (Switch)findViewById(R.id.room508);
        room509 = (Switch)findViewById(R.id.room509);
        room510 = (Switch)findViewById(R.id.room510);
        room511 = (Switch)findViewById(R.id.room511);
        room512 = (Switch)findViewById(R.id.room512);
        room513 = (Switch)findViewById(R.id.room513);
        room514 = (Switch)findViewById(R.id.room514);
        room515 = (Switch)findViewById(R.id.room515);
        room516 = (Switch)findViewById(R.id.room516);
        room517 = (Switch)findViewById(R.id.room517);
        room518 = (Switch)findViewById(R.id.room518);
        room519 = (Switch)findViewById(R.id.room519);
        room520 = (Switch)findViewById(R.id.room520);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
