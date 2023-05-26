package com.example.assignment.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assignment.MainActivity;
import com.example.assignment.R;
import com.example.assignment.dao.ThuThuDAO;

public class Login extends AppCompatActivity {
    TextView tvRegister;
    EditText etUsername, etPassword;
    Button btnLogin;
    private ImageView ivLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister = (TextView) findViewById(R.id.tvRegister);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

//        ivLogin = findViewById(R.id.ivLogin);
//        Glide.with(this).load(R.drawable.login).into(ivLogin);

        // khởi tạo 1 lần (nếu sai)
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                if(thuThuDAO.checkLogin(user, pass)){
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(Login.this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(Login.this, R.color.theme_tele));
    }
}