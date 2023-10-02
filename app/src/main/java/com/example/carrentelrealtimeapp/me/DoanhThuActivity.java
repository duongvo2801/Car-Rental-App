package com.example.carrentelrealtimeapp.me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carrentelrealtimeapp.R;
import com.example.carrentelrealtimeapp.dao.Top10DAO;

import java.util.Calendar;

public class DoanhThuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);

        EditText etStart = findViewById(R.id.etStart);
        EditText etEnd = findViewById(R.id.etEnd);
        Button btnThongKe = findViewById(R.id.btnThongKe);
        TextView tvResult = findViewById(R.id.tvResult);

        Calendar calendar = Calendar.getInstance();
        etStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DoanhThuActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) { //2022/10/22 (i = năm)
                                String days = "";
                                String months = "";
                                if(i2 < 10) {
                                    days = "0" + i2;
                                } else {
                                    days = String.valueOf(i2);
                                }
                                if((i1 + 1) < 10) {
                                    months = "0" + (i1 + 1);
                                } else {
                                    months = String.valueOf((i1 + 1));
                                }
                                etStart.setText(i + "/"  + months + "/" + days);
                            }
                        },
                        // ngày hiện tại
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        etEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DoanhThuActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) { //2022/10/22 (i = năm)
                                String days = "";
                                String months = "";
                                if(i2 < 10) {
                                    days = "0" + i2;
                                } else {
                                    days = String.valueOf(i2);
                                }
                                if((i1 + 1) < 10) {
                                    months = "0" + (i1 + 1);
                                } else {
                                    months = String.valueOf((i1 + 1));
                                }

                                etEnd.setText(i + "/"  + months + "/" + days);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Top10DAO top10DAO = new Top10DAO(DoanhThuActivity.this);
                String startDay = etStart.getText().toString();
                String endDay = etEnd.getText().toString();
                int doanhThu = top10DAO.getDoanhThu(startDay, endDay);
                tvResult.setText(doanhThu + " VND");
            }
        });


        // status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.theme_tele));
    }
}