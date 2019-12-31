package com.example.slock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {

    ImageButton backBtn;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<com.example.slock.Log> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        recyclerView = findViewById(R.id.lRecycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<com.example.slock.Log>(); // User 객체를 담을 ArrayList (Adapter 쪽으로)

        adapter = new LogAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // RecyclerView에 Adapter 연결


        arrayList.clear(); //초기화
//        com.example.slock.Log log = "data".getValue(com.example.slock.Log.class); // 만들어뒀던 List 객체에 데이터를 담는다.
//        arrayList.add(log); // 담은 데이터들을 arrayList에 넣고 RecyclerView로 보낼 준비
        adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침

        backBtn = (ImageButton)findViewById(R.id.lbackBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
