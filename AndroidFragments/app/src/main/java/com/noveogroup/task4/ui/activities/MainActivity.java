package com.noveogroup.task4.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.noveogroup.task4.R;
import com.noveogroup.task4.ui.ColorHolder;
import com.noveogroup.task4.ui.fragments.FragmentWithDialog;
import com.noveogroup.task4.ui.fragments.SimpleFragment;
import com.noveogroup.task4.ui.fragments.WebFragment;


public class MainActivity extends AppCompatActivity {
    private static final String CLICKABLE_FRAGMENT_TAG = "clickable";
    private static final String UNCLICKABLE_FRAGMENT_TAG = "unclickable";
    private static final String DIALOG_FRAGMENT_TAG = "dialog_fragment";
    private static final String WEB_FRAGMENT_TAG = "web_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        if (savedInstanceState != null) {
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.first_fragment_container, SimpleFragment.newInstance(ColorHolder.RED, true), CLICKABLE_FRAGMENT_TAG)
                .add(R.id.second_fragment_container, SimpleFragment.newInstance(ColorHolder.BLUE, false), UNCLICKABLE_FRAGMENT_TAG)
                .add(R.id.web_fragment_container, WebFragment.newInstance(), WEB_FRAGMENT_TAG)
                .add(R.id.dialog_fragment_container, FragmentWithDialog.newInstance(), DIALOG_FRAGMENT_TAG)
                .commit();
    }

}
