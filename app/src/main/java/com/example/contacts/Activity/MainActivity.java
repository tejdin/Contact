package com.example.contacts.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.contacts.R;

import Adapters.ContactAdapter;
import DBConfig.RequestVolley;
import ViewModel.ContactViewModel;
import fragments.ContactList;

public class MainActivity extends AppCompatActivity {

    ContactViewModel contactViewModel;
    ContactAdapter contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestVolley.getInstance(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String signal = intent.getStringExtra("signal");
        Log.d(TAG, "onCreate: "+id+" "+signal);


        Fragment fragment = new ContactList();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("signal", signal);

        fragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }











}