package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class SignUpTeacherActivity extends AppCompatActivity {

    Button signinBtn, signupBtn, continueBtn;
    EditText tName, tId, tPwd1,tPwd2, tCertnum; // 회원가입할 선생님 이름, 인증번호
    Intent intent;
    String url = "http://10.120.74.188:8080/signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teacher);

        final RequestQueue queue = Volley.newRequestQueue(SignUpTeacherActivity.this);

        signinBtn = (Button)findViewById(R.id.tSigninBtn);
        signupBtn = (Button)findViewById(R.id.tSignupBtn);
        continueBtn = (Button)findViewById(R.id.tContinueBtn);

        tName = (EditText)findViewById(R.id.tName);
        tId = (EditText)findViewById(R.id.tId);
        tPwd1 = (EditText)findViewById(R.id.tPwd1);
        tPwd2 = (EditText)findViewById(R.id.tPwd2);
        tCertnum = (EditText) findViewById(R.id.tCertnum);

        tId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 텍스트가 입력되기 전
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 텍스트 변화가 있을 때
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 텍스트 입력된 후


            }
        });

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
                if (tName.getText().toString().equals("")) {
                    ToastCustom("이름을 입력해주세요");
                    return;
                }
                if (tId.getText().toString().equals("")) {
                    ToastCustom("ID를 입력해주세요.");
                    return;
                }
                if (tPwd1.getText().toString().equals("")) {
                    ToastCustom("비밀번호를 입력해주세요");
                    return;
                }
                if (tPwd2.getText().toString().equals("")) {
                    ToastCustom("비밀번호를 다시 한 번 입력해주세요");
                    return;
                }
                if (tCertnum.getText().toString().equals("")) {
                    ToastCustom("인증번호를 입력해주세요");
                    return;
                }

                if(tPwd1.getText().toString().equals(tPwd2.getText().toString())){
                    HashMap<String, String> data = new HashMap<>();
                    data.put("name", tName.getText().toString());
                    data.put("id", tId.getText().toString());
                    data.put("pw", tPwd1.getText().toString());
                    data.put("level","2");
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
                                            ToastCustom("인증번호가 일치하지 않습니다.");
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
                    // snackbar
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
