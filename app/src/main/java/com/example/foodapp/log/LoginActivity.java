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
import com.example.foodapp.activity.ManageActivity;
import com.example.foodapp.dao.AccountDAO;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout btnToRegister;
    private Button btnLogin, btnFbLogin, btnGgLogin;
    private EditText edtPhone, edtPassword;
    private AccountDAO accountDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        btnToRegister = findViewById(R.id.btnToRegister);
        btnLogin = findViewById(R.id.btnLogin);
        btnFbLogin = findViewById(R.id.btnFb);
        btnGgLogin = findViewById(R.id.btnGg);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
    }

    private void addEvents() {
        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        btnFbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Facebook login logic here
            }
        });

        btnGgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Google login logic here
            }
        });
    }

    private void handleLogin() {
        String phone = edtPhone.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isValidUser = accountDAO.loginUser(phone, password);
        if (isValidUser) {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, ManageActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Số điện thoại hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }
}

