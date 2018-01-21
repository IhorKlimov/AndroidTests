package com.myhexaville.androidtests;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.myhexaville.androidtests.chat.ChatActivity;
import com.myhexaville.androidtests.firebase.FirebaseSignUpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void openChatActivity(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }

    public void openFirebaseActivity(View view) {
        startActivity(new Intent(this, FirebaseSignUpActivity.class));
    }
}
