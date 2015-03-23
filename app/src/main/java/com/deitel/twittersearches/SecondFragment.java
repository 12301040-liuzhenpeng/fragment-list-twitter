package com.deitel.twittersearches;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SecondFragment extends Fragment {


    private String query;
    private WebView queryView;

    public static SecondFragment newInstance(String query) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("query", query);
        fragment.setArguments(args);
        return fragment;
    }

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString("query");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_second, container, false);

        // Set the adapter
        queryView = (WebView) myView.findViewById(R.id.queryView);
        queryView.getSettings().setJavaScriptEnabled(true);
        queryView.getSettings().setBuiltInZoomControls(true);
        queryView.loadUrl(query);
        queryView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView nView, String url) {
                nView.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedError(WebView nView, int errorCode,
                                        String description, String failingUrl) {
                Toast.makeText(getActivity(), "Sorry," + description, Toast.LENGTH_SHORT).show();
            }
        });
        return myView;
    }
}
