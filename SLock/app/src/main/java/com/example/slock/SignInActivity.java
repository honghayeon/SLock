package com.example.slock;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    Button signupBtn, continueBtn;
    EditText inId, inPwd;
    ImageView loadingImage;
    Vibrator vibrator;
    CustomAnimation customAnimation;
    TextView warnId, warnPwd;
    InputMethodManager imm;
    ImageButton deleteIdBtn, deletePwdBtn, seeBtn;
    Boolean flag = true;
    CheckBox check;
    int posPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);

        signupBtn = (Button)findViewById(R.id.inSignupBtn);
        continueBtn = (Button)findViewById(R.id.inContinueBtn);

        inId = (EditText)findViewById(R.id.inId);
        inPwd = (EditText)findViewById(R.id.inPwd);

        loadingImage = (ImageView)findViewById(R.id.loadingImage);

        customAnimation = new CustomAnimation(SignInActivity.this);

        warnId = (TextView)findViewById(R.id.inWarnId);
        warnPwd = (TextView)findViewById(R.id.inWarnPwd);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        deleteIdBtn = (ImageButton)findViewById(R.id.inIdDel);
        deletePwdBtn = (ImageButton)findViewById(R.id.inPwdDel);
        seeBtn = (ImageButton)findViewById(R.id.inPwdSee);

        check = (CheckBox)findViewById(R.id.checkbox);

        inPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() { // Password 입력 후 키보드의 완료 버튼을 누르면
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

        inId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnId.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnId.setVisibility(View.INVISIBLE);

                if(!inId.getText().toString().equals("")){
                    deleteIdBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deleteIdBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(inId.getText().toString().equals("") || inPwd.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        inId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { // EdiText에 포커스가 있는지 확인
                if(!hasFocus){ // 만약 포커스가 되어있지 않으면
                    deleteIdBtn.setVisibility(View.GONE);
                }
            }
        });

        inPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnPwd.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPwd.setVisibility(View.INVISIBLE);

                if(!inPwd.getText().toString().equals("")){
                    deletePwdBtn.setVisibility(View.VISIBLE);
                }
                else{
                    deletePwdBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                posPwd = inPwd.getSelectionEnd(); // inputType이 바뀌면 자동으로 커서가 맨 앞으로 이동하기 때문에 현재 커서 위치를 저장해놓음

                if(inId.getText().toString().equals("") || inPwd.getText().toString().equals("")){
                    continueBtn.setBackgroundResource(R.drawable.button2_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.mainPurple));
                }
                else{
                    continueBtn.setBackgroundResource(R.drawable.button_background);
                    continueBtn.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        inPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    deletePwdBtn.setVisibility(View.GONE);
                }
            }
        });

        deleteIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inId.setText("");
            }
        });
        deletePwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inPwd.setText("");
            }
        });

        seeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == true){
                    seeBtn.setImageResource(R.drawable.eye_open);
                    inPwd.setInputType(InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    if (posPwd >= 0) {
                        inPwd.setSelection(posPwd);
                    }

                    flag = false;
                }
                else{
                    seeBtn.setImageResource(R.drawable.eye_close);
                    inPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                    if (posPwd >= 0) {
                        inPwd.setSelection(posPwd);
                    }

                    flag = true;
                }
            }
        });

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
                Intent intent2 = new Intent(getApplicationContext(), MainTeacherActivity.class);
                startActivity(intent2);

                if(inId.getText().toString().equals("") || inPwd.getText().toString().equals("")){
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(200);

                    if (inPwd.getText().toString().equals("")) {
                        warnPwd.setVisibility(View.VISIBLE);

                        inPwd.requestFocus();
                        imm.showSoftInput(inPwd,0);
                    }
                    if (inId.getText().toString().equals("")) {
                        warnId.setVisibility(View.VISIBLE);

                        inId.requestFocus(); // id에 자동 포커스 맞추기
                        imm.showSoftInput(inId,0); // 키보드
                    }
                }
                else{
                    customAnimation.show();

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
                                        String result = jsonObject.getString("level");
                                        String name = jsonObject.getString("name");

                                        if(result.equals("1")){
                                            String roomnumber =  jsonObject.getString("rnum");

                                            if(check.isChecked()){
                                                SharedPreference.setAttribute(getApplicationContext(), "job", "student");
                                                SharedPreference.setAttribute(getApplicationContext(), "id", inId.getText().toString());
                                                SharedPreference.setAttribute(getApplicationContext(), "name", name);
                                                SharedPreference.setAttribute(getApplicationContext(), "roomnumber", roomnumber);
                                            }

                                            ToastCustom(name +  " 학생님 환영합니다!");

                                            customAnimation.dismiss();

                                            Intent intent1 = new Intent(getApplicationContext(), MainStudentActivity.class);
                                            startActivity(intent1);
                                        }
                                        else if(result.equals("2")){
                                            if(check.isChecked()){
                                                SharedPreference.setAttribute(getApplicationContext(), "job", "teacher");
                                                SharedPreference.setAttribute(getApplicationContext(), "id", inId.getText().toString());
                                                SharedPreference.setAttribute(getApplicationContext(), "name", name);
                                            }

                                            ToastCustom(name +  " 선생님 환영합니다!");

                                            customAnimation.dismiss();

                                            Intent intent2 = new Intent(getApplicationContext(), MainTeacherActivity.class);
                                            startActivity(intent2);
                                        }
                                        else{
                                            customAnimation.dismiss();

                                            ToastCustom("아이디와 비밀번호가 일치하지 않습니다.");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            customAnimation.dismiss();

//                        Toast.makeText(getApplicationContext(), "서버가 꺼져있습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    );
                    request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(request);
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
