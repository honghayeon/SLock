package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import org.w3c.dom.Text;

import java.util.HashMap;

public class SignUpStudentActivity extends AppCompatActivity {

    Button signinBtn, signupBtn, continueBtn;
    EditText sName, sId, sPwd1, sPwd2, sStunum, sRoomnum; // 회원가입할 학생 이름, 아이디, 학번, 방번호
    Intent intent;
    Vibrator vibrator;
    TextView sWarnName, sWarnId, sWarnPwd1, sWarnPwd2, sWarnStunum, sWarnRoomnum;
    InputMethodManager imm;
    String url = "http://10.120.74.188:8080/signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        final RequestQueue queue = Volley.newRequestQueue(SignUpStudentActivity.this);

        signinBtn = (Button)findViewById(R.id.sSigninBtn);
        signupBtn = (Button)findViewById(R.id.sSignupBtn);
        continueBtn = (Button)findViewById(R.id.sContinueBtn);

        sName = (EditText)findViewById(R.id.sName);
        sId = (EditText)findViewById(R.id.sId);
        sPwd1 = (EditText)findViewById(R.id.sPwd1);
        sPwd2 = (EditText)findViewById(R.id.sPwd2);
        sStunum = (EditText) findViewById(R.id.sStunum);
        sRoomnum = (EditText) findViewById(R.id.sRoomnum);

        sWarnName = (TextView)findViewById(R.id.sWarnName);
        sWarnId = (TextView)findViewById(R.id.sWarnId);
        sWarnPwd1 = (TextView)findViewById(R.id.sWarnPwd1);
        sWarnPwd2 = (TextView)findViewById(R.id.sWarnPwd2);
        sWarnStunum = (TextView) findViewById(R.id.sWarnStunum);
        sWarnRoomnum = (TextView) findViewById(R.id.sWarnRoomnum);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        sName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sWarnName.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sWarnId.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                HashMap<String, String> data = new HashMap<>();
                data.put("id", sId.getText().toString());
                // Request a string response from the provided URL
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    String result=jsonObject.getString("status");
                                    if(!result.equals("ok")){
                                        sWarnId.setText("* Cannot Use this ID");
                                        sWarnId.setVisibility(View.VISIBLE);
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

        sPwd1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sWarnPwd1.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sPwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sWarnPwd2.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sStunum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sWarnStunum.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sRoomnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sWarnRoomnum.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
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
                if(sName.getText().toString().equals("") || sId.getText().toString().equals("") || sPwd1.getText().toString().equals("") || sPwd2.getText().toString().equals("") || sStunum.getText().toString().equals("") || sRoomnum.getText().toString().equals("")){
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(200);

                    if(sRoomnum.getText().toString().equals("")) {
                        sWarnRoomnum.setVisibility(View.VISIBLE);

                        sRoomnum.requestFocus();
                        imm.showSoftInput(sRoomnum, 0);
                    }
                    if (sStunum.getText().toString().equals("")) {
                        sWarnStunum.setVisibility(View.VISIBLE);

                        sStunum.requestFocus();
                        imm.showSoftInput(sStunum, 0);
                    }
                    if (sPwd2.getText().toString().equals("")) {
                        sWarnPwd2.setVisibility(View.VISIBLE);

                        sPwd2.requestFocus();
                        imm.showSoftInput(sPwd2, 0);
                    }
                    if (sPwd1.getText().toString().equals("")) {
                        sWarnPwd1.setVisibility(View.VISIBLE);

                        sPwd1.requestFocus();
                        imm.showSoftInput(sPwd1, 0);
                    }
                    if (sId.getText().toString().equals("")) {
                        sWarnId.setVisibility(View.VISIBLE);

                        sId.requestFocus();
                        imm.showSoftInput(sId, 0);
                    }
                    if (sName.getText().toString().equals("")) {
                        sWarnName.setVisibility(View.VISIBLE);

                        sName.requestFocus();
                        imm.showSoftInput(sName, 0);
                    }
                }
                else{
                    if(sPwd1.getText().toString().equals(sPwd2.getText().toString())){
                        HashMap<String, String> data = new HashMap<>();
                        data.put("name", sName.getText().toString());
                        data.put("id", sId.getText().toString());
                        data.put("pw", sPwd1.getText().toString());
                        data.put("level","1");
                        data.put("num", sStunum.getText().toString());
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
                                                ToastCustom(sName.getText().toString() +  "님 회원가입을 환영합니다!");

                                                startActivity(intent);
                                            }
                                            else{
                                                ToastCustom("회원가입을 실패했습니다.");
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
                        // password 일치 X
                    }
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
