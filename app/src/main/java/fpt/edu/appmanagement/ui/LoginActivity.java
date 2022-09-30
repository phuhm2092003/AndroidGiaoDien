package fpt.edu.appmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import fpt.edu.appmanagement.R;

public class LoginActivity extends AppCompatActivity {
    public static final String ADMIN = "admin";
    private TextInputLayout lUserName, lPassowrd;
    private TextInputEditText iUserName, iPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();


        Objects.requireNonNull(lUserName.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(iUserName.length() >0){
                    lUserName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Objects.requireNonNull(lPassowrd.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(iPassword.length() >0){
                    lPassowrd.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void anhXa() {
        // TextInputLayout
        lUserName = findViewById(R.id.layoutUserName);
        lPassowrd = findViewById(R.id.layoutPassword);
        // TexInputEditText
        iUserName = findViewById(R.id.iUserName);
        iPassword = findViewById(R.id.iPassword);
    }

    public void back(View view) {
        // chuyển qua màng hình welcome
        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
    }

    // xử lý sự kiện đăng nhập
    public void fLogin(View view) {
        if(iUserName.length() == 0){
            lUserName.setError("Vui lòng nhập tên đăng nhập !");
            lUserName.setErrorEnabled(true);
        }else {
            lUserName.setErrorEnabled(false);
        }
        if(iPassword.length() == 0){
            lPassowrd.setError("Vui lòng nhập mật khẩu !");
            lPassowrd.setErrorEnabled(true);
        }else {
            lPassowrd.setErrorEnabled(false);
        }

        if(iUserName.length() > 0 && iPassword.length() >0){
            if(Objects.requireNonNull(iUserName.getText()).toString().equals(ADMIN) && Objects.requireNonNull(iPassword.getText()).toString().equals(ADMIN)){
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}