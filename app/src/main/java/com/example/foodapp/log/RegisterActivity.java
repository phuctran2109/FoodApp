package com.example.foodapp.log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.dao.AccountDAO;

public class RegisterActivity extends AppCompatActivity {
    private LinearLayout btnGoToLogin;
    private EditText edtPhone, edtName, edtPass, edtRePass;
    private Button btnRegister, btnFbRegister, btnGgRegister;
    private AccountDAO accountDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        accountDAO = new AccountDAO(this);
        accountDAO.open();
        addControls();
        addEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accountDAO.close();
    }

    private void addControls() {
        btnGoToLogin = findViewById(R.id.btnToLogin);
        edtPhone = findViewById(R.id.edtPhoneRegis);
        edtName = findViewById(R.id.edtNameRegis);
        edtPass = findViewById(R.id.edtPassRegis);
        edtRePass = findViewById(R.id.edtRePassRegis);
        btnRegister = findViewById(R.id.btnRegister);
        btnFbRegister = findViewById(R.id.btnFbRegis);
        btnGgRegister = findViewById(R.id.btnGgRegis);
    }

    private void addEvents() {
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();
            }
        });

        btnFbRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Facebook registration logic here
            }
        });

        btnGgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Google registration logic here
            }
        });
    }

    private void handleRegister() {
        String phone = edtPhone.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        String rePassword = edtRePass.getText().toString().trim();

        if (phone.isEmpty() || name.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register the user
        long userId = accountDAO.registerUser(phone, name, password); // Assuming password is stored as a plain text, you should hash it in production

        if (userId != -1) {
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
