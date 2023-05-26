package com.example.assignment.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.dao.ThuThuDAO;
import com.example.assignment.database.DbHelper;

public class Register extends AppCompatActivity {
    EditText etUsername, etPassword, etRePassword;
    Button btnRegister;
    DbHelper dbHelper;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);


        thuThuDAO = new ThuThuDAO(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                String rePass = etRePassword.getText().toString();
                if(user.equals("") || pass.equals("") || rePass.equals("")) {
                    Toast.makeText(Register.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(rePass)) {
                        Boolean checkUser = ThuThuDAO.checkUser(user);
                        if (checkUser == false) {
                            Boolean checkRegister = ThuThuDAO.register(user, pass);
                            if(checkRegister == true) {
                                Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            } else {
                                Toast.makeText(Register.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "Kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.theme_tele));
    }
}