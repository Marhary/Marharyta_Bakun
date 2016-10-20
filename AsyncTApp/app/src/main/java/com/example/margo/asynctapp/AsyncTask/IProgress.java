package com.example.margo.asynctapp.AsyncTask;

public interface IProgress<Progress> {

    void onProgressUpdate(Progress progress);

    void onProgressChanged(Progress i);
}
