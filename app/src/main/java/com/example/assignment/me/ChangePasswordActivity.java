package com.example.assignment.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.dao.ThuThuDAO;
import com.example.assignment.account.Login;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText etOldPass, etNewPass, etReNewPass;
    private Button btnChangePass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etOldPass = findViewById(R.id.etOldPass);
        etNewPass = findViewById(R.id.etNewPass);
        etReNewPass = findViewById(R.id.etReNewPass);
        btnChangePass = findViewById(R.id.btnChangePass);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = etOldPass.getText().toString();
                String newPass = etNewPass.getText().toString();
                String reNewPass = etReNewPass.getText().toString();

                if(oldPass.equals("") || newPass.equals("") || reNewPass.equals("")) {
                    Toast.makeText(ChangePasswordActivity.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if(newPass.equals(reNewPass)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                        String maTT = sharedPreferences.getString("maTT", "");

                        // update
                        ThuThuDAO thuThuDAO = new ThuThuDAO(ChangePasswordActivity.this);
                        boolean check = thuThuDAO.updatePass(maTT, oldPass, newPass);
                        if(check) {
                            Toast.makeText(ChangePasswordActivity.this, "Cật nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChangePasswordActivity.this, Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, "Cật nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        // status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.theme_tele));
    }
}