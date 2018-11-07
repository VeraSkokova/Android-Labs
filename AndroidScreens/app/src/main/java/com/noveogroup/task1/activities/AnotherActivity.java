package com.noveogroup.task1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.noveogroup.task1.R;

public class AnotherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity);

        Intent intent = getIntent();
        String surnameMessage = intent.getStringExtra(MainActivity.SURNAME_MESSAGE);
        String nameMessage = intent.getStringExtra(MainActivity.NAME_MESSAGE);
        String ageMessage = intent.getStringExtra(MainActivity.AGE_MESSAGE);

        TextView surnameTextView = (TextView) findViewById(R.id.shownSurname);
        surnameTextView.setText(surnameMessage);

        TextView nameTextView = (TextView) findViewById(R.id.shownName);
        nameTextView.setText(nameMessage);

        TextView ageTextView = (TextView) findViewById(R.id.shownAge);
        ageTextView.setText(ageMessage);
    }
}
