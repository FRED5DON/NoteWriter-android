package com.sagosoft.app.android.notewriter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.sagosoft.app.android.notewriter.R;
import com.sagosoft.app.android.notewriter.base.BaseAppFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by FRED_anjujia on 2015/12/2.
 */
public class TextEditorFragment extends BaseAppFragment {

    private static final String kSchemeDomain="sagosoft";

    @InjectView(R.id.content_main_webView)
    WebView webView;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    public static BaseAppFragment getInstance(Bundle bundle) {
        BaseAppFragment baseAppFragment = new TextEditorFragment();
        if (bundle != null) {
            baseAppFragment.setArguments(bundle);
        }
        return baseAppFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main_editor_layout, container, false);
        ButterKnife.inject(this, view);
        initWebview();

        return view;
    }

    private void initWebview() {
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setDisplayZoomControls(false);
        setting.setSupportZoom(true);
        setting.setDomStorageEnabled(true);
        setting.setDefaultTextEncodingName("UTF-8");
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                progressBar.postInvalidate();
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {


        });
//        webView.setScrollBarStyle();
        webView.addJavascriptInterface(new FDJavascriptInterfaceImpl(getActivity()), kSchemeDomain);
        webView.loadUrl("file:///file:///android_asset/editor.html");
    }


    public void execJavascript(String script){
        webView.loadUrl("javascipt:"+script+";");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

class FDJavascriptInterfaceImpl{

    private final Context context;

    public FDJavascriptInterfaceImpl(Context context){
        this.context=context;
    }


    @JavascriptInterface
    public void getPageData(){

    }

}
