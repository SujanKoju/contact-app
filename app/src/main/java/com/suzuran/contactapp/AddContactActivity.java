package com.suzuran.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class AddContactActivity extends AppCompatActivity {
    EditText editTextName, editTextPhone;
    Button buttonAdd, buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        editTextName = findViewById(R.id.edittext_name);
        editTextPhone = findViewById(R.id.edittext_phone);

        buttonAdd = findViewById(R.id.button_add);
        buttonView = findViewById(R.id.button_view);

        buttonAdd.setOnClickListener(v -> {
            Log.d("SUJAN", "-- ADD BUTTON CLICKED --");
            String name = editTextName.getText().toString();
            String phone = editTextPhone.getText().toString();

            if (name.length() <= 0 || phone.length() <= 0) {
                Toast.makeText(AddContactActivity.this, "NAME / PHONE NO MISSING", Toast.LENGTH_SHORT).show();
            } else {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(AddContactActivity.this);
                dataBaseHelper.addContact(new Contact(name, phone));
                Toast.makeText(AddContactActivity.this, "--- Contact Added Successfully ---", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });

        buttonView.setOnClickListener(v -> {
            Log.d("SUJAN", "-- VIEW BUTTON CLICKED --");
            Intent intent = new Intent(AddContactActivity.this, ViewContactActivity.class);
            startActivity(intent);
        });
    }

    //TO CALL API , WE USE VOLLEY LIBRARY
    private void apiCallExample() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/2",
                null, response -> {
            try {
                Toast.makeText(AddContactActivity.this, "-- API CALLED --  NAME : " + response.getString("title"), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        });
        requestQueue.add(jsonObjectRequest);
    }
}