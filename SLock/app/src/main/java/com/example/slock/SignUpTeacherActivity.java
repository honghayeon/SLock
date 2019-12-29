package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    ImageView loadingImage;
    Vibrator vibrator;
    CustomAnimation customAnimation;
    TextView warnName, warnId, warnPwd1, warnPwd2, warnCertnum, overlapId;
    InputMethodManager imm;
    ImageButton deleteNameBtn, deleteIdBtn, deletePwd1Btn, deletePwd2Btn, deleteCertnumBtn, see1Btn, see2Btn;
    Boolean flag1 = true, flag2 = true;
    int posPwd1, posPwd2;
    CustomDialog customDialog;

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

        warnName = (TextView)findViewById(R.id.tWarnName);
        warnId = (TextView)findViewById(R.id.tWarnId);
        warnPwd1 = (TextView)findViewById(R.id.tWarnPwd1);
        warnPwd2 = (TextView)findViewById(R.id.tWarnPwd2);
        warnCertnum = (TextView)findViewById(R.id.tWarnCertnum);
        overlapId = (TextView)findViewById(R.id.tOverlapId);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        deleteNameBtn = (ImageButton)findViewById(R.id.tNameDel);
        deleteIdBtn = (ImageButton)findViewById(R.id.tIdDel);
        deletePwd1Btn = (ImageButton)findViewById(R.id.tPwd1Del);
        deletePwd2Btn = (ImageButton)findViewById(R.id.tPwd2Del);
        deleteCertnumBtn = (ImageButton)findViewById(R.id.tCertnumDel);
        see1Btn = (ImageButton)findViewById(R.id.tPwd1See);
        see2Btn = (ImageButton)findViewById(R.id.tPwd2See);

        tName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnName.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnName.setVisibility(View.INVISIBLE);

                if(!tName.getText().toString().equals("")){
                    deleteNameBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteNameBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tName.getText().toString().equals("") || tId.getText().toString().equals("") || tPwd1.getText().toString().equals("") || tPwd2.getText().toString().equals("") || tCertnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        tId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnId.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnId.setVisibility(View.INVISIBLE);

                if(!tId.getText().toString().equals("")){
                    deleteIdBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteIdBtn.setVisibility(View.GONE);
                }

                // 아이디 중복 체크
                HashMap<String, String> data = new HashMap<>();
                data.put("id", tId.getText().toString());
                // Request a string response from the provided URL
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://10.120.74.188:8080/idduplicate", new JSONObject(data),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    String result=jsonObject.getString("status");
                                    if(!result.equals("ok")){
                                        overlapId.setVisibility(View.VISIBLE);
                                    }
                                    else{
                                        overlapId.setVisibility(View.INVISIBLE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
                );
                request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(request);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tName.getText().toString().equals("") || tId.getText().toString().equals("") || tPwd1.getText().toString().equals("") || tPwd2.getText().toString().equals("") || tCertnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        tPwd1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnPwd1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPwd1.setVisibility(View.INVISIBLE);

                if(!tPwd1.getText().toString().equals("")){
                    deletePwd1Btn.setVisibility(View.VISIBLE);
                }
                else{
                    deletePwd1Btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                posPwd1 = tPwd1.getSelectionEnd(); // inputType이 바뀌면 자동으로 커서가 맨 앞으로 이동하기 때문에 현재 커서 위치를 저장해놓음

                if(tName.getText().toString().equals("") || tId.getText().toString().equals("") || tPwd1.getText().toString().equals("") || tPwd2.getText().toString().equals("") || tCertnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        tPwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnPwd2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPwd2.setVisibility(View.INVISIBLE);

                if(!tPwd2.getText().toString().equals("")){
                    deletePwd2Btn.setVisibility(View.VISIBLE);
                }
                else{
                    deletePwd2Btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                posPwd2 = tPwd2.getSelectionEnd(); // inputType이 바뀌면 자동으로 커서가 맨 앞으로 이동하기 때문에 현재 커서 위치를 저장해놓음

                if(tName.getText().toString().equals("") || tId.getText().toString().equals("") || tPwd1.getText().toString().equals("") || tPwd2.getText().toString().equals("") || tCertnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        tCertnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnCertnum.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnCertnum.setVisibility(View.INVISIBLE);

                if(!tCertnum.getText().toString().equals("")){
                    deleteCertnumBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteCertnumBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(tName.getText().toString().equals("") || tId.getText().toString().equals("") || tPwd1.getText().toString().equals("") || tPwd2.getText().toString().equals("") || tCertnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });


        tName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteNameBtn.setVisibility(View.GONE);
                }
                else{
                    if(!tName.getText().toString().equals("")){
                        deleteNameBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        tId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteIdBtn.setVisibility(View.GONE);
                }
                else{
                    if(!tId.getText().toString().equals("")){
                        deleteIdBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        tPwd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deletePwd1Btn.setVisibility(View.GONE);
                }
                else{
                    if(!tPwd1.getText().toString().equals("")){
                        deletePwd1Btn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        tPwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deletePwd2Btn.setVisibility(View.GONE);
                }
                else{
                    if(!tPwd2.getText().toString().equals("")){
                        deletePwd2Btn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        tCertnum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteCertnumBtn.setVisibility(View.GONE);
                }
                else{
                    if(!tCertnum.getText().toString().equals("")){
                        deleteCertnumBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        deleteNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tName.setText("");
            }
        });
        deleteIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tId.setText("");
            }
        });
        deletePwd1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPwd1.setText("");
            }
        });
        deletePwd2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPwd2.setText("");
            }
        });
        deleteCertnumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tCertnum.setText("");
            }
        });

        see1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1 == true){
                    see1Btn.setImageResource(R.drawable.eye_open);
                    tPwd1.setInputType(InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    if (posPwd1 >= 0) {
                        tPwd1.setSelection(posPwd1);
                    }

                    flag1 = false;
                }
                else{
                    see1Btn.setImageResource(R.drawable.eye_close);
                    tPwd1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    if (posPwd1 >= 0) {
                        tPwd1.setSelection(posPwd1);
                    }

                    flag1 = true;
                }
            }
        });

        see2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2 == true){
                    see2Btn.setImageResource(R.drawable.eye_open);
                    tPwd2.setInputType(InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    if (posPwd2 >= 0) {
                        tPwd2.setSelection(posPwd2);
                    }

                    flag2 = false;
                }
                else{
                    see2Btn.setImageResource(R.drawable.eye_close);
                    tPwd2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    if (posPwd2 >= 0) {
                        tPwd2.setSelection(posPwd2);
                    }

                    flag2 = true;
                }
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
                if(tName.getText().toString().equals("") || tId.getText().toString().equals("") || tPwd1.getText().toString().equals("") || tPwd2.getText().toString().equals("") || tCertnum.getText().toString().equals("")){
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(200);

                    if (tCertnum.getText().toString().equals("")) {
                        warnCertnum.setVisibility(View.VISIBLE);

                        tCertnum.requestFocus();
                        imm.showSoftInput(tCertnum, 0);
                    }
                    if (tPwd2.getText().toString().equals("")) {
                        warnPwd2.setVisibility(View.VISIBLE);

                        tPwd2.requestFocus();
                        imm.showSoftInput(tPwd2, 0);
                    }
                    if (tPwd1.getText().toString().equals("")) {
                        warnPwd1.setVisibility(View.VISIBLE);

                        tPwd1.requestFocus();
                        imm.showSoftInput(tPwd1, 0);
                    }
                    if (tId.getText().toString().equals("")) {
                        warnId.setVisibility(View.VISIBLE);

                        tId.requestFocus();
                        imm.showSoftInput(tId, 0);
                    }
                    if (tName.getText().toString().equals("")) {
                        warnName.setVisibility(View.VISIBLE);

                        tName.requestFocus();
                        imm.showSoftInput(tName, 0);
                    }
                }
                else {
                    if (!tPwd1.getText().toString().equals(tPwd2.getText().toString())) {
                        customDialog = new CustomDialog(SignUpTeacherActivity.this, "비밀번호가 일치하지않습니다.");
                        customDialog.show();

                        return;
                    }
                    if (tCertnum.getText().length() != 4) {
                        customDialog = new CustomDialog(SignUpTeacherActivity.this, "인증번호는 4자리 숫자로 이루어져있습니다.");
                        customDialog.show();

                        return;
                    }

                    customAnimation.show();

                    HashMap<String, String> data = new HashMap<>();
                    data.put("name", tName.getText().toString());
                    data.put("id", tId.getText().toString());
                    data.put("pw", tPwd1.getText().toString());
                    data.put("level", "2");
                    // Request a string response from the provided URL
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response.toString());
                                        String result = jsonObject.getString("status");

                                        customAnimation.dismiss();

                                        if (result.equals("ok")) {
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            customDialog = new CustomDialog(SignUpTeacherActivity.this, "죄송합니다. 오류로 인하여 회원가입을 실패했습니다.");
                                            customDialog.show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            customAnimation.dismiss();

                            customDialog = new CustomDialog(SignUpTeacherActivity.this, "죄송합니다. 오류로 인하여 서버가 실행되지 않고 있습니다. 잠시 후 접속해주십시오.");
                            customDialog.show();
                        }
                    });
                    request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(request);
                 }
            }
        });
    }
}
