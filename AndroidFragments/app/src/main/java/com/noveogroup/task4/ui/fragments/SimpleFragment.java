package com.noveogroup.task4.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noveogroup.task4.R;
import com.noveogroup.task4.ui.ColorHolder;

public class SimpleFragment extends Fragment {
    private static final String ARG_BACKGROUND_COLOR = "background_color";
    private static final String ARG_IS_ABLE_TO_CLICK = "is_able_to_click";

    private int backgroundColor;
    private boolean isAbleToClick;

    public static SimpleFragment newInstance(int backgroundColor, boolean isAbleToClick) {
        final Bundle arguments = new Bundle();
        arguments.putInt(ARG_BACKGROUND_COLOR, backgroundColor);
        arguments.putBoolean(ARG_IS_ABLE_TO_CLICK, isAbleToClick);

        final SimpleFragment simpleFragment = new SimpleFragment();
        simpleFragment.setArguments(arguments);
        return simpleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        view.setBackgroundColor(backgroundColor);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAbleToClick) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.second_fragment_container, WebFragment.newInstance())
                            .replace(R.id.web_fragment_container, SimpleFragment.newInstance(ColorHolder.BLUE, false))
                            .commit();
                    isAbleToClick = false;
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backgroundColor = getArguments().getInt(ARG_BACKGROUND_COLOR);
        isAbleToClick = getArguments().getBoolean(ARG_IS_ABLE_TO_CLICK);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_BACKGROUND_COLOR, backgroundColor);
        outState.putBoolean(ARG_IS_ABLE_TO_CLICK, isAbleToClick);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            backgroundColor = savedInstanceState.getInt(ARG_BACKGROUND_COLOR);
            isAbleToClick = savedInstanceState.getBoolean(ARG_IS_ABLE_TO_CLICK);
        }
    }
}
