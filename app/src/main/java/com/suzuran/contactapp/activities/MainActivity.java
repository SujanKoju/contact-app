package com.suzuran.contactapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.suzuran.contactapp.R;

public class MainActivity extends AppCompatActivity {
    EditText editText_email, editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editTextTextPassword);

    }

    public void loginButtonClicked(View view) {
        Log.d("SUJAN", "-- LOGIN BUTTON CLICKED --");
        if (editText_email.length() <= 0 || editText_password.length() <= 0) {
            Toast.makeText(this, "EMAIL / PASSWORD MISSING", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editText_email.getText().toString().equalsIgnoreCase("kojusujan1111@gmail.com") &&
                editText_password.getText().toString().equalsIgnoreCase("sujan")) {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "EMAIL / PASSWORD INCORRECT", Toast.LENGTH_SHORT).show();
        }


    }
}