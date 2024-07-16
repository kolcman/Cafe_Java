package com.kolcman.cafe_java;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTextName;
    private EditText editTextTextPassword;
    private Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();


        buttonSignIn.setOnClickListener(v -> {
            String userName = editTextTextName.getText().toString().trim();
            String password = editTextTextPassword.getText().toString().trim();

            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText( MainActivity.this,
                        getString(R.string.error_fields_empty_toast),
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                launchNextScreen(userName);
            }
        });
    }

    private void launchNextScreen(String userName){
        Intent intent = MakeOrderActivity.newIntent(this, userName);
        startActivity(intent);
    }

    private void initViews(){
        editTextTextName = findViewById(R.id.editTextTextName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
    }
}