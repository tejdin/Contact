package com.example.contacts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.contacts.R;

import fragments.ContactInFragment;

public class ContactInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new ContactInFragment();
        fm.beginTransaction().add(R.id.fragment_container, fragment).commit();


    }
}