package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class QuenMatKhau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quen_mat_khau);

        //Khai báo biến và ánh xạ
        TextInputEditText edtSDtFogot = findViewById(R.id.edtSdtFogot);
        Button btnReset = findViewById(R.id.btnReset);

//        db = new DbHelper(this);

        //Bắt sự kiện click btnReset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soDienThoai = edtSDtFogot.getText().toString();
//                boolean check = db.checkUsername(soDienThoai);
                if (soDienThoai == "0123456789"){
                    Intent intent = new Intent(QuenMatKhau.this, ResetPassword.class);
                    intent.putExtra("Số điện thoại",soDienThoai);
                    startActivity(intent);
                }else {
                    Toast.makeText(QuenMatKhau.this, "Số điện thoại không đúng!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}