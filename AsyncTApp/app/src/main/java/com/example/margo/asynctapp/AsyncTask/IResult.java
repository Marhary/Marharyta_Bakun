package com.example.margo.asynctapp.AsyncTask;

public interface IResult<Result, P> {

    void onSuccess(Result result);

    void onError(Exception exception);

    void onProgressChanged(P progress);
}

