package com.example.slock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import org.w3c.dom.Text;

import java.util.HashMap;

public class SignUpStudentActivity extends AppCompatActivity {

    Button signinBtn, signupBtn, continueBtn;
    EditText sName, sId, sPwd1, sPwd2, sStunum, sRoomnum; // 회원가입할 학생 이름, 아이디, 학번, 방번호
    Intent intent;
    String url = "http://10.120.74.188:8080/signup";
    ImageView loadingImage;
    Vibrator vibrator;
    CustomAnimation customAnimation;
    TextView warnName, warnId, warnPwd1, warnPwd2, warnStunum, warnRoomnum, overlapId;
    InputMethodManager imm;
    ImageButton deleteNameBtn, deleteIdBtn, deletePwd1Btn, deletePwd2Btn, deleteStunumBtn, deleteRoomnumBtn, see1Btn, see2Btn;
    Boolean flag1 = true, flag2 = true;
    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        final RequestQueue queue = Volley.newRequestQueue(SignUpStudentActivity.this);

        loadingImage = (ImageView)findViewById(R.id.loadingImage);

        customAnimation = new CustomAnimation(SignUpStudentActivity.this);

        signinBtn = (Button)findViewById(R.id.sSigninBtn);
        signupBtn = (Button)findViewById(R.id.sSignupBtn);
        continueBtn = (Button)findViewById(R.id.sContinueBtn);

        sName = (EditText)findViewById(R.id.sName);
        sId = (EditText)findViewById(R.id.sId);
        sPwd1 = (EditText)findViewById(R.id.sPwd1);
        sPwd2 = (EditText)findViewById(R.id.sPwd2);
        sStunum = (EditText) findViewById(R.id.sStunum);
        sRoomnum = (EditText) findViewById(R.id.sRoomnum);

        warnName = (TextView)findViewById(R.id.sWarnName);
        warnId = (TextView)findViewById(R.id.sWarnId);
        warnPwd1 = (TextView)findViewById(R.id.sWarnPwd1);
        warnPwd2 = (TextView)findViewById(R.id.sWarnPwd2);
        warnStunum = (TextView) findViewById(R.id.sWarnStunum);
        warnRoomnum = (TextView) findViewById(R.id.sWarnRoomnum);
        overlapId = (TextView)findViewById(R.id.sOverlapId);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        deleteNameBtn = (ImageButton)findViewById(R.id.sNameDel);
        deleteIdBtn = (ImageButton)findViewById(R.id.sIdDel);
        deletePwd1Btn = (ImageButton)findViewById(R.id.sPwd1Del);
        deletePwd2Btn = (ImageButton)findViewById(R.id.sPwd2Del);
        deleteStunumBtn = (ImageButton)findViewById(R.id.sStunumDel);
        deleteRoomnumBtn = (ImageButton)findViewById(R.id.sRoomnumDel);
        see1Btn = (ImageButton)findViewById(R.id.sPwd1See);
        see2Btn = (ImageButton)findViewById(R.id.sPwd2See);

        sName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnName.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnName.setVisibility(View.INVISIBLE);

