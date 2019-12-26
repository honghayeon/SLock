package com.example.slock;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    final RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);

    Button signupBtn, continueBtn;
    EditText inId, inPwd; // 로그인하기 위해 입력한 id와 pwd

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signupBtn = (Button)findViewById(R.id.inSignupBtn);
        continueBtn = (Button)findViewById(R.id.inContinueBtn);

        inId = (EditText)findViewById(R.id.inId);
        inPwd = (EditText)findViewById(R.id.inPwd);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainTeacherActivity.class);
//                startActivity(intent);

                if (inId.getText().toString().equals("")) {
                    ToastCustom("ID를 입력해주세요.");
                    return;
                }
                if (inPwd.getText().toString().equals("")) {
                    ToastCustom("비밀번호를 입력해주세요");
                    return;
                }
                String url = "http://10.120.74.188:8080/login";
                HashMap<String, String> data = new HashMap<>();
                data.put("id", inId.getText().toString());
                data.put("pw", inPwd.getText().toString());
                // Request a string response from the provided URL
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    String result=jsonObject.getString("status");
                                    if(result.equals("students")){
                                        ToastCustom("로그인 성공");
                                        // ToastCustom(resultName +  "님 로그인을 환영합니다!");

                                        Intent intent = new Intent(getApplicationContext(), MainStudentActivity.class);
                                        startActivity(intent);
                                    }
                                    else if(result.equals("teacher")){
                                        ToastCustom("로그인 성공");

                                        Intent intent = new Intent(getApplicationContext(), MainTeacherActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), "서버가 꺼져있습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                );
                request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(request);
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
