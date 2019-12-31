package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainStudentActivity extends AppCompatActivity {

    TextView userName, mainDoorState, doorState;
    ImageButton myPage;
    Button logoutBtn, requsetBtn;
    TimePicker time;
    LineChart lineChart;
    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);

        final RequestQueue queue = Volley.newRequestQueue(this);

        userName = (TextView)findViewById(R.id.msUser);

        myPage = (ImageButton)findViewById(R.id.msImage);

        logoutBtn = (Button)findViewById(R.id.msLogoutBtn);
        requsetBtn = (Button)findViewById(R.id.msRequBtn);

        time = (TimePicker)findViewById(R.id.msTime);

        mainDoorState = (TextView)findViewById(R.id.msMainDoorState);
        doorState = (TextView)findViewById(R.id.msDoorState);

        lineChart = (LineChart)findViewById(R.id.msChart);

        userName.setText(SharedPreference.getAttribute(getApplicationContext(), "name") + ",");

        // 그래프
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(9, 1));
        entries.add(new Entry(12, 2));
        entries.add(new Entry(15, 7));
        entries.add(new Entry(18, 4));
        entries.add(new Entry(20, 3));
        entries.add(new Entry(21, 2));

        LineDataSet lineDataSet = new LineDataSet(entries, "신청자 수");
        lineDataSet.setLineWidth(2); // 선 두께
        lineDataSet.setCircleRadius(5); // circle 두께
        lineDataSet.setCircleColor(Color.parseColor("#46A8FB")); // circle color
        lineDataSet.setColor(Color.parseColor("#46A8FB"));
        lineDataSet.setDrawCircleHole(false); // circle 가운데 흰 색 구멍
        lineDataSet.setDrawCircles(true); // circle 그리기
        lineDataSet.setDrawHorizontalHighlightIndicator(false); // 하이라이팅
        lineDataSet.setDrawHighlightIndicators(false); // 하이라이팅
        lineDataSet.setDrawValues(false); // 점 위에 값

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.parseColor("#46A8FB")); // X축 값 색
        xAxis.setGridColor(Color.parseColor("#F1F9FF")); // X축 줄 색
        xAxis.setAxisMinimum(8);
        xAxis.setAxisMaximum(21);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.parseColor("#46A8FB")); // Y축 값 색
        yLAxis.setGridColor(Color.parseColor("#F1F9FF")); // Y축 줄 색
        yLAxis.setAxisMinimum(0);
        yLAxis.setDrawAxisLine(false); // 맨 처음 값
        YAxis yRAxis = lineChart.getAxisRight(); // y축 오른쪽 비활성화
        yRAxis.setDrawLabels(false); // label 보이기
        yRAxis.setDrawAxisLine(false); // 줄 보이기
        yRAxis.setDrawGridLines(false); // 라인 보이기

        Description description = new Description();
        description.setText("SLock");

//        lineDataSet.setDrawFilled(true); //그래프 밑부분 색칠

        lineChart.setDoubleTapToZoomEnabled(false); // 애니메이션
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(1800, Easing.EaseInCubic);
        lineChart.invalidate();

        MyMarkerView marker = new MyMarkerView(this,R.layout.text);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);

        lineChart.setPinchZoom(true);

        // 현재 현관문 상태 받아옴
        final Timer timer = new Timer();
        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                // 반복실행할 구문
                String url = "http://10.120.74.188:8080/main_door/read";
                HashMap<String, String> data = new HashMap<>();
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response.toString());
                                    String lock = jsonObject.getString("l_flg");
                                    if (lock.equals("1")) {
                                        mainDoorState.setText("Open");
                                        mainDoorState.setTextColor(getResources().getColor(R.color.green));
                                    }
                                    else {
                                        mainDoorState.setText("Close");
                                        mainDoorState.setTextColor(getResources().getColor(R.color.red));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                customDialog = new CustomDialog(MainStudentActivity.this, "이용에 불편을 드려 죄송합니다.\n잠시 후 다시 접속해 주세요.");
                                customDialog.show();

                                timer.cancel();
                            }
                        }
                );
                request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(request);
                HashMap<String, String> data2 = new HashMap<>();
                data2.put("rnum",SharedPreference.getAttribute(getApplicationContext(), "roomnumber"));
                request = new JsonObjectRequest(Request.Method.POST, "http://10.120.74.188:8080/second_door/read", new JSONObject(data2),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = new JSONObject(response.toString());
                                    String lock = jsonObject.getString("d_flg");
                                    if (lock.equals("1")) {
                                        doorState.setText("Open");
                                        doorState.setTextColor(getResources().getColor(R.color.green));
                                    }
                                    else {
                                        doorState.setText("Close");
                                        doorState.setTextColor(getResources().getColor(R.color.red));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                customDialog = new CustomDialog(MainStudentActivity.this, "이용에 불편을 드려 죄송합니다.\n잠시 후 다시 접속해 주세요.");
                                customDialog.show();

                                timer.cancel();
                            }
                        }
                );
                request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(request);
            }
        };

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
                timer.cancel();

                SharedPreference.removeAttribute(getApplicationContext(), "job");
                SharedPreference.removeAttribute(getApplicationContext(), "id");
                SharedPreference.removeAttribute(getApplicationContext(), "name");
                SharedPreference.removeAttribute(getApplicationContext(), "roomnumber");
                SharedPreference.removeAttribute(getApplicationContext(), "remember");

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

        timer.schedule(TT, 0, 1000); //Timer 실행
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