                if(!sName.getText().toString().equals("")){
                    deleteNameBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteNameBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sName.getText().toString().equals("") || sId.getText().toString().equals("") || sPwd1.getText().toString().equals("") || sPwd2.getText().toString().equals("") || sStunum.getText().toString().equals("") || sRoomnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        sId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnId.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnId.setVisibility(View.INVISIBLE);

                if(!sId.getText().toString().equals("")){
                    deleteIdBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteIdBtn.setVisibility(View.GONE);
                }

                // 아이디 중복 체크
                HashMap<String, String> data = new HashMap<>();
                data.put("id", sId.getText().toString());
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
                if(sName.getText().toString().equals("") || sId.getText().toString().equals("") || sPwd1.getText().toString().equals("") || sPwd2.getText().toString().equals("") || sStunum.getText().toString().equals("") || sRoomnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        sPwd1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnPwd1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPwd1.setVisibility(View.INVISIBLE);

                if(!sPwd1.getText().toString().equals("")){
                    deletePwd1Btn.setVisibility(View.VISIBLE);
                }
                else{
                    deletePwd1Btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sName.getText().toString().equals("") || sId.getText().toString().equals("") || sPwd1.getText().toString().equals("") || sPwd2.getText().toString().equals("") || sStunum.getText().toString().equals("") || sRoomnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        sPwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnPwd2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPwd2.setVisibility(View.INVISIBLE);

                if(!sPwd2.getText().toString().equals("")){
                    deletePwd2Btn.setVisibility(View.VISIBLE);
                }
                else{
                    deletePwd2Btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sName.getText().toString().equals("") || sId.getText().toString().equals("") || sPwd1.getText().toString().equals("") || sPwd2.getText().toString().equals("") || sStunum.getText().toString().equals("") || sRoomnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        sStunum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnStunum.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnStunum.setVisibility(View.INVISIBLE);

                if(!sStunum.getText().toString().equals("")){
                    deleteStunumBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteStunumBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sName.getText().toString().equals("") || sId.getText().toString().equals("") || sPwd1.getText().toString().equals("") || sPwd2.getText().toString().equals("") || sStunum.getText().toString().equals("") || sRoomnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        sRoomnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnRoomnum.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnRoomnum.setVisibility(View.INVISIBLE);

                if(!sRoomnum.getText().toString().equals("")){
                    deleteRoomnumBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteRoomnumBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(sName.getText().toString().equals("") || sId.getText().toString().equals("") || sPwd1.getText().toString().equals("") || sPwd2.getText().toString().equals("") || sStunum.getText().toString().equals("") || sRoomnum.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        sName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteNameBtn.setVisibility(View.GONE);
                }
                else{
                    if(!sName.getText().toString().equals("")){
                        deleteNameBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        sId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteIdBtn.setVisibility(View.GONE);
                }
                else{
                    if(!sId.getText().toString().equals("")){
                        deleteIdBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        sPwd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deletePwd1Btn.setVisibility(View.GONE);
                }
                else{
                    if(!sPwd1.getText().toString().equals("")){
                        deletePwd1Btn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        sPwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deletePwd2Btn.setVisibility(View.GONE);
                }
                else{
                    if(!sPwd2.getText().toString().equals("")){
                        deletePwd2Btn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        sStunum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteStunumBtn.setVisibility(View.GONE);
                }
                else{
                    if(!sStunum.getText().toString().equals("")){
                        deleteStunumBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        sRoomnum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteRoomnumBtn.setVisibility(View.GONE);
                }
                else{
                    if(!sRoomnum.getText().toString().equals("")){
                        deleteRoomnumBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        sRoomnum.setOnEditorActionListener(new TextView.OnEditorActionListener() { // Password 입력 후 키보드의 완료 버튼을 누르면
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch(actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        continueBtn.callOnClick(); // 자동으로 continue 버튼을 눌러줌
                        break;
                }
                return false;
            }
        });

        deleteNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName.setText("");
            }
        });
        deleteIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sId.setText("");
            }
        });
        deletePwd1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPwd1.setText("");
            }
        });
        deletePwd2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPwd2.setText("");
            }
        });
        deleteStunumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sStunum.setText("");
            }
        });
        deleteRoomnumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sRoomnum.setText("");
            }
        });

        see1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1 == true){
                    see1Btn.setImageResource(R.drawable.eye_open);
                    sPwd1.setInputType(InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    flag1 = false;
                }
                else{
                    see1Btn.setImageResource(R.drawable.eye_close);
                    sPwd1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    flag1 = true;
                }
                sPwd1.setSelection(sPwd1.length());
            }
        });

        see2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2 == true){
                    see2Btn.setImageResource(R.drawable.eye_open);
                    sPwd2.setInputType(InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    flag2 = false;
                }
                else{
                    see2Btn.setImageResource(R.drawable.eye_close);
                    sPwd2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    flag2 = true;
                }
                sPwd2.setSelection(sPwd2.length());
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
                        warnRoomnum.setVisibility(View.VISIBLE);

                        sRoomnum.requestFocus();
                        imm.showSoftInput(sRoomnum, 0);
                    }
                    if (sStunum.getText().toString().equals("")) {
                        warnStunum.setVisibility(View.VISIBLE);

                        sStunum.requestFocus();
                        imm.showSoftInput(sStunum, 0);
                    }
                    if (sPwd2.getText().toString().equals("")) {
                        warnPwd2.setVisibility(View.VISIBLE);

                        sPwd2.requestFocus();
                        imm.showSoftInput(sPwd2, 0);
                    }
                    if (sPwd1.getText().toString().equals("")) {
                        warnPwd1.setVisibility(View.VISIBLE);

                        sPwd1.requestFocus();
                        imm.showSoftInput(sPwd1, 0);
                    }
                    if (sId.getText().toString().equals("")) {
                        warnId.setVisibility(View.VISIBLE);

                        sId.requestFocus();
                        imm.showSoftInput(sId, 0);
                    }
                    if (sName.getText().toString().equals("")) {
                        warnName.setVisibility(View.VISIBLE);

                        sName.requestFocus();
                        imm.showSoftInput(sName, 0);
                    }
                }
                else{
                    if(!sPwd1.getText().toString().equals(sPwd2.getText().toString())){
                        customDialog = new CustomDialog(SignUpStudentActivity.this, "비밀번호를 확인해 주세요.");
                        customDialog.show();

                        return;
                    }
                    if(sStunum.getText().length() != 4){
                        customDialog = new CustomDialog(SignUpStudentActivity.this, "학번은 4자리 숫자로 입력해 주세요.");
                        customDialog.show();

                        return;
                    }
                    if(sRoomnum.getText().length() != 3){
                        customDialog = new CustomDialog(SignUpStudentActivity.this, "호실번호는 3자리 숫자로 입력해 주세요.");
                        customDialog.show();

                        return;
                    }
                    if(!((Integer.parseInt(sRoomnum.getText().toString()) >= 301 && Integer.parseInt(sRoomnum.getText().toString()) <= 320)
                            ||  (Integer.parseInt(sRoomnum.getText().toString()) >= 401 && Integer.parseInt(sRoomnum.getText().toString()) <= 420)
                            ||  (Integer.parseInt(sRoomnum.getText().toString()) >= 501 && Integer.parseInt(sRoomnum.getText().toString()) <= 520))){
                        customDialog = new CustomDialog(SignUpStudentActivity.this, "호실은 3, 4, 5층 각 1호부터 20호 내로 입력해 주세요.");
                        customDialog.show();

                        return;
                    }

                    customAnimation.show();

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

                                        customAnimation.dismiss();

                                        if(result.equals("ok")){
                                            startActivity(intent);
                                            finish();
                                        }
                                        else{
                                            customDialog = new CustomDialog(SignUpStudentActivity.this, "죄송합니다. 오류로 인하여 회원가입을 실패했습니다.");
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

                            customDialog = new CustomDialog(SignUpStudentActivity.this, "이용에 불편을 드려 죄송합니다.\n잠시 후 다시 접속해 주세요.");
                            customDialog.show();
                        }
                    }
                    );
                    request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(request);
                }
            }
        });
    }
}