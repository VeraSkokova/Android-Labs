package com.noveogroup.task4.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.noveogroup.task4.R;

public class WebFragment extends Fragment {
    private static String url = "https://google.com";
    private WebView webView;

    public static WebFragment newInstance() {
        return new WebFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_fragment, container, false);
        webView = (WebView) view.findViewById(R.id.web_view);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
        url = webView.getUrl();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
        webView.loadUrl(url);
    }

    @Override
    public void onStop() {
        super.onStop();
        webView.stopLoading();
    }
}
