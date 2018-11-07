package com.noveogroup.task4.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noveogroup.task4.R;
import com.noveogroup.task4.ui.ColorHolder;

public class FragmentWithDialog extends DialogFragment implements View.OnClickListener {
    private static final String DIALOG_TAG = "dialog";

    public static FragmentWithDialog newInstance() {
        return new FragmentWithDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        view.setBackgroundColor(ColorHolder.GREEN);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        showDialog();
    }

    private void showDialog() {
        FragmentWithDialog fragmentWithDialog = FragmentWithDialog.newInstance();
        fragmentWithDialog.show(getFragmentManager(), DIALOG_TAG);
    }
}
