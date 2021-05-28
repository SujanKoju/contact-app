package com.suzuran.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewContactActivity extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        recyclerView =findViewById(R.id.recyclerView_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(ViewContactActivity.this);
        List<Contact> allContacts = dataBaseHelper.getAllContacts();

        if (allContacts.size()>0)
        {
            ContactAdapterClass contactAdapterClass = new ContactAdapterClass(allContacts, ViewContactActivity.this);
            recyclerView.setAdapter(contactAdapterClass);
        }
        else {
            Toast.makeText(this, "--- NO CONTACTS SAVED ---", Toast.LENGTH_SHORT).show();
        }
    }
}