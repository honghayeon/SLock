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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
