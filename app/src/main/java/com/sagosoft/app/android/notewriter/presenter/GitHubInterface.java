package com.sagosoft.app.android.notewriter.presenter;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by FRED_anjujia on 2015/12/1.
 */
public interface GitHubInterface {

    @GET("/users/{user}/repos")
    void loadRepos(@Path("user") String user,Callback<List<Repo>> callback);

    @GET("/repos")
    void loadBaidu(@Query("wd") String wd,Callback<String> callback);
}
