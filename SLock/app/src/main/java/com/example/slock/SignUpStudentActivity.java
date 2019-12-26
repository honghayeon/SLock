package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class SignUpStudentActivity extends AppCompatActivity {

    final RequestQueue queue = Volley.newRequestQueue(SignUpStudentActivity.this);

    Button signinBtn, signupBtn, continueBtn;
    EditText sName, sId, sPwd1, sPwd2, sStunum, sRoomnum; // 회원가입할 학생 이름, 아이디, 학번, 방번호
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        signinBtn = (Button)findViewById(R.id.sSigninBtn);
        signupBtn = (Button)findViewById(R.id.sSignupBtn);
        continueBtn = (Button)findViewById(R.id.sContinueBtn);

        sName = (EditText)findViewById(R.id.sName);
        sId = (EditText)findViewById(R.id.sId);
        sPwd1 = (EditText)findViewById(R.id.sPwd1);
        sPwd2 = (EditText)findViewById(R.id.sPwd2);
        sStunum = (EditText) findViewById(R.id.sStunum);
        sRoomnum = (EditText) findViewById(R.id.sRoomnum);

        intent = new Intent(getApplicationContext(), SignInActivity.class);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sName.getText().toString().equals("")) {
                    ToastCustom("이름을 입력해주세요");
                    return;
                }
                if (sId.getText().toString().equals("")) {
                    ToastCustom("ID를 입력해주세요.");
                    return;
                }
                if (sPwd1.getText().toString().equals("")) {
                    ToastCustom("비밀번호를 입력해주세요");
                    return;
                }
                if (sPwd2.getText().toString().equals("")) {
                    ToastCustom("비밀번호를 다시 한 번 입력해주세요");
                    return;
                }
                if (sStunum.getText().toString().equals("")) {
                    ToastCustom("학생번호를 입력해주세요");
                    return;
                }
                if(sRoomnum.getText().toString().equals("")) {
                    ToastCustom("호실을 입력해주세요");
                    return;
                }

                if(sPwd1.getText().toString().equals(sPwd2.getText().toString())){
                    // ************꼭 URL 수정할 것*************
                    String url = "http://10.120.74.188:8080/login";
                    // ************꼭 URL 수정할 것*************
                    HashMap<String, String> data = new HashMap<>();
                    data.put("name", sName.getText().toString());
                    data.put("id", sId.getText().toString());
                    data.put("pw", sPwd1.getText().toString());
                    data.put("snum", sStunum.getText().toString());
                    data.put("rnum", sRoomnum.getText().toString());
                    // Request a string response from the provided URL
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.toString());
                                        String result=jsonObject.getString("status");
                                        if(result.equals("ok")){
                                            ToastCustom("회원가입 성공");
                                            // ToastCustom(resultName +  "님 회원가입을 환영합니다!");

                                            startActivity(intent);
                                        }
                                        else{
                                            ToastCustom("회원가입 실패");
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
                else{
                    ToastCustom("비밀번호가 일치하지 않습니다.");
                }
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
