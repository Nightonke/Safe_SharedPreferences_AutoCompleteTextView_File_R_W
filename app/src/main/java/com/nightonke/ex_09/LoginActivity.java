package com.nightonke.ex_09;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.nightonke.ex_09.AES.AES;

public class LoginActivity extends AppCompatActivity {

    private final String THE_KEY = "NightonkeSoFuck!";

    private EditText name;
    private EditText password;

    private Button register;
    private Button login;

    private CheckBox remember;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        name = (EditText)findViewById(R.id.user_name);
        password = (EditText)findViewById(R.id.password);

        register = (Button)findViewById(R.id.register);
        login = (Button)findViewById(R.id.login);

        remember = (CheckBox)findViewById(R.id.remember_password);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AES aes = new AES();
                aes.setKey(THE_KEY);

                SharedPreferences.Editor editor
                        = getSharedPreferences("User Data", MODE_PRIVATE).edit();
                editor.putString("NAME", aes.Encrypt(name.getText().toString()));
                editor.putString("PASSWORD", aes.Encrypt(password.getText().toString()));
                editor.commit();
                Toast.makeText(mContext,
                        "Welcome, " + name.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AES aes = new AES();
                aes.setKey(THE_KEY);

                SharedPreferences sharedPreferences
                        = getSharedPreferences("User Data", MODE_PRIVATE);
                if (name.getText().toString().
                        equals(aes.Decrypt(sharedPreferences.getString("NAME", "")))
                        && password.getText().toString().
                        equals(aes.Decrypt(sharedPreferences.getString("PASSWORD", "")))
                        && !"".equals(name.getText().toString())
                        && !"".equals(password.getText().toString())) {
                    // 注意用户名和密码为空的情况
                    // "".equals()这种写法可以有效避免null.equals()的情况
                    Toast.makeText(mContext,
                            "Welcome back!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, EditFileActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext,
                            "Hey, password is not correct!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {

        if (remember.isChecked()) {
            AES aes = new AES();
            aes.setKey(THE_KEY);

            SharedPreferences sharedPreferences = getSharedPreferences("User Data", MODE_PRIVATE);
            name.setText(aes.Decrypt(sharedPreferences.getString("NAME", "")));
            password.setText(aes.Decrypt(sharedPreferences.getString("PASSWORD", "")));

            // 如果已经自动填充了用户名和密码就不需要获取焦点，因为再次输入的可能性不大
            name.clearFocus();
            password.clearFocus();
            login.requestFocus();
        } else {
            // 如果不自动填充，那么让用户名获取焦点，并自动弹出键盘
            // 一定要记住在AndroidManifest中对应activity中加入：
            // android:windowSoftInputMode="stateAlwaysVisible"
            name.requestFocus();
            InputMethodManager keyboard
                    = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);
            name.setText("");
            password.setText("");
        }

        super.onResume();
    }
}
