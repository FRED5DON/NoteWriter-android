package com.sagosoft.app.android.notewriter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sagosoft.app.android.notewriter.R;
import com.sagosoft.app.android.notewriter.base.BaseAppCompatActivity;
import com.sagosoft.app.android.notewriter.presenter.GitHubInterface;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kenel.app.sagosoft.com.EventBus.EventBusBuilder;
import kenel.app.sagosoft.com.RestHttp.RestBuilder;
import kenel.app.sagosoft.com.RestHttp.RestBuilderOptions;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SplashActivity extends BaseAppCompatActivity {


    @InjectView(R.id.text_splash_activity)
    TextView textSplash;
    private boolean isRightHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        EventBusBuilder.build().register(this);
        ButterKnife.inject(this);

        textSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRepos("nokia");
                EventBusBuilder.build().post(sendToast());
            }
        });
        textSplash.setText("HI!~");
        //
        textSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(newSingleIntent(MainActivity.class));
                finish();
            }
        }, 1000);

    }


    @Produce
    public String sendToast() {
        return "你好~~~";
    }


    @Subscribe
    public void getToast(String s) {
        Toast.makeText(SplashActivity.this, s, Toast.LENGTH_LONG).show();
    }


    private void loadRepos(String user) {
        RestBuilderOptions options = new RestBuilderOptions();
        options.host = "https://api.github.com/users/nokia";
        GitHubInterface service = RestBuilder.build(options, GitHubInterface.class);
        service.loadBaidu(user, new Callback<String>() {
            @Override
            public void success(String repos, Response response) {
                isRightHere = true;
                textSplash.append("" + repos);
            }

            @Override
            public void failure(RetrofitError error) {
                isRightHere = true;
                Toast.makeText(SplashActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusBuilder.build().unregister(this);
    }
}
